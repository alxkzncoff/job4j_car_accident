package ru.job4j.accident.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginControl {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "fail", required = false) String fail,
                            Model model) {
        String errorMessage = null;
        String successReg = null;
        if (error != null) {
            errorMessage = "Логин или пароль введены неверно!";
        }
        if (logout != null) {
            errorMessage = "Вы вышли из системы!";
        }
        if ("true".equals(fail)) {
            errorMessage = "Пользователь с таким именем уже существует!";
        }
        if ("false".equals(fail)) {
            successReg = "Регистрация прошла успешно!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successReg", successReg);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
