package com.project.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(
                HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 40000) // ConnectionTimeOut 40초
                        .doOnConnected(
                                connection -> {
                                    connection.addHandlerLast(new ReadTimeoutHandler(40000, TimeUnit.MILLISECONDS));
                                    connection.addHandlerLast(new WriteTimeoutHandler(40000, TimeUnit.MILLISECONDS));
                                }
                        )
        );
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(codec -> codec.defaultCodecs().maxInMemorySize(30 * 1024 * 1024)) //30M
                .build();
        return WebClient.builder()
                .clientConnector(clientHttpConnector)
                .exchangeStrategies(exchangeStrategies).build();
    }
}
