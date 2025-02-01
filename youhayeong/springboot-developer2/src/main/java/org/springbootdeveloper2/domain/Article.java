package org.springbootdeveloper2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity // 엔티티로 지정
@Getter // 모든 필드에 대한 접근자 메서드 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 제어자가 protected인 기본 생성자 생성
public class Article {
    @Id // pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // title 이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false) // content라는 not null 컬럼과 매핑
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder // 생성자 위에 입력하면 빌더 패턴 방식으로 객체 생성 가능
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getId(), getTitle(), getContent() => @NoArgsConstructor, @Getter로 대치

    // 블로그 글 수정 메소드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
