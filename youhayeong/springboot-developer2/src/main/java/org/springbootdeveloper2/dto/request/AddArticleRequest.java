package org.springbootdeveloper2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springbootdeveloper2.domain.Article;

@NoArgsConstructor // 매개변수가 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    private String title;

    private String content;

    public Article toEntity() { // 빌더 패턴을 이용해 DTO를 엔티티로 만들어주는 메서드
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
