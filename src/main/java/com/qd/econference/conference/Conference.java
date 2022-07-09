package com.qd.econference.conference;

import com.qd.econference.auth.User;
import com.qd.econference.room.Room;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Document(collection = "conferences")
public class Conference {
    @Id
    private BigInteger id;
    private String name;
    private Integer expectedParticipantCount;
    @DBRef
    private Room room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean enabled;
    @DBRef(lazy = true)
    private Set<User> participants;
}
