package com.mysite.sbb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerFormDto {
    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;
}