package com.example.eLearningDyscalculiaDisability.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Controller
public class HomeController {

    @GetMapping("/")  // When user visits "/", return index.html
    public String home() {
        return "index"; // This maps to templates/index.html
    }

    @GetMapping("/session")
    public Object getSessionData(HttpSession session, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        String username = (String) session.getAttribute("username");

        // If requested via AJAX (fetch API), return JSON
        if ("XMLHttpRequest".equals(requestedWith)) {
            Map<String, String> response = new HashMap<>();
            response.put("username", Objects.requireNonNullElse(username, ""));
            return response;
        }

        // Otherwise, redirect to homepage
        return new RedirectView("/");
    }
}
