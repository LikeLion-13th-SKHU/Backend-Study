package me.jungdaun.springbootdeveloper.repository;

import me.jungdaun.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
