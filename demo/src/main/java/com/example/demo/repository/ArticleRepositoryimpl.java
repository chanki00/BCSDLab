package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepositoryimpl implements ArticleRepository{
    private long id = 1L;
    private Map<Long, Article> articleMap = new HashMap<>();


    @Override
    public void saveArticle(Article article) {
        articleMap.put(id++, article);
    }

    @Override
    public Article findById(long id) {
        return articleMap.get(id);
    }

    @Override
    public void updateArticle(long id, Article article) {
        articleMap.put(id, article);
    }

    @Override
    public void deleteById(long id) {
        articleMap.remove(id);
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        for (Article article : articleMap.values()) {
            articles.add(article);
        }
        return articles;
    }
}
