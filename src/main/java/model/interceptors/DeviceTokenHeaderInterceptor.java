package model.interceptors;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class DeviceTokenHeaderInterceptor implements ClientHttpRequestInterceptor{
    private final String deviceToken;

    public DeviceTokenHeaderInterceptor(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("DeviceAccesToken", deviceToken);
        return execution.execute(request, body);
    }
}