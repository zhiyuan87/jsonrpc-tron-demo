package com.tron.jsonrpc.config;

import com.tron.jsonrpc.utils.gson.GsonUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
public class RestTemplateConfig implements ResponseErrorHandler {
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(this)
                .setReadTimeout(Duration.ofSeconds(10))
                .setConnectTimeout(Duration.ofSeconds(5))
                .additionalMessageConverters(new FormHttpMessageConverter())
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .additionalMessageConverters(new ByteArrayHttpMessageConverter())
                .additionalMessageConverters(new GsonHttpMessageConverter(GsonUtils.getInstance()))
                .build();
    }

    @Override
    public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {

    }
}