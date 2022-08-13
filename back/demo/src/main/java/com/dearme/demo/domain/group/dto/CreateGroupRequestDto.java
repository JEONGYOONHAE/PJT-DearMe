package com.dearme.demo.domain.group.dto;

import com.dearme.demo.domain.group.entity.Group;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGroupRequestDto {
    @NotBlank(message = "title은 비워둘 수 없습니다.")
    private String title;
    @NotBlank(message = "contents는 비워둘 수 없습니다.")
    private String contents;
    @NotBlank(message = "price는 비워둘 수 없습니다.")
    private Long price;

    public Group toEntity(){
        return Group.builder().title(this.title).contents(this.contents).price(this.price).build();
    }
}
