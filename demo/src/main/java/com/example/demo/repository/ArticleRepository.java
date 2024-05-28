package com.example.demo.repository;

import com.example.demo.domain.Article;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository {
    void saveArticle(Article article);

    Article findById(long id);

    void updateArticle(long id, Article article);

    void deleteById(long id);

    List<Article> findAll();

    List<Article> findAllonBoard(Long board_id);
}
