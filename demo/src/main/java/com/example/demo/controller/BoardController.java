package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        boardService.saveBoard(board);
        return ResponseEntity.ok(board);
    }

    @GetMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> readBoard(@PathVariable("id") Long id) {
        Board board = boardService.findById(id);
        if (board == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(board);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable("id") Long id, Board newBoard) {
        Board board = boardService.findById(id);
        if (id == null) return ResponseEntity.notFound().build();
        boardService.updateBoard(id, newBoard);
        return ResponseEntity.ok(newBoard);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

