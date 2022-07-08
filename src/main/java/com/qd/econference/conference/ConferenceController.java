package com.qd.econference.conference;

import com.qd.econference.users.UserDto;
import com.qd.econference.users.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Set;
import javax.validation.Valid;

import static java.util.stream.Collectors.toSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conferences")
public class ConferenceController {
    private final ConferenceService conferenceService;
    private final ConferenceDtoMapper conferenceDtoMapper;
    private final ConferenceRequestDtoMapper conferenceRequestDtoMapper;
    private final ConferenceStatisticsDtoMapper conferenceStatisticsDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    ConferenceDto add(@RequestBody @Valid AddConferenceRequestDto request) {
        return conferenceDtoMapper.mapToConferenceDto(
                conferenceService.add(conferenceRequestDtoMapper.mapToAddConferenceRequest(request)));
    }

    @PutMapping("/{conferenceId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    ConferenceDto update(@PathVariable BigInteger conferenceId, @RequestBody @Valid UpdateConferenceRequestDto request) {
        return conferenceDtoMapper.mapToConferenceDto(
                conferenceService.update(conferenceId, conferenceRequestDtoMapper.mapToUpdateConferenceRequest(request)));
    }

    @GetMapping("/{conferenceId}")
    ConferenceDto get(@PathVariable BigInteger conferenceId) {
        return conferenceDtoMapper.mapToConferenceDto(conferenceService.getById(conferenceId));
    }

    @GetMapping("/{conferenceId}/statistics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    ConferenceStatisticsDto getStatistics(@PathVariable BigInteger conferenceId) {
        return conferenceStatisticsDtoMapper.mapToConferenceStatisticsDto(conferenceService.getStatistics(conferenceId));
    }

    @GetMapping("/{conferenceId}/statistics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    Set<UserDto> getParticipants(@PathVariable BigInteger conferenceId) {
        return conferenceService.getParticipants(conferenceId).stream().map(userDtoMapper::mapToUserDto).collect(toSet());
    }
}
