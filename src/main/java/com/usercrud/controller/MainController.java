package com.usercrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
 
	@RequestMapping( value = "/", method = RequestMethod.GET )
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/view/index.html");
        return mav;
    }
}
