package com.niit.userController;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.ProductDao;

import com.niit.model.Product;



@Controller
public class ProductController 
{

	@Autowired
	private ProductDao productDao;
	@RequestMapping("/productform")
	
	public String productform(@ModelAttribute("product")Product product, Model model){
		
	model.addAttribute("product",new Product());
	model.addAttribute("products",productDao.getAllProduct());
	return "productform";
	}
	@RequestMapping(value="/addproduct",method= RequestMethod.POST)
	public String productform(@ModelAttribute("product")@Valid Product product,BindingResult result, Model model) {
		if (result.hasErrors()) {
		model.addAttribute("product",product);
		model.addAttribute("products",productDao.getAllProduct());
        List<Product> products= productDao.getAllProduct();
        return "productform";
        }
        productDao.addProduct(product);
        return "redirect:/productform";
	}
	
	@RequestMapping(value="/editproduct/{productId}")
	public ModelAndView editProduct(@PathVariable("productId")int productId)
	{
		ModelAndView mv=new ModelAndView("Product");
		mv.addObject("product", productDao.getProductById(productId));
		mv.setViewName("productform");
		return mv;
	
	}
	@RequestMapping(value="/deleteproduct/{productId}",method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int productId)
	{
		productDao.delete(productId);
		return "redirect:/productform";
		
	}
	}
