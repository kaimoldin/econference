package com.qd.econference.participant;

import com.qd.econference.auth.User;
import com.qd.econference.conference.Conference;
import com.qd.econference.conference.ConferenceService;
import com.qd.econference.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {
    private final ConferenceService conferenceService;
    private final UserService userService;

    public Set<Conference> getAvailableConferences(BigInteger userId) {
        return conferenceService.getAvailableConferences(userId);
    }

    public Set<Conference> getScheduledConferences(BigInteger userId) {
        return conferenceService.getScheduledConferences(userId);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Conference register(BigInteger userId, BigInteger conferenceId) {
        if (conferenceService.getStatistics(conferenceId).getAvailableSeatCount() == 0) {
            throw new IllegalStateException("no seats available");
        }
        Conference conference = conferenceService.getById(conferenceId);
        User user = userService.getById(userId);
        conference.getParticipants().add(user);
        return conferenceService.save(conference);
    }

    public Conference cancelRegistration(BigInteger userId, BigInteger conferenceId) {
        Conference conference = conferenceService.getById(conferenceId);
        User user = userService.getById(userId);
        conference.getParticipants().remove(user);
        return conferenceService.save(conference);
    }
}
