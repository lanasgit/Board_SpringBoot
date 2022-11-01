package com.mysite.sbb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionFormDto {
    // @NotEmpty는 해당 값이 Null 또는 빈 문자열("")을 허용하지 않음을 의미한다. 그리고 여기에 사용된 message 속성은 검증이 실패할 경우 화면에 표시할 오류 메시지이다.
    // @Size(max=200)은 최대 길이가 200 바이트를 넘으면 안된다는 의미이다.
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}