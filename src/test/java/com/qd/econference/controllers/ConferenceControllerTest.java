package com.qd.econference.controllers;

import com.qd.econference.dto.AddConferenceRequestDto;
import com.qd.econference.dto.UpdateConferenceRequestDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.containsString;

class ConferenceControllerTest extends BaseControllerTest {
    @Test
    void add_whenItIsOk() {
        with().spec(managerAuth())
                .body(AddConferenceRequestDto.builder().name("Spring conference")
                        .startTime(LocalDateTime.of(2022, 3, 1, 9, 0))
                        .endTime(LocalDateTime.of(2022, 3, 1, 18, 0))
                        .roomId(BigInteger.valueOf(100))
                        .expectedParticipantCount(10)
                        .build())
                .post("/api/v1/conferences")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void add_whenThereAreTooManyParticipants() {
        with().spec(managerAuth())
                .body(AddConferenceRequestDto.builder().name("Spring conference")
                        .startTime(LocalDateTime.of(2022, 3, 1, 9, 0))
                        .endTime(LocalDateTime.of(2022, 3, 1, 18, 0))
                        .roomId(BigInteger.valueOf(100))
                        .expectedParticipantCount(100)
                        .build())
                .post("/api/v1/conferences")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", containsString("requested more seats than actually available"));
    }

    @Test
    void add_whenThereIsAnotherConferenceInSamePeriod() {
        with().spec(managerAuth())
                .body(AddConferenceRequestDto.builder().name("Spring-summer conference")
                        .startTime(LocalDateTime.of(2022, 3, 1, 9, 0))
                        .endTime(LocalDateTime.of(2022, 7, 1, 18, 0))
                        .roomId(BigInteger.valueOf(100))
                        .expectedParticipantCount(10)
                        .build())
                .post("/api/v1/conferences")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", containsString("another conference in the same period"));
    }

    @Test
    void update_whenPeriodIsIncorrect() {
        with().spec(managerAuth())
                .body(UpdateConferenceRequestDto.builder().name("Spring-summer conference")
                        .startTime(LocalDateTime.of(2022, 6, 1, 18, 0))
                        .endTime(LocalDateTime.of(2022, 3, 1, 9, 0))
                        .build())
                .put("/api/v1/conferences/1000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", containsString("incorrect time period"));
    }
}
