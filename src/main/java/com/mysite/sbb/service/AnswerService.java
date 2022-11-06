package com.mysite.sbb.service;

import com.mysite.sbb.domain.answer.Answer;
import com.mysite.sbb.domain.answer.AnswerRepository;
import com.mysite.sbb.domain.question.Question;
import com.mysite.sbb.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, User author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}
