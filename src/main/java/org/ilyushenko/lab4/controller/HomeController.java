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
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class HomeController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/")
    public String hello() {

        return "/Hello";
    }
    @RequestMapping(value = "/users")
    public String getUsers(Model model) {
        model.addAttribute( "users", userDAO.getAll() );
        return "Users";
    }
    @GetMapping(value = "/addUsers")
    public String getSignUp(Model model)
    {
        model.addAttribute( "user", new User(  ) );
        return "/Sign_up";
    }
    @PostMapping(value = "/addUsers")
    public String getSignUp(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate( user, result);
        if (result.hasErrors()){
            return "/Sign_up";
        }
        userDAO.add(user);
        return "redirect:/users";
    }
}



