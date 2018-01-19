package com.niit.dao;

import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.User;

public interface CartDao {

	public Cart getCartByUser(User user);
	public void addCartItem(Cart cart);
	public void removeCartItem(Cart cart,CartItem cartItem);
	public void deleteCart(Cart cart);
	public CartItem getCartItemById(int id);
}
