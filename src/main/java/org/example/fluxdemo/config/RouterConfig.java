package org.example.fluxdemo.config;

import org.example.fluxdemo.handler.ApiHandler;
import org.example.fluxdemo.handler.CoinHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> coinRouter(CoinHandler coinHandler) {
        return RouterFunctions.nest(path("/api/coin"),
                route(POST("/addOne").and(accept(MediaType.APPLICATION_JSON)), coinHandler::addOne)
                .andRoute(POST("/removeByType").and(accept(MediaType.APPLICATION_JSON)), coinHandler::removeByType)
                .andRoute(POST("/updateByType").and(accept(MediaType.APPLICATION_JSON)), coinHandler::updateByType)
                .andRoute(GET("/queryByType"), coinHandler::queryByType));
    }

    @Bean
    public RouterFunction<ServerResponse> apiRouter(ApiHandler apiHandler) {
        return RouterFunctions.nest(path("/api/currentprice"),
                route(GET("/v1"), apiHandler::v1Api)
                .andRoute(GET("/v2"), apiHandler::v2Api));
    }
}
