package com.hedgehogs.space.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author natalya_ezhkova@mail.ru
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }


}
