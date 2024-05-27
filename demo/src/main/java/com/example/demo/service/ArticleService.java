package com.example.demo.service;

import com.example.demo.domain.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {   //C: 생성, R: 조회, U: 수정, D: 삭제
    private Map<Long, Article> articleMap = new HashMap<>();
    private Long id = 0L;


    public void saveArticle(Article article) {
        articleMap.put(id++, article);
    }

    public Article findById(long id) {
        return articleMap.get(id);
    }

    public void updateArticle(long id, Article article) {
        articleMap.put(id, article);
    }

    public void deleteById(long id) {
        articleMap.remove(id);
    }

}
