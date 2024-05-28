package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
                    rs.getString("content")
            );

            article.setId(rs.getLong("id"));

            return article;
        };
    }

    @Override
    public void saveArticle(Article article) {
        String sql = "insert into article (author_id, board_id, title, content, created_date, modified_date)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new
                    String[]{"id"});
            ps.setLong(1, article.getMemberId());
            ps.setLong(2, article.getBoardId());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            ps.setTimestamp(5, Timestamp.valueOf(article.getCreatedDay()));
            ps.setTimestamp(6, Timestamp.valueOf(article.getUpdatedDay()));
            return ps;
        }, keyHolder);

        Long key = keyHolder.getKey().longValue();
        article.setId(key);

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
