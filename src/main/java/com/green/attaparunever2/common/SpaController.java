package com.green.attaparunever2.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class SpaController {
    @RequestMapping("/{path:[^\\.]*}")
    public String redirect() {
        return "redirect:/index.html";
    }
}
