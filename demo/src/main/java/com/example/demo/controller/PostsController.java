package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;
    private final BoardService boardService;

    public PostsController(DataSource dataSource) {
        this.articleService = new ArticleService(dataSource);
        this.boardService = new BoardService(dataSource);
    }

    @GetMapping("/posts")
    public String posts(@RequestParam(name = "boardId", required = false, defaultValue = "1")Long boardId, Model model) {
        List<ArticleDto> articleDtoList = articleService.findByBoardId(boardId);
        Board board = boardService.findById(boardId);
        model.addAttribute("boardName", board.getBoardName());
        model.addAttribute("articles", articleDtoList);
        return "posts";
    }

}
