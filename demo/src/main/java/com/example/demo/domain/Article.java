package com.example.demo.domain;

import java.time.LocalDateTime;

public class Article {
    private long memberId;
    private long boardId;
    private String title;
    private String content;
    private LocalDateTime createdDay;
    private LocalDateTime updatedDay;

    public Article(long memberId, long boardId, String title, String content, LocalDateTime createdDay, LocalDateTime updatedDay) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDay = LocalDateTime.now();
        this.updatedDay = LocalDateTime.now();
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
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
}
