package com.mysite.sbb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionFormDto {
    // @Size(max=200)은 최대 길이가 200 바이트를 넘으면 안된다는 의미이다.
    @NotBlank(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotBlank(message="내용은 필수항목입니다.")
    private String content;
}