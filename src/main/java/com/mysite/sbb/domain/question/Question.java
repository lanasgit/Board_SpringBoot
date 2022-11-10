package com.mysite.sbb.domain.question;

import com.mysite.sbb.domain.answer.Answer;
import com.mysite.sbb.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    // cascade = CascadeType.REMOVE: 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private User author;

    // 하나의 질문에 여러사람이 추천할 수 있고 한 사람이 여러 개의 질문을 추천할 수 있다. 이렇듯 질문과 추천인은 부모와 자식의 관계가 아니고 대등한 관계이기 때문에 @ManyToMany를 사용해야 한다.
    // List가 아닌 Set으로 한 이유는 추천인은 중복되면 안되기 때문이다.
    // @ManyToMany 관계로 속성을 생성하면 새로운 테이블을 생성하여 데이터를 관리한다. 테이블에는 서로 연관된 엔티티의 고유번호(id) 2개가 프라이머리 키로 되어 있기 때문에 다대다(N:N) 관계가 성립하는 구조이다.
    @ManyToMany
    Set<User> voter;
}