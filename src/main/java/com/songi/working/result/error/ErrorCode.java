package com.songi.working.result.error;

public enum  ErrorCode {
    INVALID_INPUT_VALUE(400, "잘못된 요청입니다."),
    NOT_FOUND(404,  "찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버에 요청 중 에러가 발생했습니다.");

    private final String message;
    private int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return status;
    }
}
