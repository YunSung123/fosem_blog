package com.tenco.blog.model;

import ch.qos.logback.core.util.StringUtil;
import com.tenco.blog.util.MyDateUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
// @Entity - JPA가 이 클래스를 데이터베이스 테이블과 매핑하는 객체로 인식하게 설정
// 즉, 이 어노테이션이 있어야 JPA가 관리함
@Entity
@Table(name = "board_tb")
public class Board {

    //@id : 이 필드가 기본키 임을 설정함
    @Id
    // IDENTITY 전략: 데이터베이스에 기본 AUTO_INCREMENT 기능 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    // createdAt -> 포맷 하는 메서드 만들어 보기
    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }
}
