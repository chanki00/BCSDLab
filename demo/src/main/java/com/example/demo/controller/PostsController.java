package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;
    private final BoardService boardService;

    public PostsController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String posts(@RequestParam(name = "boardId", required = false, defaultValue = "1")Long boardId, Model model) {
        List<ArticleDto> articleDtoList = articleService.findAll();
        model.addAttribute("articles", articleDtoList);
        return "posts";
    }

}
