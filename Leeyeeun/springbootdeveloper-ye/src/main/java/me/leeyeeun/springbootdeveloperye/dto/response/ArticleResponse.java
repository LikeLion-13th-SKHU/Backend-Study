package me.leeyeeun.springbootdeveloperye.dto.response;

import lombok.Getter;
import me.leeyeeun.springbootdeveloperye.domain.Article;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}