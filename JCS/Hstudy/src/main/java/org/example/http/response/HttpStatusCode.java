package org.example.http.response;

public enum HttpStatusCode {
    HTTP_OK("200","OK"),
    HTTP_FOUND("302", "FOUND"),
    HTTP_BAD_REQUEST("400", "BAD_REQUEST"),
    HTTP_UNAUTHORIZED("403", "UNAUTHORIZED"),
    HTTP_NOT_FOUND("404", "NOT_FOUND"),
    HTTP_METHOD_NOT_ALLOWED("405", "NOT_ALLOWED");
    private final String code;
    private final String DEFAULT_MESSAGE;
    HttpStatusCode(String code, String defaultMessage) {
        this.code = code;
        this.DEFAULT_MESSAGE = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
