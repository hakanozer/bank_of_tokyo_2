package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.LoginService;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final LoginService loginService;
    String token = "token";

    @GetMapping("/")
    public String login(Model model) {
        token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        return "login";
    }

    @PostMapping("/login")
    public String adminLogin(
            @Valid Admin admin,
            BindingResult result,
            Model model,
            @RequestParam(defaultValue = "") String token_auth
    ) {
        if ( token.equals(token_auth) ) {
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                model.addAttribute("errors", errors);
            } else {
                boolean status = loginService.login(admin);
                if (status) {
                    return "redirect:/dashboard";
                }
                //model.addAttribute("password", admin.getPassword() );
            }
        }
        token = "token";
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/";
    }

}
