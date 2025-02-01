package me.leeyeeun.springbootdeveloperye.repository;

import me.leeyeeun.springbootdeveloperye.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
