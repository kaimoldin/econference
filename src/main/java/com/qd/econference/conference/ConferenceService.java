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
import java.util.Set;

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
        checkSeats(room.getSeatCount(), request.getExpectedParticipantCount());
        return save(Conference.builder()
                .name(request.getName())
                .expectedParticipantCount(request.getExpectedParticipantCount())
                .room(room)
                .build());
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
            checkSeats(conference.getRoom().getSeatCount(), request.getExpectedParticipantCount());
            conference.setExpectedParticipantCount(request.getExpectedParticipantCount());
        }
        if (request.getEnabled() != null) {
            conference.setEnabled(request.getEnabled());
        }
        return save(conference);
    }

    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    private void checkSeats(Integer seatCount, Integer expectedParticipantCount) {
        if (seatCount.compareTo(expectedParticipantCount) < 0) {
            throw new IllegalStateException("requested more seats than actually available");
        }
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
