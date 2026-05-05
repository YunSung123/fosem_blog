package com.tenco.blog._core.error;

public class Exception500 extends RuntimeException {
    public Exception500(String msg) {
        super(msg);
    }

    // thorw new Exception500("잘못된 요청"); 사용 예시
}
