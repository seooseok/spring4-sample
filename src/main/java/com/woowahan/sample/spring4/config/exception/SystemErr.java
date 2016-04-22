package com.woowahan.sample.spring4.config.exception;

/**
 * 연관된 서브시스템에서 내는 에러로 해당 에러는 API 시스템이 문제가 아닌 연동 문제로 판단한다.
 */
public enum SystemErr implements Err {
    ACCESS_FAIL_FOR_DB("S001", "DB 접근 실패"),
    ACCESS_FAIL_FOR_SUB_SYSTEM("S001", "서브 시스템 접근 실패"),

    UNKNOWN_ERROR("S999", "알 수 없는 오류");

    private String code;
    private String message;

    SystemErr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return "[" + code + "] " + message;
    }
}
