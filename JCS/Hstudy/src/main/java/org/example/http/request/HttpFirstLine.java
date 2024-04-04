package org.example.http.request;

import org.example.http.HttpMethod;

public class HttpFirstLine {
    private static final String REQUEST_SPLIT_DELIMITER = " ";
    private static final int HTTP_METHOD_INDEX = 0;
    private static final int HTTP_REQUEST_URL_INDEX = 1;
    private static final int HTTP_VERSION_INDEX = 2;
    private final HttpMethod httpMethod;
    private final String requestUrl;
    private final String httpVersion;

    private HttpFirstLine(HttpMethod httpMethod, String requestUrl, String httpVersion) {
        this.httpMethod = httpMethod;
        this.requestUrl = requestUrl;
        this.httpVersion = httpVersion;
    }

    public static HttpFirstLine of(String line) {
        String[] splits = line.split(REQUEST_SPLIT_DELIMITER);
        return new HttpFirstLine(HttpMethod.of(splits[HTTP_METHOD_INDEX]),
                splits[HTTP_REQUEST_URL_INDEX],
                splits[HTTP_VERSION_INDEX]);
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
