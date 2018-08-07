package com.lfpapp.lfpapp.controllers;

import com.lfpapp.lfpapp.models.User;
import com.lfpapp.lfpapp.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String landing(Model model) {
        model.addAttribute("title", "Welcome to LFP");
        return "landing";
    }

    @RequestMapping(value = "signup")
    public String signup(Model model){
        model.addAttribute(new User());
        model.addAttribute("title", "Create an Account");
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute @Valid User newUser, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("errors", new User());
            return "category/add";
        } else {
            userDao.save(newUser);
            return "Hello, " + newUser.getUsername() + "! Your location is " + newUser.getLocation() + ".";
        }
    }

}
