package com.woowahan.sample.spring4.config.exception;

/**
 * 사용자 에러
 * 유저가 내는 에러로 유저는 사람만을 의미하지 않으면 API 시스템을 사용하는 모두가 해당된다. API call 자체를 잘못 날리는 경우 해당 에러코드를 이용한다.
 */
public enum HumanErr implements Err {
    INVALID_ARGS("H001", "잘못된 입력값"),
    EXPIRED("H002", "만료"),
    IS_EMPTY("H003", "값이 없음"),
    NO_SUPPORT("H004", "지원하지 않는 요청");

    private String code;
    private String message;

    HumanErr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return "[" + code + "] " + message + " ";
    }
}
