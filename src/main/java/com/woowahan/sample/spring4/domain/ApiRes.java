package com.woowahan.sample.spring4.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.woowahan.sample.spring4.common.constant.ApiStatus;
import lombok.Getter;
import lombok.ToString;


/**
 * ApiRes
 */
@ToString
public class ApiRes {

    @Getter
    @JsonView(JView.Res.class)
    private final String status;        //상태

    @Getter
    @JsonView(JView.Res.class)
    private final String code;        //상태

    @Getter
    @JsonView(JView.Res.class)
    private final String message;     //메시지

    @Getter
    @JsonView(JView.Res.class)
    private final Object result;

    private ApiRes(Builder builder) {
        status = builder.status;
        code = builder.code;
        message = builder.message;
        result = builder.result;
    }

    public static Builder ok() {
        return new Builder(ApiStatus.ok, "0000", "success");
    }

    public static class Builder<T> {
        private final String status;
        private final String code;
        private final String message;
        private final T result;

        public Builder(ApiStatus status, String code, String message) {
            this.status = status.name();
            this.code = code;
            this.message = message;
            this.result = null;
        }

        public Builder(ApiStatus status, String code, String message, T t) {
            this.status = status.name();
            this.code = code;
            this.message = message;
            this.result = t;
        }



        public ApiRes build() {
            return new ApiRes(this);
        }
    }

}
