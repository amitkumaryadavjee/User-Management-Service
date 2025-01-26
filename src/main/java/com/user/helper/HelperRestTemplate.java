package com.user.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class HelperRestTemplate {

    @Value("${external.api.base.url}")
    private String externalApiBaseUrl;

    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate = new RestTemplate();

    private final String  DATA_SERVICE= "Data-service";

    private void setHttpHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    //In future  we can integrate other service also
    private String getServiceUrl(String appName) {
        switch (appName) {
            case DATA_SERVICE:
                return externalApiBaseUrl;
            default:
                break;
        }

        log.error("No service url found for app {}", appName);
        throw new RestClientException("No service url found for app " + appName);
    }

    @Retryable(
            value = {RestClientException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 20000)
    )
    public Object fetchData() {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(DATA_SERVICE) + "/users")
                .toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched the Order Successfully ");
        return response.getBody();
    }
}