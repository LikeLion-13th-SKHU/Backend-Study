package org.springbootdeveloper2.repository;

import org.springbootdeveloper2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
