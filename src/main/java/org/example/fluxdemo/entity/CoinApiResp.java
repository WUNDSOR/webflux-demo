package org.example.fluxdemo.entity;

import lombok.Data;

@Data
public class CoinApiResp {
    private String updateTime;
    private String type;
    private String name;
    private Float exchangeRate;
}
