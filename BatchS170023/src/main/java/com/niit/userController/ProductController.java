package com.niit.userController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CategoryDao;
import com.niit.dao.ProductDao;
import com.niit.model.Category;
import com.niit.model.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private Product product;

	@RequestMapping("/productform")
	public String productform(@ModelAttribute("product") Product product, Model model) {

		List<Category> categories = categoryDao.getAllCategory();
		for(Category c : categories){
			
			System.out.println("Category : "+c.getName());
		}
		model.addAttribute("categories", categories);
		model.addAttribute("product", new Product());
		List<Product> products = productDao.getAllProduct();
		model.addAttribute("products", products);
		
		for(Product p : products){
			
			System.out.println("Product Category : "+p.getCategory().getName());
		}
		
		return "productform";
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String product(@ModelAttribute("category") Category category, Model model) {
		model.addAttribute("category", category);

		List<Category> categories = categoryDao.getAllCategory();
		categoryDao.addCategory(category);
		return "redirect:/productform";
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String product(@ModelAttribute("product") Product product, Model model) {
		model.addAttribute("uploadfile", product);
		model.addAttribute("uploadfiles", productDao.getAllProduct());

		List<Product> products = productDao.getAllProduct();
		if(product.getProductId() != 0){
			productDao.Products(product);
			this.product = product;
			return "uploadfile";
		}
		this.product = productDao.addProduct(product);
		return "uploadfile";

		/*
		 * if (result.hasErrors()) { model.addAttribute("product", product);
		 * model.addAttribute("products", productDao.getAllProduct());
		 * List<Product> products = productDao.getAllProduct(); return
		 * "productform"; } productDao.addProduct(product); return
		 * "redirect:/productform";
		 */
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String uploadfile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
		System.out.println("------------------------ lusu1 ---------------------------");
		model.addAttribute("uploadfiles", productDao.getAllProduct());
		System.out.println("----------------lusu2----------------------");
		List<Product> products = productDao.getAllProduct();
		System.out.println("------------------lusu3---------------");

		System.out.println("-----------------lusu4--------------");
		String imagepath = request.getServletContext().getRealPath("/resources/images");
		System.out.println("----------------------lusu5----------");
		System.out.println("Directory:" + imagepath);
		System.out.println("-------------------lusu6-----------------");
		Path path = Paths.get(imagepath + File.separator + product.getProductId() + ".jpg");
		System.out.println("---------------------lusu7-------------------");
		File imageFile = new File(path.toString());
		System.out.println("-------------------------lusu8---------------------");
		System.out.println("Path:" + path.toString());
		System.out.println("----------------------------lusu9--------------------------");
		if (!imageFile.exists()) {
			imageFile.mkdirs();
			System.out.println("-------------------------lusu10-------------------------");
		}
		try {
			file.transferTo(imageFile);
			System.out.println("---------------------------lusu11--------------------");
			return "redirect:/productform";
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("--------------------lusu12--------------");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("------------------------------lusu13------------------------");
		}
		return "uploadfile";

	}

	@RequestMapping(value = "/editproduct/{productId}")
	public ModelAndView editProduct(@PathVariable("productId") int productId) {
		ModelAndView mv = new ModelAndView("Product");
		mv.addObject("product", productDao.getProductById(productId));
		List<Category> categories = categoryDao.getAllCategory();
		for(Category c : categories){
			
			System.out.println("Category : "+c.getName());
		}
		mv.addObject("categories", categories);
		mv.setViewName("productform");
		return mv;

	}

	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int productId) {
		productDao.delete(productId);
		return "redirect:/productform";

	}
	
}
