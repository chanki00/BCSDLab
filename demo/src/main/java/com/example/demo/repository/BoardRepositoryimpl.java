package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepositoryimpl implements BoardRepository{

    private Map<Long, Board> boardMap = new HashMap<>();
    private long id = 1L;

    @Override
    public void saveBoard(Board board) {
        boardMap.put(id++, board);
    }

    @Override
    public Board findById(long id) {
        return boardMap.get(id);
    }

    @Override
    public void updateBoard(long id, Board board) {
        boardMap.put(id, board);
    }

    @Override
    public void deleteById(long id) {
        boardMap.remove(id);
    }
}
