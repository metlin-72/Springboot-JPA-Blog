package com.metlin.blog.dto;

import lombok.Data;

@Data
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;

    public ReplySaveRequestDto() {
    }

    public ReplySaveRequestDto(int userId, int boardId, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.content = content;
    }
}
