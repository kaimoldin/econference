package com.qd.econference.controllers;

import com.qd.econference.dto.ConferenceDto;
import com.qd.econference.mappers.ConferenceDtoMapper;
import com.qd.econference.mappers.ConferenceRequestDtoMapper;
import com.qd.econference.mappers.ConferenceStatisticsDtoMapper;
import com.qd.econference.mappers.UserDtoMapper;
import com.qd.econference.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Set;

import static com.qd.econference.controllers.RestConstants.API_V1_PATH_PREFIX;
import static java.util.stream.Collectors.toSet;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH_PREFIX + "/users/{userId}")
public class ParticipantController {
    private final ParticipantService participantService;
    private final ConferenceDtoMapper conferenceDtoMapper;
    private final ConferenceRequestDtoMapper conferenceRequestDtoMapper;
    private final ConferenceStatisticsDtoMapper conferenceStatisticsDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/available-conferences")
    Set<ConferenceDto> getAvailableConferences(@PathVariable BigInteger userId) {
        return participantService.getAvailableConferences(userId).stream().map(conferenceDtoMapper::mapToConferenceDto).collect(toSet());
    }

    @GetMapping("/conferences")
    Set<ConferenceDto> getScheduledConferences(@PathVariable BigInteger userId) {
        return participantService.getScheduledConferences(userId).stream().map(conferenceDtoMapper::mapToConferenceDto).collect(toSet());
    }

    @PutMapping("/conferences/{conferenceId}")
    ConferenceDto register(@PathVariable BigInteger userId, @PathVariable BigInteger conferenceId) {
        return conferenceDtoMapper.mapToConferenceDto(participantService.register(userId, conferenceId));
    }

    @DeleteMapping("/conferences/{conferenceId}")
    @PreAuthorize("@securityCheckService.canCancelRegistration(#userId, principal)")
    ConferenceDto cancelRegistration(@PathVariable BigInteger userId, @PathVariable BigInteger conferenceId) {
        return conferenceDtoMapper.mapToConferenceDto(participantService.cancelRegistration(userId, conferenceId));
    }
}
