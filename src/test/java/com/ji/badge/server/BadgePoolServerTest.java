package com.ji.badge.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ji.badge.server.data.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BadgePoolServerTest {

    private ObjectMapper jacksonMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void start() {
    }

    @Test
    void stop() {
    }

    @Test
    void generateTestData() {
        System.out.println("[JUnit] testing - generateTestData");
        String s = null;
        try {
            s = BadgePoolServer.getInstance().generateTestData();
            //Gson gson = new Gson();
        //TestData testData = gson.fromJson(s, TestData.class);
            TestData testData = jacksonMapper.readValue(s, TestData.class);
            assertNotNull(testData,"test data should not be null");
            boolean status = testData.status();
            int value = testData.value();
            assertEquals(true, status, "default testa data status should be true");
            assertEquals(33, value, "default testa data value should be 33");
        } catch (IOException e) {
            e.printStackTrace();
            fail("fail badly");

        }
        System.out.println("SUCCESS");


    }

    @Test
    void isOnline() {
        assertEquals(false, BadgePoolServer.getInstance().isOnline());
    }
}