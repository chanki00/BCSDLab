package com.example.demo.repository;

import com.example.demo.domain.Board;

public interface BoardRepository {
    void saveBoard(Board board);
    Board findById(long id);
    void updateBoard(long id, Board board);
    void deleteById(long id);
}
