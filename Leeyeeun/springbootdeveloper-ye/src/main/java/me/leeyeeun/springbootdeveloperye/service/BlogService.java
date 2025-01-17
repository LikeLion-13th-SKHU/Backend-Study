package me.leeyeeun.springbootdeveloperye.service;

import lombok.RequiredArgsConstructor;

import me.leeyeeun.springbootdeveloperye.domain.Article;
import me.leeyeeun.springbootdeveloperye.dto.request.AddArticleRequest;
import me.leeyeeun.springbootdeveloperye.dto.request.UpdateArticleRequest;
import me.leeyeeun.springbootdeveloperye.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 빈으로 등록
@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest addArticleRequest) {
        return blogRepository.save(addArticleRequest.toEntity());
    }

    // 블로그 글 목록 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 한 개 조회
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("not found: " + id));
    }

    // 블로그 글 한 개 삭제
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정
    @Transactional // 트랜잭션 메서드
    public Article update(Long id, UpdateArticleRequest updateArticleRequest) {
        Article article = blogRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("not found: " + id));

        article.update(updateArticleRequest.getTitle(), updateArticleRequest.getContent());

        return article;
    }
}
