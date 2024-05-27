package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroduceController {


    @GetMapping("/introduce")
    public String introdueName(@RequestParam(name="name", required = false, defaultValue = "김찬기") String name, Model model) {
        //@RequestParam -> required를 false로 하면 필수가 아님
        model.addAttribute("name", name);
        return "introduce";
    }

    @ResponseBody
    @GetMapping("/json")
    public User json() {
        User user = new User();
        user.setAge(25);
        user.setName("김찬기");
        return user;
    }

}
