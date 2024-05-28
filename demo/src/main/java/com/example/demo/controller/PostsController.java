package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;

    @Autowired
    public PostsController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        List<ArticleDto> articleDtoList = articleService.findAll();
        model.addAttribute("articles", articleDtoList);
        return "posts";
    }

}
