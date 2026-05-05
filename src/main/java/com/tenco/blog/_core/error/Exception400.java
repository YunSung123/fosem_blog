package com.tenco.blog._core.error;

public class Exception400 extends  RuntimeException {

    public Exception400(String msg) {
        super(msg);
    }

    // thorw new Exception400("잘못된 요청"); 사용 예시
}
