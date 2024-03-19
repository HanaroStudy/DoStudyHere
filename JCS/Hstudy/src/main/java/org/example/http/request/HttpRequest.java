package org.example.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.http.HttpMethod;

public class HttpRequest {

    private static final String EMPTY_LINE = "";
    private static final String CONTENT_LENGTH = "Content-Length";
    private final HttpFirstLine httpFirstLine;
    private final HttpHeaders httpHeaders;
    private final HttpBody httpBody;

    private HttpRequest(HttpFirstLine httpFirstLine, HttpHeaders httpHeaders, HttpBody httpBody) {
        this.httpFirstLine = httpFirstLine;
        this.httpHeaders = httpHeaders;
        this.httpBody = httpBody;
    }

    public static HttpRequest from(BufferedReader bufferedReader) throws IOException {
        HttpFirstLine httpFirstLine = parseFirstLine(bufferedReader);
        HttpHeaders httpHeaders = parseHeaderLine(bufferedReader);
        HttpBody httpBody = parseBody(bufferedReader, httpHeaders);
        return new HttpRequest(httpFirstLine, httpHeaders, httpBody);
    }

    private static HttpFirstLine parseFirstLine(BufferedReader bufferedReader) throws IOException {
        return HttpFirstLine.of(bufferedReader.readLine());
    }

    private static HttpHeaders parseHeaderLine(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals(EMPTY_LINE)) {
                break;
            }
            lines.add(line);
        }
        return HttpHeaders.of(lines);
    }

    private static HttpBody parseBody(BufferedReader bufferedReader, HttpHeaders httpHeaders) throws IOException {
        if (!httpHeaders.containsHeader(CONTENT_LENGTH)) {
            return new HttpBody(EMPTY_LINE);
        }
        int contentLength = Integer.parseInt(httpHeaders.getHeaderValue(CONTENT_LENGTH));
        char[] buffer = new char[contentLength];
        bufferedReader.read(buffer, 0, contentLength);
        return new HttpBody(new String(buffer));
    }

    public String getRequestUrl() {
        return httpFirstLine.getRequestUrl();
    }

    public String getBody() {
        return httpBody.getContent();
    }

    public boolean isPost() {
        return httpFirstLine.getHttpMethod().equals(HttpMethod.POST);
    }

    public boolean isGet() {
        return httpFirstLine.getHttpMethod().equals(HttpMethod.GET);
    }
}
