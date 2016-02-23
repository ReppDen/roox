package ru.repp.den.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginPage(Model model){
        return "login";
    }


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String start(Model model){
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/protected")
    @Secured("ROLE_USER")
    public String protectedPage(Model model){
        return "protected";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}