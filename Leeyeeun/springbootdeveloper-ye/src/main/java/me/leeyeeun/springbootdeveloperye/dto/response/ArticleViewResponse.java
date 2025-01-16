package me.leeyeeun.springbootdeveloperye.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.leeyeeun.springbootdeveloperye.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();;
        this.title = article.getTitle();
        this.content = article.getContent();;
        this.createdAt = article.getCreatedAt();
    }
}
