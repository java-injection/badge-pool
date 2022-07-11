package com.ji.badge.server.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record TestData(
        @JsonProperty("timestamp") Date timestamp,
        @JsonProperty("status")boolean status,
        @JsonProperty("value") int value) {
}
