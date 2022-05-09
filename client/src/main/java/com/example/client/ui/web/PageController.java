package com.example.client.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    private final String remoteApiPath;

    @Autowired
    public PageController(@Value("${remote.api.path:http://localhost:9090/api/jsFunctionsExecute}") String remoteApiPath) {
        this.remoteApiPath = remoteApiPath;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("remoteApiPath", remoteApiPath);
        return "index";
    }
}
