package org.example.http.response;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.example.http.HttpMethod;
import org.example.http.request.HttpBody;

/**
 * HttpResponse 객체 ? 어쩌면 템플릿 같은 역할을 한다. 기본 구성이 형셩되어 있고 WAS에서는 이 템플릿에 내용을 추가한다. 예를 들어, was의 수행 상태에 따라 StatusCode를 설정하거나,
 * 클라이언트에 넘겨줄 헤더 또는 쿠키, 바디 등을 설정할 수 있다. Response 객체는 was 전반에 걸쳐 변형되어진다. was에게 자신의 값 일부를 수정할 수 있는 Interface를 제공해야한다.
 */

public class HttpResponse {
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CHARACTER_SET = ";charset=utf-8 ";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String LOCATION = "Location";
    private static final String ALLOW = "Allow";
    private static final String SPACE = " ";
    private static final String LINE_SEPARATOR = "";
    private static final String HEADER_JOINER = ": ";
    private static final String ALLOW_JOINER = ", ";

    private HttpStatusLine httpStatusLine;
    private final HashMap<String, String> responseHeaders = new LinkedHashMap<>();
    private HttpBody httpBody;

    public HttpResponse() {
    }

    public String getResponse() {
        return String.join("\r\n",
                httpStatusLine.toString(),
                parseHeader(),
                LINE_SEPARATOR,
                httpBody.getContent());
    }

    private String parseHeader() {
        List<String> list = responseHeaders.entrySet().stream()
                .map(entry -> entry.getKey() + HEADER_JOINER + entry.getValue())
                .collect(Collectors.toList());
        return String.join("\r\n", list);
    }

    public void setHttpBody(String content) {
        this.httpBody = new HttpBody(content);
        this.responseHeaders.put(CONTENT_LENGTH, content.getBytes().length + SPACE);
    }

    public void setCookies(String cookie) {
        responseHeaders.put(SET_COOKIE, cookie);
    }

    public void setLocation(String location) {
        responseHeaders.put(LOCATION, location);
    }

    public void setHttpStatus(HttpStatusCode status) {
        this.httpStatusLine = HttpStatusLine.of(status);
    }

    public void setContentType(ContentType contentType) {
        responseHeaders.put(CONTENT_TYPE, contentType.getValue() + CHARACTER_SET);
    }

    public void setAllow(List<HttpMethod> httpMethods) {
        String allowMethods = httpMethods.stream()
                .map(HttpMethod::getName)
                .collect(Collectors.joining(ALLOW_JOINER));
        responseHeaders.put(ALLOW, allowMethods + SPACE);
    }
}
