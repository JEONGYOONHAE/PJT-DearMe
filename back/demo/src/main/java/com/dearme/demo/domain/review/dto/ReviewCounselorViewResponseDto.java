package com.dearme.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewCounselorViewResponseDto {
    private Long id;
    private String nickname;
    private Long value;
    private String contents;
}
