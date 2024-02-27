package org.example.fluxdemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CoinApiDto {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Coin> bpi;
    @Data
    public static class Time {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class Coin {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonProperty("rate_float")
        private Float rateFloat;
    }
}
