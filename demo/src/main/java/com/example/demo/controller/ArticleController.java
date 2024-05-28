package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //객체를 리턴 -> HTTP 응답 제어 불가능 -> ResponseEntity 사용

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.saveArticle(article);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> readArticle(@PathVariable("id") long id) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article newArticle) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        articleService.updateArticle(id, newArticle);
        return ResponseEntity.ok(newArticle);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<List<ArticleDto>> readAllArticles() {
        List<ArticleDto> articles = articleService.findAll();
        return ResponseEntity.ok(articles);
    }
}
