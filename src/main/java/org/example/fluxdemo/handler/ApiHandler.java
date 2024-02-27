package org.example.fluxdemo.handler;

import org.example.fluxdemo.entity.CoinApiDto;
import org.example.fluxdemo.entity.CoinApiResp;
import org.example.fluxdemo.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ApiHandler {
    private final WebClient webClient;
    private final DateTimeFormatter voFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
    private final DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private CoinRepository coinRepository;


    public ApiHandler() {
        this.webClient = WebClient.create("https://api.coindesk.com");
    }
    public Mono<ServerResponse> v1Api(ServerRequest request) {
        return getData().flatMap(entity -> ServerResponse.ok().bodyValue(entity));
    }
    public Mono<ServerResponse> v2Api(ServerRequest request) {
        return getData()
                .map(HttpEntity::getBody)
                .flatMap(dto -> {
                    List<CoinApiResp> list = new ArrayList<>();
                    Map<String, CoinApiDto.Coin> map = dto.getBpi();
                    map.forEach((code, coinEntity) -> {
                        list.add(getApiResp(dto, code, coinEntity));
                    });
                    return ServerResponse.ok().bodyValue(list);
                });
    }

    private String getCoinName(String type) {
        return coinRepository.queryByType(type).getName();
    }

    private Mono<ResponseEntity<CoinApiDto>> getData() {
        return webClient.get()
                .uri("/v1/bpi/currentprice.json")
                .retrieve()
                .toEntity(CoinApiDto.class);
    }

    private CoinApiResp getApiResp(CoinApiDto dto, String code, CoinApiDto.Coin coinEntity) {
        LocalDateTime updateTime = LocalDateTime.parse(dto.getTime().getUpdatedISO(), isoFormatter);
        String formatTime = voFormatter.format(updateTime);

        CoinApiResp vo = new CoinApiResp();
        vo.setType(coinEntity.getCode());
        vo.setExchangeRate(coinEntity.getRateFloat());
        vo.setUpdateTime(formatTime);
        vo.setName(getCoinName(code));
        return vo;
    }
}
