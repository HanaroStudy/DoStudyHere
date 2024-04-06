package org.example.http.response;

public class HttpStatusLine {
    private final String HTTP_VERSION = "HTTP/1.1";
    private final HttpStatusCode HTTP_STATUS;

    public static HttpStatusLine of(HttpStatusCode statusCode) {
        return new HttpStatusLine(statusCode);
    }

    private HttpStatusLine(HttpStatusCode statusCode) {
        this.HTTP_STATUS = statusCode;

    }

    @Override
    public String toString() {
        return String.join(" ",
                HTTP_VERSION,
                HTTP_STATUS.getCode(),
                HTTP_STATUS.getDefaultMessage());
    }
}
