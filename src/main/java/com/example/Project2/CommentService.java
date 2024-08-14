package com.example.Project2;

import com.example.Project2.model.Article;
import com.example.Project2.model.Comment;
import com.example.Project2.repo.ArticleRepository;
import com.example.Project2.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final ArticleRepository articleRepo;
    private final CommentRepository commentRepo;
    public CommentService(
        ArticleRepository articleRepo,
        CommentRepository commentRepo
    ) {
        this.articleRepo = articleRepo;
        this.commentRepo = commentRepo;
    }

    // CREATE
    public Comment create(
            Long articleId,
            String content,
            String password
    ) {
        Article article = articleRepo.findById(articleId).orElseThrow(null);
        Comment comment = new Comment(
                content,
                password,
                article
        );
        return commentRepo.save(comment);
    }

    // READ ONE
    public Comment readOne(
            Long articleId,
            Long commentId
    ) {
        Comment comment = commentRepo.findById(commentId).orElseThrow();

        if (!comment.getArticle().getId().equals(articleId))
            throw new RuntimeException();

        return comment;
    }

    // READ ALL
    public List<Comment> readAll() {
        return commentRepo.findAll();
    }

    // UPDATE
    public Comment update(
            Long commentId,
            Article title,
            String content,
            String password
    ) {
        Optional<Comment> optionalTargetComment = commentRepo.findById(commentId);
        if (optionalTargetComment.isEmpty()) {
            return null;
        }
        Comment targetComment = optionalTargetComment.get();
        if (password.equals(optionalTargetComment.get().getPassword())) {
            targetComment.setArticle(title);
            targetComment.setContent(content);
        }
        return commentRepo.save(targetComment);
    }

    // DELETE
    public void delete(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
