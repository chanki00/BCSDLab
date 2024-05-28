package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Primary
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(
                    rs.getLong("author_id"),
                    rs.getLong("board_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("created_date").toLocalDateTime(),
                    rs.getTimestamp("modified_date").toLocalDateTime()
            );
            return article;
        };
    }

    @Override
    public void saveArticle(Article article) {
        String sql = "insert into article (author_id, board_id, title, content, created_date, modified_date)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getMemberId(), article.getBoardId(),
                article.getTitle(), article.getContent(),
                LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public Article findById(long id) {
        String sql = "select * from article where id=?";

        Article article = jdbcTemplate.queryForObject(sql, articleRowMapper(), id);

        return article;
    }

    @Transactional
    @Override
    public void updateArticle(long id, Article article) {
        String sql = "update article set title=?, content=?, modified_date=? where id=?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), LocalDateTime.now(), id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        String sql = "delete from article where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Article> findAll() {
        String sql = "select * from article";
        List<Article> articleList = jdbcTemplate.query(sql, articleRowMapper());

        return articleList;
    }

    @Override
    public List<Article> findAllonBoard(Long board_id) {
        String sql = "select * from article where board_id=?";
        List<Article> articleList = jdbcTemplate.query(sql, articleRowMapper(), board_id);
        return articleList;
    }
}
