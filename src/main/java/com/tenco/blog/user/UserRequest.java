package com.tenco.blog.user;

import lombok.Data;

public class UserRequest {

    // 로그인 DTO
    @Data
    public static class LogionDTO {
        private String username;
        private String password;

        // 유효성 검사
        public void validate() {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("사용자명은 필수 입니다.");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("비밀번호는 필수 입니다.");
            }
        }
    }

    // 회원 가입 DTO
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        // 편의 기능 추가 - 내가 가지고 있는 멤버 변수에 값으로 User 엔터를 생성
        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }

        // 유효성 검사 메서드 만들기
        public void validate() {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("사용자명은 필수 입니다.");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("비밀번호는 필수 입니다.");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("이메일은 필수 입니다.");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
            }
        }
    }

    @Data
    public static class UpdateDTO {
        private String password;

        public void validate() throws IllegalAccessException {
            if(password == null || password.isBlank()) {
                throw new IllegalArgumentException("비밀번호는 필수 입니다");
            }
            if (password.length() < 4) {
                throw new IllegalAccessException("비밀번호는 4자 이상이어야 합니다");
            }
        }
    }

}
