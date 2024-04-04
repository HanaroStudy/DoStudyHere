package org.example.http.response;

public enum HttpStatusCode {
    HTTP_OK("200"),
    HTTP_FOUND("302"),
    HTTP_BAD_REQUEST("400"),
    HTTP_UNAUTHORIZED("403"),
    HTTP_NOT_FOUND("404"),
    HTTP_METHOD_NOT_ALLOWED("405");
    private final String code;

    HttpStatusCode(String code) {
        this.code = code;
    }
}
