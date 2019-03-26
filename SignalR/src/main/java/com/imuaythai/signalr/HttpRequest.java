package com.imuaythai.signalr;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String url;
    private final Map<String, String> headers = new HashMap<>();

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
