package com.project.utils;

import com.project.exception.ConnectionError;
import com.project.exception.ErrorResponse;
import javassist.compiler.CompileError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConnectorUtils {

    private final WebClient.Builder webClientBuilder;

    public <req, res> Mono<res> send(
            HttpMethod httpMethod,
            String baseUrl,
            MultiValueMap<String,String> httpHeaders,
            req httpBody,
            Class<req> requestType,
            Class<res> responseType
    ) {
        WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();
        return webClient.method(httpMethod)
                .headers(header -> header.addAll(httpHeaders))
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