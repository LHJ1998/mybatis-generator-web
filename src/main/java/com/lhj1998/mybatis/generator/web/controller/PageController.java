package com.lhj1998.mybatis.generator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "/{page}")
    public String page(@PathVariable String page){
        return page + ".html";
    }

}
