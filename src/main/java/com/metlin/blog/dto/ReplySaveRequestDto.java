package com.metlin.blog.dto;

import lombok.Data;

@Data
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;
}
