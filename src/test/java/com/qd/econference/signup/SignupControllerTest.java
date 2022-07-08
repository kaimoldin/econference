package com.qd.econference.signup;

import com.qd.econference.BaseControllerTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.with;

class SignupControllerTest extends BaseControllerTest {
    @Autowired
    SignupController signupController;

    @Test
    void signup_whenItIsOk() {
        with().body(SignupRequestDto.builder().email("test@test").name("test").password("123").build())
                .post("/api/v1/signup")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void signup_whenEmailIsMissing() {
        with().body(SignupRequestDto.builder().name("test").password("123").build())
                .post("/api/v1/signup")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void signup_whenPasswordIsMissing() {
        with().body(SignupRequestDto.builder().email("test@test").name("test").build())
                .post("/api/v1/signup")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void signup_whenUserExists() {
        with().body(SignupRequestDto.builder().email("admin@localhost").name("admin").password("123").build())
                .post("/api/v1/signup")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
