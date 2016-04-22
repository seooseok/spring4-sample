package com.woowahan.sample.spring4.config.exception;

/**
 * 개발 상의 문제나 프로세스 버그로 인해서 나는 에러
 */
public enum ProcessErr implements Err {
    DEVELOP_MISTAKE("P001", "개발 버그"),
    WRONG_SETTING("P002","잘못된 설정"),

    UNKNOWN_ERROR("P999", "알 수 없는 프로세스 에러")
    ;

    private String code;
    private String message;

    ProcessErr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return "[" + code + "] " + message;
    }
}
