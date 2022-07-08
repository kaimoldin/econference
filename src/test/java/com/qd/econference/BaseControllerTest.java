package com.qd.econference;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
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
}
