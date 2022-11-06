package com.mysite.sbb.controller;

import com.mysite.sbb.dto.UserCreateFormDto;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model, UserCreateFormDto userCreateFormDto) {
        model.addAttribute("userCreateFormDto", userCreateFormDto);
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateFormDto userCreateFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        // bindingResult.rejectValue(필드명, 오류코드, 에러메시지)를 의미
        if (!userCreateFormDto.getPassword1().equals(userCreateFormDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateFormDto.getUsername(),
                    userCreateFormDto.getEmail(), userCreateFormDto.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}