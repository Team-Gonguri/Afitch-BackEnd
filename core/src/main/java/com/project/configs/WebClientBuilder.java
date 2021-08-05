package com.project.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class WebClientBuilder {

    @Bean
    public WebClient.Builder webClientBuilder() {
        ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(
                HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 40000) // ConnectionTimeOut 40초
                        .doOnConnected(
                                connection -> {
                                    connection.addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS));
                                    connection.addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS));
                                }
                        )
        );
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(codec -> codec.defaultCodecs().maxInMemorySize(30 * 1024 * 1024)) //30M
                .build();
        return WebClient.builder()
                .clientConnector(clientHttpConnector)
                .exchangeStrategies(exchangeStrategies);
    }
}
