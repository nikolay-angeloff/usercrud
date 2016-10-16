package com.usercrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
 
	@RequestMapping( value = {"/", "/knockout"}, method = RequestMethod.GET )
    public ModelAndView knockout() {
        ModelAndView mav = new ModelAndView("/view/index.html");
        return mav;
    }
	
	@RequestMapping( value = "/angular", method = RequestMethod.GET )
    public ModelAndView angular() {
        ModelAndView mav = new ModelAndView("/view/angular.html");
        return mav;
    }
}
