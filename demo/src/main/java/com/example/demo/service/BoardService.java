package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.JdbcBoardRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(DataSource dataSource) {
        this.boardRepository = new JdbcBoardRepository(dataSource);
    }

    public void saveBoard(Board board) {
        boardRepository.saveBoard(board);
    }

    public Board findById(long id) {
        return boardRepository.findById(id);
    }

    public void updateBoard(long id, Board board) {
        boardRepository.updateBoard(id, board);
    }

    public void deleteById(long id) {
        boardRepository.deleteById(id);
    }

}
