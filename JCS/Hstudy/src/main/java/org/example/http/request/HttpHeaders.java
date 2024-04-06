package org.example.http.request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpHeaders {
    private static final String HEADER_SPLIT_DELIMITER = ": ";
    private static final int HEADER_KEY_INDEX = 0;
    private static final int HEADER_VALUE_INDEX = 1;
    private final Map<String, String> headers;
    private HttpHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public static HttpHeaders of(List<String> lines) {
        Map<String, String> headers = new LinkedHashMap<>();
        for (String line : lines) {
            String[] splits = line.split(HEADER_SPLIT_DELIMITER);
            headers.put(splits[HEADER_KEY_INDEX], splits[HEADER_VALUE_INDEX]);
        }
        return new HttpHeaders(headers);
    }

    public boolean containsHeader(String header) {
        return headers.containsKey(header);
    }

    public String getHeaderValue(String header) {
        if (!headers.containsKey(header)) {
            throw new IllegalArgumentException("일치하는 키가 존재하지 않습니다.");
        }
        return headers.get(header);
    }
}
