package com.tenco.blog._core.error;

public class Exception404 extends RuntimeException {
    public Exception404(String msg) {
        super(msg);
    }

    // thorw new Exception404("잘못된 요청"); 사용 예시
}
