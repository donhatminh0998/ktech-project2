package com.example.Project2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("articles/{articleId}/comments")
public class CommentController {
    private final CommentService service;
    public CommentController(
            CommentService service
            ) {
        this.service = service;
    }

    // CREATE
    @PostMapping("create")
    public String create(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("commentPassword")
            String commentPassword
    ) {
        service.create(articleId, content, commentPassword);
        return String.format("redirect:/articles/%d", articleId);
    }

    // READ ALL
//    @GetMapping
//    public String readAll(Model model) {
//        model.addAttribute("comments", service.readAll());
//        return ".html";
//    }
}
