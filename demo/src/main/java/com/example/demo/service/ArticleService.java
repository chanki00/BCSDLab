package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.JdbcArticleRepository;
import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {   //C: 생성, R: 조회, U: 수정, D: 삭제
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(DataSource dataSource) {
        this.articleRepository = new JdbcArticleRepository(dataSource);
        this.memberRepository = new JdbcMemberRepository(dataSource);
    }

    public void saveArticle(Article article) {
        articleRepository.saveArticle(article);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

    public void updateArticle(long id, Article article) {
        articleRepository.updateArticle(id, article);
    }

    public void deleteById(long id) {
        articleRepository.deleteById(id);
    }

    public List<ArticleDto> findAll() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articles) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            articleDto.setAuthor(memberRepository.findById(article.getMemberId()).getName());
            articleDto.setDate(article.getCreatedDay());

            articleDtoList.add(articleDto);
        }

        return articleDtoList;
    }

    public List<ArticleDto> findByBoardId(long boardId) {
        List<ArticleDto> articles = new ArrayList<>();
        List<Article> articlesOnBoard = articleRepository.findAllonBoard(boardId);
        for (Article article : articlesOnBoard) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            articleDto.setAuthor(memberRepository.findById(article.getMemberId()).getName());
            articleDto.setDate(article.getCreatedDay());

            articles.add(articleDto);
        }

        return articles;
    }

}
