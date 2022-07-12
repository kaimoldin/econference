package com.qd.econference.controllers;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    @Qualifier("initMongoDB")
    CommandLineRunner initMongoDBRunner;

    @Autowired
    @Qualifier("resetMongoDB")
    CommandLineRunner resetMongoDBRunner;

    @BeforeEach
    void setUp() throws Exception {
        RestAssured.port = port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        resetMongoDBRunner.run();
        initMongoDBRunner.run();
    }

    protected RequestSpecification adminAuth() {
        BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
        basicAuthScheme.setUserName("admin@localhost");
        basicAuthScheme.setPassword("123");
        return new RequestSpecBuilder().setAuth(basicAuthScheme).build();
    }

    protected RequestSpecification managerAuth() {
        BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
        basicAuthScheme.setUserName("manager@localhost");
        basicAuthScheme.setPassword("123");
        return new RequestSpecBuilder().setAuth(basicAuthScheme).build();
    }

    protected RequestSpecification participantAuth() {
        BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
        basicAuthScheme.setUserName("participant@localhost");
        basicAuthScheme.setPassword("123");
        return new RequestSpecBuilder().setAuth(basicAuthScheme).build();
    }
}
