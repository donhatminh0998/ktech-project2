package com.example.Project2.repo;

import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
//    Optional<Article> findFirstByIdLessThanAndBoardType_IdOrderByIdDesc(Long id, Long boardId);
//    Optional<Article> findFirstByIdGreaterThanAndBoardType_IdOrderByIdDesc(Long id, Long boardId);
}
