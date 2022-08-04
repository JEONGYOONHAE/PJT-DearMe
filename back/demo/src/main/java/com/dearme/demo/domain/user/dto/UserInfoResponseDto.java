package com.dearme.demo.domain.user.dto;

import com.dearme.demo.domain.user.entity.Gender;
import com.dearme.demo.domain.user.entity.Type;
import com.dearme.demo.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public class UserInfoResponseDto {
    private String id;
    private String pw;
    private String nickname;
    private String email;
    private Gender gender;
    private Type type;
    private String pictureUrl;
    private CounselorProfileInfoDto counselorProfileInfoDto;

    public static UserInfoResponseDto ofUser(User user){
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .pw(user.getPw())
                .nickname(user.getNickName())
                .email(user.getEmail())
                .gender(user.getGender())
                .type(user.getType())
                .pictureUrl(user.getPicture().getRealFileName())
                .build();
    }

    public static UserInfoResponseDto ofCounselor(User counselor){
        return UserInfoResponseDto.builder()
                .id(counselor.getId())
                .pw(counselor.getPw())
                .nickname(counselor.getNickName())
                .email(counselor.getEmail())
                .gender(counselor.getGender())
                .type(counselor.getType())
                .pictureUrl(counselor.getPicture().getRealFileName())
                .counselorProfileInfoDto(CounselorProfileInfoDto.of(counselor.getCounselorProfile()))
                .build();
    }
}