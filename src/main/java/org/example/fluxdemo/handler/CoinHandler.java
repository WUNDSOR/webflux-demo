package org.example.fluxdemo.handler;

import org.example.fluxdemo.entity.CoinVo;
import org.example.fluxdemo.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CoinHandler {
    @Autowired
    private CoinRepository coinRepository;
    public Mono<ServerResponse> addOne(ServerRequest request) {
        Mono<CoinVo> mono = request.bodyToMono(CoinVo.class);
        return mono.flatMap((coinVo -> {
            coinRepository.insert(coinVo);
            return ServerResponse.ok().bodyValue(coinVo);
        }));
    }

    public Mono<ServerResponse> removeByType(ServerRequest request) {
        Mono<CoinVo> mono = request.bodyToMono(CoinVo.class);
        return mono.flatMap(coinVo -> {
            coinRepository.deleteByType(coinVo.getType());
            return ServerResponse.ok().bodyValue(coinVo);
        });
    }

    public Mono<ServerResponse> updateByType(ServerRequest request) {
        Mono<CoinVo> mono = request.bodyToMono(CoinVo.class);
        return mono.flatMap(coinVo -> {
            coinRepository.updateByType(coinVo);
            return ServerResponse.ok().bodyValue(coinVo);
        });
    }

    public Mono<ServerResponse> queryByType(ServerRequest request) {
        String type = request.queryParam("type").orElse("");
        return Mono.just(type).flatMap(param -> {
            CoinVo result = coinRepository.queryByType(param);
            return ServerResponse.ok().bodyValue(result);
        });
    }
}
