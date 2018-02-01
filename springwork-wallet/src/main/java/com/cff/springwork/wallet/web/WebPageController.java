package com.cff.springwork.wallet.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {
    @RequestMapping("/")
    public String index(Model model) {
    	model.addAttribute("hello", "成功了？");
        return "index";
    }
}
