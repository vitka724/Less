package org.ilyushenko.lab4.controller;


import org.ilyushenko.lab4.dao.UserDAO;
import org.ilyushenko.lab4.model.User;
import org.ilyushenko.lab4.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;


@Controller
public class HomeController {
    private final UserValidator userValidator;
    @Autowired
    private UserDAO userDAO;

    public HomeController(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    // private Collection<User> users = new ArrayList<>();
    @GetMapping(value = "/")
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/users")
    public String getUsers(Model model) throws SQLException {
        model.addAttribute("users", userDAO.getAll());
        return "Users";
    }

    @GetMapping(value = "/addUsers")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "Sign_up";
    }

    @PostMapping(value = "/addUsers")
    public String getSignUp(@ModelAttribute @Valid User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "Sign_up";
        }
       // userDAO.add(user);
        return "redirect:/users";
    }
}


