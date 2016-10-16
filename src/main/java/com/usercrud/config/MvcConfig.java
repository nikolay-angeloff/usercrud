package com.usercrud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
@Configuration  
@EnableWebMvc  
@ComponentScan(basePackages = { "com.usercrud.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	    registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	}
	
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("/view/index.html");
    }
 
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
