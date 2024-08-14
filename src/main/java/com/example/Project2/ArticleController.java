package com.example.Project2;


import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import com.zaxxer.hikari.util.UtilityElf;
import lombok.Getter;
import org.hibernate.dialect.function.LpadRpadPadEmulation;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService service;
    private final BoardService boardService;
    public ArticleController(
            ArticleService service,
            BoardService boardService
    ) {
        this.service = service;
        this.boardService = boardService;
    }

    // CREATE
    @GetMapping("create")
    public String createView(Model model) {
        model.addAttribute("boards", boardService.readAll());
        return "articles/create.html";
    }

    @PostMapping("create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ) {
        Long id = service.create(title, content, password, boardId).getId();
        return String.format("redirect:/articles/%d", id);
    }

    // READ ALL
    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("articles", service.readAll());
        return "articles/home.html";
    }

    // READ ONE
    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ) {
//        Article article = service.readOne(id);
//        Long boardId = article.getBoardType().getId();
//        Article prevArticle = service.findPrev(id, boardId);
//        Article nextArticle = service.findNext(id, boardId);
//        model.addAttribute("prevArticle", nextArticle);
//        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("article", service.readOne(id));
        return "articles/read.html";
    }

    // UPDATE
    @GetMapping("{id}/update")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model1,
            Model model2
    ) {
        model1.addAttribute("boards", boardService.readAll());
        model2.addAttribute("article", service.readOne(id));
        return "articles/update.html";
    }
    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ) {
        service.update(id, title, content, password, boardId);
        return String.format("redirect:/articles/%d", id);
    }

    // DELETE
    @PostMapping("{id}/delete")
    public String delete(
        @PathVariable("id")
        Long id,
        @RequestParam("password")
        String password
    ) {
        service.delete(id, password);
        return "redirect:/articles";
    }
}
