package com.tenco.blog._core.error;

public class Exception401 extends RuntimeException {
    public Exception401(String msg) {
        super(msg);
    }

    // thorw new Exception401("잘못된 요청"); 사용 예시
}
