package com.niit.userController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/addtocart/{id}")
	public String addToCart(@PathVariable("id")int id, Model model, Principal principal) {

		User user = userDao.getUserByUsername(principal.getName());
		Product product = productDao.getProductById(id);

		boolean found = false;

		List<CartItem> cartItems;
		CartItem cartItem;
		Cart cart;
		if ((cart = cartDao.getCartByUser(user)) == null) {
			cart = new Cart();
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			cartItem.setSubTotal(product.getCost());

			cartItems = new ArrayList<CartItem>();
			cartItems.add(cartItem);

			cart.setCartItems(cartItems);
			cart.setUser(user);
			cart.setGrandTotal(cartItem.getSubTotal());

			cartDao.addCartItem(cart);
			return "redirect:/cart";

		} else {
			for (CartItem ca : cart.getCartItems()) {
				if (ca.getProduct().getProductId() == id) {
					ca.setQuantity(ca.getQuantity() + 1);
					ca.setSubTotal(ca.getSubTotal() + product.getCost());
					cart.setGrandTotal(cart.getGrandTotal() + ca.getProduct().getCost());
					found = true;
				}
			}

			if (!found) {
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setQuantity(1);
				cartItem.setSubTotal(product.getCost());

				cart.getCartItems().add(cartItem);
				cart.setGrandTotal(cart.getGrandTotal() + cartItem.getSubTotal());

			}

			cartDao.addCartItem(cart);
		}
		model.addAttribute("cart", cart);
		return "cart";
	}

	@RequestMapping(value = { "/checkout" })
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
		return "redirect:/user/cart";
	}

	@RequestMapping("/cart")
	public String getCart(Principal principal, Model model) {

		User user = userDao.getUserByUsername(principal.getName());
		Cart cart = cartDao.getCartByUser(user);
		model.addAttribute("cart", cart);
		return "cart";
	}

}
