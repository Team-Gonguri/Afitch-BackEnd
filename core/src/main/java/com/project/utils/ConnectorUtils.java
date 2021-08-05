package com.project.utils;

import com.project.exception.ConnectionError;
import com.project.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConnectorUtils {

    private final WebClient webClient;

    public <req, res> Mono<res> send(
            HttpMethod httpMethod,
            String baseUrl,
            req httpBody,
            Class<req> requestType,
            Class<res> responseType
    ) {
        return webClient.mutate().baseUrl(baseUrl).build()
                .method(httpMethod)
                .body(Mono.just(httpBody), requestType)
                .retrieve()
                .onStatus(HttpStatus::isError, (responseError ->
                        responseError.bodyToMono(ErrorResponse.class)
                                .map(parsingError -> {
                                    throw new ConnectionError(parsingError);
                                })
                )).bodyToMono(responseType);
    }
}