package com.qd.econference.room;

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
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomDtoMapper roomDtoMapper;
    private final RoomRequestDtoMapper roomRequestDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    RoomDto add(@RequestBody @Valid AddRoomRequestDto request) {
        return roomDtoMapper.mapToRoomDto(
                roomService.add(roomRequestDtoMapper.mapToAddRoomRequest(request)));
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    RoomDto update(@PathVariable BigInteger roomId, @RequestBody @Valid UpdateRoomRequestDto request) {
        return roomDtoMapper.mapToRoomDto(
                roomService.update(roomId, roomRequestDtoMapper.mapToUpdateRoomRequest(request)));
    }

    @GetMapping("/{roomId}")
    RoomDto get(@PathVariable BigInteger roomId) {
        return roomDtoMapper.mapToRoomDto(roomService.getById(roomId));
    }
}
