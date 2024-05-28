package com.example.demo.controller;

import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
}

