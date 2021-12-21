package com.fanbakery.fancake.module.logout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LogoutController {
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
