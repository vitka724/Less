package org.ilyushenko.lab4.controller;

import org.ilyushenko.lab4.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class HomeController {
    Collection<User> users = new ArrayList<>();
    @GetMapping(value = "/")
    public String Hello() {
        return "/Hello";
    }
    @RequestMapping(value = "/users")
    public String getUsers(Model model)  {
        model.addAttribute( "users", users);;
        return "Users";
    }
    @GetMapping(value = "/addUsers")
    public String getSignUp(Model model)
    {
        model.addAttribute( "user", new User(  ) );
        return "/Sign_Up";
    }
    @PostMapping(value = "/addUsers")
    public String getSignUp(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "/Sign_Up";
        }
        users.add(user);
        return "Users";
    }
}

