package org.springbootdeveloper2.controller;

import lombok.RequiredArgsConstructor;
import org.springbootdeveloper2.domain.Article;
import org.springbootdeveloper2.dto.response.ArticleListViewResponse;
import org.springbootdeveloper2.dto.response.ArticleViewResponse;
import org.springbootdeveloper2.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    
    private final BlogService blogService;

    // 블로그 글 전체 리스트 담은 뷰 리턴
    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articleListViewResponses = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articleListViewResponses);

        return "articleList";
    }

    // 블로그 글 보러가기 누르면 블로그 글 보이도록
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    // 블로그 글 수정
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
