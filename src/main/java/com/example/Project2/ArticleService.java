package com.example.Project2;

import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import com.example.Project2.repo.ArticleRepository;
import com.example.Project2.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final BoardRepository boardRepo;
    public ArticleService(ArticleRepository repository, BoardRepository boardRepo) {
        this.repository = repository;
        this.boardRepo = boardRepo;
    }
//    public Article findPrev(Long id, Long boardId) {
//        return repository.findFirstByIdLessThanAndBoardType_IdOrderByIdDesc(id, boardId).orElseThrow();
//    }
//    public Article findNext(Long id, Long boardId) {
//        return repository.findFirstByIdGreaterThanAndBoardType_IdOrderByIdDesc(id, boardId).orElseThrow();
//    }


    // CREATE
    public Article create(
            String title,
            String content,
            String password,
            Long boardId
    ) {
        Board board = boardRepo.findById(boardId).orElseThrow(null);
        Article article = new Article(
                title,
                content,
                password,
                board
        );
        return repository.save(article);
    }

    // READ ALL
    public List<Article> readAll() {
        return repository.findAll();
    }

    // READ ONE
    public Article readOne(Long id) {
        return repository.findById(id).orElseThrow();
    }

    // UPDATE
    public Article update(
            Long id,
            String title,
            String content,
            String password,
            Long boardId
    ) {
        Optional<Article> optionalTarget = repository.findById(id);
        if (optionalTarget.isEmpty()) {
            return null;
        }
        Article target = optionalTarget.get();
        if (password.equals(optionalTarget.get().getPassword())) {
            Board board = boardRepo.findById(boardId).orElseThrow();
            target.setTitle(title);
            target.setContent(content);
            target.setBoardType(board);
        }
        return repository.save(target);
    }

    // DELETE
    public void delete (Long id, String password) {
        if (password.equals(repository.findById(id).get().getPassword())) {
            repository.deleteById(id);
        }
    }

}
