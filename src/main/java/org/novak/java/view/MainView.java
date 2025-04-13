package org.novak.java.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainView {

    @GetMapping
    public String showLoginPage() {
        return "mainView";
    }
}