package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Primary
@Repository
public class JdbcBoardRepository implements BoardRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board(
                    rs.getString("name")
            );
            return board;
        };
    }

    @Override
    public void saveBoard(Board board) {
        String sql = "insert into board (name) VALUES (?)";
        jdbcTemplate.update(sql, board.getBoardName());
    }

    @Override
    public Board findById(long id) {
        String sql = "select * from board where id = ?";

        Board board = jdbcTemplate.queryForObject(sql, boardRowMapper(), id);

        return board;
    }

    @Transactional
    @Override
    public void updateBoard(long id, Board board) {
        String sql = "update board set name=? where id=?";
        jdbcTemplate.update(sql, board.getBoardName(), id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        String sql = "delete from board where id=?";
        jdbcTemplate.update(sql, id);
    }
}
