package com.skypro.adsonline.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperComment {
    private int count; // общее количество комментариев
    private List<Comment> results;
}
