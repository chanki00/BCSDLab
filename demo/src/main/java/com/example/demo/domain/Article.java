package com.example.demo.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long memberId;
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createdDay;
    private LocalDateTime updatedDay;

    public Article(Long memberId, Long boardId, String title, String content) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDay = LocalDateTime.now();
        this.updatedDay = LocalDateTime.now();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId (Long memberId) {
        this.memberId = memberId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(LocalDateTime createdDay) {
        this.createdDay = createdDay;
    }

    public LocalDateTime getUpdatedDay() {
        return updatedDay;
    }

    public void setUpdatedDay(LocalDateTime updatedDay) {
        this.updatedDay = updatedDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
