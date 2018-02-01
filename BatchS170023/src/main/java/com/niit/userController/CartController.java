package com.niit.userController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartDao;
import com.niit.dao.ProductDao;
import com.niit.dao.UserDao;
import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.Product;
import com.niit.model.User;

@RequestMapping("/user")
@Controller
public class CartController {

	@Autowired
	UserDao userDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDao productDao;

	@RequestMapping("/cart")
	public String getCart(Principal principal, Model model) {

		User user = userDao.getUserByUsername(principal.getName());
		Cart cart = cartDao.getCartByUser(user);
		model.addAttribute("cart", cart);
		return "cart";
	}

	@RequestMapping(value = { "/addtocart" })
	public String addToCart(@RequestParam("id") int productId, Principal principal, Model model) {
		System.out.println("------------------------ lusu1 ---------------------------");
		User user = userDao.getUserByUsername(principal.getName());
		System.out.println("------------------------ lusu2 ---------------------------");
		Product product = productDao.getProductById(productId);
		System.out.println("------------------------ lusu3 ---------------------------");
        Cart cart=user.getCart();
        System.out.println("------------------------ lusu4 ---------------------------");
		boolean found = false;
		System.out.println("------------------------ lusu5 ---------------------------");
		List<CartItem> cartItems;
		System.out.println("------------------------ lusu6 ---------------------------");
		CartItem cartItem;
		System.out.println("------------------------ lusu7 ---------------------------");
		
		if ((cart = cartDao.getCartByUser(user)) == null) {
			System.out.println("------------------------ lusu8 ---------------------------");
			cart = new Cart();
			System.out.println("------------------------ lusu9 ---------------------------");
			cartItem = new CartItem();
			System.out.println("------------------------ lusu10 ---------------------------");
			cartItem.setProduct(product);
			System.out.println("------------------------ lusu11 ---------------------------");
			cartItem.setQuantity(1);
			System.out.println("------------------------ lusu12 ---------------------------");
			cartItem.setSubTotal(product.getCost());
			System.out.println("------------------------ lusu13 ---------------------------");

			cartItems = new ArrayList<CartItem>();
			System.out.println("------------------------ lusu14 ---------------------------");
			cartItems.add(cartItem);
			System.out.println("------------------------ lusu15 ---------------------------");
			cart.setCartItems(cartItems);
			System.out.println("------------------------ lusu16 ---------------------------");
			cart.setUser(user);
			System.out.println("------------------------ lusu17 ---------------------------");
			cart.setGrandTotal(cartItem.getSubTotal());
			System.out.println("------------------------ lusu18 ---------------------------");

			System.out.println(cart.toString());
			user.setCart(cart);
			userDao.upUser(user);
			/*cartDao.addCartItem(cart);*/
			System.out.println("------------------------ lusu19 ---------------------------");
			return "redirect:/cart";
			

		} else {
			System.out.println("------------------------ lusu20 ---------------------------");
			for (CartItem ca : cart.getCartItems()) {
				System.out.println("------------------------ lusu21 ---------------------------");
				if (ca.getProduct().getProductId() == productId) {
					System.out.println("------------------------ lusu22 ---------------------------");
					ca.setQuantity(ca.getQuantity() + 1);
					System.out.println("------------------------ lusu23 ---------------------------");
					ca.setSubTotal(ca.getSubTotal() + product.getCost());
					System.out.println("------------------------ lusu24 ---------------------------");
					cart.setGrandTotal(cart.getGrandTotal() + ca.getProduct().getCost());
					System.out.println("------------------------ lusu25 ---------------------------");
					found = true;
					System.out.println("------------------------ lusu26 ---------------------------");
				}
			}

			if (!found) {
				System.out.println("------------------------ lusu27 ---------------------------");
				cartItem = new CartItem();
				System.out.println("------------------------ lusu28 ---------------------------");
				cartItem.setProduct(product);
				System.out.println("------------------------ lusu29 ---------------------------");
				cartItem.setQuantity(1);
				System.out.println("------------------------ lusu30 ---------------------------");
				cartItem.setSubTotal(product.getCost());
				System.out.println("------------------------ lusu31 ---------------------------");
				cart.getCartItems().add(cartItem);
				System.out.println("------------------------ lusu32 ---------------------------");
				cart.setGrandTotal(cart.getGrandTotal() + cartItem.getSubTotal());
				System.out.println("------------------------ lusu33 ---------------------------");

			}
			System.out.println("------------------------ lusu34 ---------------------------");
			cartDao.addCartItem(cart);
			System.out.println("------------------------ lusu35 ---------------------------");
		}
		System.out.println("------------------------ lusu36 ---------------------------");
		model.addAttribute("cart", cart);
		System.out.println("------------------------ lusu37 ---------------------------");
		return "cart";
	}

	@RequestMapping(value = {"/checkout"})
	public String getpage() {
		return "thankyou";
	}

	@RequestMapping("/delete/cartItem/{id}")
	public String deleteCartItem(@PathVariable int id, Principal principal, Model model) {

		CartItem cartItem = cartDao.getCartItemById(id);
		User user = userDao.getUserByUsername(principal.getName());
		Cart cart = cartDao.getCartByUser(user);
		cartDao.removeCartItem(cart, cartItem);
		model.addAttribute("cart", cart);
		return "redirect:/cart";
	}

}
