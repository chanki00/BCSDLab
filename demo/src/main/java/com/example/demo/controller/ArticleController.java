package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    private ArticleService articleService = new ArticleService();

    //객체를 리턴 -> HTTP 응답 제어 불가능 -> ResponseEntity 사용

    @PostMapping("/article")
    public ResponseEntity<Article> createArtice(@RequestBody Article article) {
        articleService.saveArticle(article);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> readArticle(@PathVariable("id") long id) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article newArticle) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        article.setTitle(newArticle.getTitle());
        article.setContent(newArticle.getContent());
        articleService.updateArticle(id, article);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
