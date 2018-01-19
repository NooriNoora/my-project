package com.niit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CategoryDao;
import com.niit.dao.ProductDao;
import com.niit.dao.UserDao;

@Controller
public class PageController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
    @RequestMapping(value={"/","/home","/index"}) 
	public ModelAndView DefaultPage(){
    	ModelAndView mv = new ModelAndView("redirect:/test");
    	mv.addObject("user click home",true);
    	return mv;
     }
 
    @RequestMapping("/test")
    public String getDefaultPage(Principal principal,Model model){
    	
//    	model.addAttribute("user", principal.getName());
    	model.addAttribute("user click home",true);
    	return "index";
    }
    
    
    @RequestMapping(value={"/landing"}) 
   	public ModelAndView landing(){
    	ModelAndView mv = new ModelAndView("landing");
    	mv.addObject("products",productDao);
    	mv.addObject("Users",userDao);
    	mv.addObject("categories",categoryDao);
    	mv.addObject("user click landing",true);
       	return mv;
        }
    

}
