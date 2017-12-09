package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.ProductDao;
import com.niit.dao.UserDao;

@Controller
public class PageController {
	
	@Autowired
	private ProductDao productDao;
	private UserDao userDao;
	
    @RequestMapping(value={"/","/home","/index"}) 
	public ModelAndView DefaultPage(){
    	ModelAndView mv = new ModelAndView("index");
    	
    	mv.addObject("user click home",true);
    	return mv;
    	 
     }
 
    @RequestMapping(value={"/landing"}) 
   	public ModelAndView landing(){
    	ModelAndView mv = new ModelAndView("landing");
    	mv.addObject("products",productDao);
    	mv.addObject("Users",userDao);
    	mv.addObject("user click landing",true);
       	return mv;
        }
    

}
