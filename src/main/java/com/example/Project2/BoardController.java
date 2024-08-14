package com.example.Project2;

import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import com.example.Project2.repo.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public BoardController (BoardService boardService,
                            ArticleService articleService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }

    // READ ALL
    @GetMapping
    public String readAll(Model model) {
        List<Article> articles = articleService.readAll();
        model.addAttribute("articles", articles);
        model.addAttribute("boards", boardService.readAll());
        return "boards/board.html";
    }

    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ) {
//        model.addAttribute("articles", articleService.readAll());
        model.addAttribute("board", boardService.readOne(id));
        return "boards/readboard.html";
    }
}
