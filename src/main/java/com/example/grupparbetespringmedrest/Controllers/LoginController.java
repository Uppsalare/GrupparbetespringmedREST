package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping()
    public String login(Model model) {
        User user = new User();
        model.addAttribute("loginUser", user);
        return "login";
    }
}