package com.tenco.blog._core.error;

public class Exception403 extends RuntimeException {
    public Exception403(String msg) {
        super(msg);
    }

    // thorw new Exception403("잘못된 요청"); 사용 예시
}
