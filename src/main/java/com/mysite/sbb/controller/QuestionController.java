package com.mysite.sbb.controller;

import com.mysite.sbb.domain.question.Question;
import com.mysite.sbb.domain.user.User;
import com.mysite.sbb.dto.AnswerFormDto;
import com.mysite.sbb.dto.QuestionFormDto;
import com.mysite.sbb.service.QuestionService;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    // Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할을 한다. Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있다.
    // Model 객체는 따로 생성할 필요없이 컨트롤러 메서드의 매개변수로 지정하기만 하면 스프링부트가 자동으로 Model 객체를 생성한다.
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // @PreAuthorize("isAuthenticated()"): 로그인이 필요한 메서드를 의미한다. 만약 @PreAuthorize("isAuthenticated()") 애너테이션이 적용된 메서드가 로그아웃 상태에서 호출되면 로그인 페이지로 이동된다.
    // 로그인 하지 않은 상태에서 "질문 등록" 버튼을 누르면 "로그인" 화면으로 이동한다. 그리고 로그인을 진행하면 원래 하려고 했던 "질문 등록" 화면으로 이동한다. 이것은 로그인 후에 원래 하려고 했던 페이지로 리다이렉트 시키는 스프링 시큐리티의 기능이다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(Model model, QuestionFormDto questionFormDto) {
        model.addAttribute("questionFormDto", questionFormDto);
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    // @GetMapping시 사용했던 questionCreate 메서드명과 동일하게 사용할 수 있다. (단, 매개변수의 형태가 다른 경우에 가능하다. - 메서드 오버로딩)
    @PostMapping("/create")
    // BindingResult 매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체이다.
    // BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다. 만약 2개의 매개변수의 위치가 정확하지 않다면 @Valid만 적용이 되어 입력값 검증 실패 시 400 오류가 발생한다.
    public String questionCreate(@Valid QuestionFormDto questionFormDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        User user = userService.getUser(principal.getName());
        questionService.create(questionFormDto.getSubject(), questionFormDto.getContent(), user);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }

    // 요청 URL http://localhost:8080/question/detail/2의 숫자 2처럼 변하는 id 값을 얻을 때에는 위와 같이 @PathVariable 애너테이션을 사용해야 한다.
    // 이 때 @RequestMapping(value = "/question/detail/{id}") 에서 사용한 id와 @PathVariable("id")의 매개변수 이름이 동일해야 한다.
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerFormDto answerFormDto) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("answerFormDto", answerFormDto);
        return "question_detail";
    }
}