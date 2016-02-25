package ru.repp.den.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginPage(Model model){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String start(Model model){
        return "index";
    }

    @RequestMapping({ "/user", "/me" })
    @ResponseBody
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

}