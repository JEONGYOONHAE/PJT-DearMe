package com.dearme.demo.domain.board.dto.board;

import com.dearme.demo.domain.board.dto.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardViewResponseDto {
    private Long id;

    private String nickName;
    private String title;

    private String contents;

    private int hitCnt;

    private Date date;

    private List<CommentResponseDto> comments;

}
