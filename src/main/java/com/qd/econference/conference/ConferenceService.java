package com.qd.econference.conference;

import com.qd.econference.auth.User;
import com.qd.econference.room.Room;
import com.qd.econference.room.RoomService;
import com.qd.econference.users.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final RoomService roomService;
    private final UserService userService;

    public Conference getById(BigInteger id) {
        return conferenceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("conference by id: " + id));
    }

    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    public Conference add(AddConferenceRequest request) {
        Room room = roomService.getById(request.getRoomId());
        return save(check(Conference.builder()
                .name(request.getName())
                .expectedParticipantCount(request.getExpectedParticipantCount())
                .room(room)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build()));
    }

    public Conference update(BigInteger conferenceId, UpdateConferenceRequest request) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new NoSuchElementException("conference by id: " + conferenceId));
        if (Strings.isNotBlank(request.getName())) {
            conference.setName(request.getName());
        }
        if (request.getRoomId() != null) {
            conference.setRoom(roomService.getById(request.getRoomId()));
        }
        if (request.getExpectedParticipantCount() != null) {
            conference.setExpectedParticipantCount(request.getExpectedParticipantCount());
        }
        if (request.getStartTime() != null) {
            conference.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            conference.setEndTime(request.getEndTime());
        }
        if (request.getEnabled() != null) {
            conference.setEnabled(request.getEnabled());
        }
        return save(check(conference));
    }

    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    protected Conference check(Conference conference) {
        if (conference.getRoom().getSeatCount().compareTo(conference.getExpectedParticipantCount()) < 0) {
            throw new IllegalStateException("requested more seats than actually available");
        }
        if (!conference.getStartTime().isBefore(conference.getEndTime())) {
            throw new IllegalStateException("incorrect time period");
        }
        List<Conference> conferencesInPeriod = conferenceRepository.findConferences(
                        conference.getRoom().getId(), conference.getStartTime(), conference.getEndTime())
                .stream()
                .filter(c -> !Objects.equals(c.getId(), conference.getId()))
                .toList();
        if (!conferencesInPeriod.isEmpty()) {
            throw new IllegalStateException("another conference in the same period: " + conferencesInPeriod.get(0));
        }
        return conference;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ConferenceStatistics getStatistics(BigInteger conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new NoSuchElementException("conference by id: " + conferenceId));
        int participantCount = conference.getParticipants().size();
        return ConferenceStatistics.builder()
                .actualParticipantCount(participantCount)
                .expectedParticipantCount(conference.getExpectedParticipantCount())
                .availableSeatCount(conference.getRoom().getSeatCount() - participantCount)
                .build();
    }

    public Set<User> getParticipants(BigInteger conferenceId) {
        return conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new NoSuchElementException("conference by id: " + conferenceId))
                .getParticipants();
    }

    public Set<Conference> getScheduledConferences(BigInteger userId) {
        User user = userService.getById(userId);
        return conferenceRepository.findAllByParticipantsContainingAndEnabledIsTrue(user);
    }

    public Set<Conference> getAvailableConferences(BigInteger userId) {
        User user = userService.getById(userId);
        return conferenceRepository.findAllByParticipantsNotContainingAndEnabledIsTrue(user);
    }
}
