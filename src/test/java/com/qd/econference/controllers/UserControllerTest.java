package com.qd.econference.controllers;

import com.qd.econference.dto.AddUserRequestDto;
import com.qd.econference.dto.UpdateUserRequestDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class UserControllerTest extends BaseControllerTest {
    @Test
    void add_whenItIsOk() {
        with().spec(adminAuth())
                .body(AddUserRequestDto.builder().email("test@test").name("test").password("123").roles(Set.of("MANAGER")).build())
                .post("/api/v1/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void add_whenRolesAreMissing() {
        with().spec(adminAuth())
                .body(AddUserRequestDto.builder().email("test@test").name("test").password("123").build())
                .post("/api/v1/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void update_whenItIsOk() {
        with().spec(adminAuth())
                .body(UpdateUserRequestDto.builder().name("manager2").build())
                .put("/api/v1/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", equalTo("manager2"));
    }

    @Test
    void update_whenRestClientIsNotAdmin() {
        with().spec(participantAuth())
                .body(UpdateUserRequestDto.builder().name("manager2").build())
                .put("/api/v1/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    void update_whenRestClientUpdatesOwnAccount() {
        with().spec(managerAuth())
                .body(UpdateUserRequestDto.builder().name("manager2").build())
                .put("/api/v1/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", equalTo("manager2"));
    }

    @Test
    void get_whenItIsOk() {
        with().spec(managerAuth())
                .get("/api/v1/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", equalTo("Bob"));
    }

    @Test
    void getRoles_whenItIsOk() {
        with().spec(managerAuth())
                .get("/api/v1/users/2/roles")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("", hasSize(1))
                .body("[0].name", equalTo("MANAGER"));
    }
}
