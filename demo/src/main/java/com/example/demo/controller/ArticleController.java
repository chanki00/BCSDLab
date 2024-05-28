package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(DataSource dataSource) {
        this.articleService = new ArticleService(dataSource);
    }

    //객체를 리턴 -> HTTP 응답 제어 불가능 -> ResponseEntity 사용

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<List<Article>> findByBoardId(@RequestParam(name = "boardId", required = false, defaultValue = "1")Long boardId) {
        List<Article> articles = articleService.findAll(boardId);
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.saveArticle(article);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> readArticle(@PathVariable("id") long id) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article newArticle) {
        Article article = articleService.findById(id);
        if (article == null) return ResponseEntity.notFound().build();
        Article updatedArticle = articleService.updateArticle(id, newArticle);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
