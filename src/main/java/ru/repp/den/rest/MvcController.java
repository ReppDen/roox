package ru.repp.den.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MvcController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(Model model){
        return "index";
    }

    @RequestMapping(value = { "/user", "/me" }, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadForm() {
        return "uploadForm";
    }

}