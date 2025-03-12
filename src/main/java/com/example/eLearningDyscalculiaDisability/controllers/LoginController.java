package com.example.eLearningDyscalculiaDisability.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login"; // Ensure this matches your login HTML file
    }

    @PostMapping(value = "/login/auth", consumes = "application/x-www-form-urlencoded")
    public RedirectView login(@RequestParam("username") String username, 
                              @RequestParam("password") String password, 
                              HttpSession session, 
                              RedirectAttributes redirectAttributes) {
        Optional<Student> studentOpt = studentRepository.findByUsername(username);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (student.getPassword().equals(password)) {
                session.setAttribute("studentId", student.getId());
                session.setAttribute("username", student.getUsername());

                // Debugging Log
                LOGGER.info("User logged in: " + student.getUsername() + " (ID: " + student.getId() + ")");
                
                return new RedirectView("/mainpage");
            } else {
                LOGGER.warning("Login failed: Incorrect password for user " + username);
            }
        } else {
            LOGGER.warning("Login failed: User " + username + " not found");
        }

        redirectAttributes.addAttribute("error", "true");
        return new RedirectView("/login");
    }

    // Logout and clear session
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        LOGGER.info("User logged out: " + session.getAttribute("username"));
        session.invalidate();
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
        return new RedirectView("/login");
    }

    // Check session data
    @GetMapping("/session")
    @ResponseBody
    public Map<String, String> getSession(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String username = (String) session.getAttribute("username");
        String studentId = (session.getAttribute("studentId") != null) ? session.getAttribute("studentId").toString() : null;

        if (username != null && studentId != null) {
            response.put("username", username);
            response.put("studentId", studentId);
          //  LOGGER.info("Session data retrieved: username=" + username + ", studentId=" + studentId);
        } else {
            response.put("message", "Not authenticated");
          //  LOGGER.warning("Session data not found");
        }
        return response;
    }
}
