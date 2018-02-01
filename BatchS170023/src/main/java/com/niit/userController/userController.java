package com.niit.userController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.ProductDao;
import com.niit.dao.UserDao;
import com.niit.model.User;

@Controller
public class userController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value={"/login"}, method={RequestMethod.GET,RequestMethod.POST})
	public String login(Model model) {
		model.addAttribute("User", new User());
		return "login";
	}
	
	@RequestMapping(value={"/logout"}, method={RequestMethod.GET})
	public String logout(Model model) {
		//model.addAttribute("User", new User());
		return "index";
	}


	@RequestMapping("/signin")
	public String signin(@ModelAttribute("User") User user, Model model) {
		model.addAttribute("User", new User());
		model.addAttribute("Users", userDao.getAlluser());
		return "signin";
	}
	@RequestMapping(value={"/cart"},method={RequestMethod.GET})
	public String cart(Model model){
		return "cart";
	}

	@RequestMapping("/User")
	public String User(@ModelAttribute("User") User user, Model model) {

		System.out.println("username" + user.getUsername());
		System.out.println("email" + user.getEmail());
		System.out.println("password" + user.getPassword());
		System.out.println("confirmpassword" + user.getConfirmpassword());
		model.addAttribute("User", new User());
		model.addAttribute("Users", userDao.getAlluser());
		return "User";

	}

	@RequestMapping("/up")
	public String getUserpage(@ModelAttribute("User") User user, Model model) {
		model.addAttribute("User", new User());
		model.addAttribute("Users", userDao.getAlluser());
		return "redirect:/User";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String getpage(@ModelAttribute("User") @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("User", user);
			model.addAttribute("Users", userDao.getAlluser());
			List<User> Users = userDao.getAlluser();
			return "signin";

		}
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		userDao.addUser(user);

		return "redirect:login";
	}
}
