package com.niit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.CartDao;
import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.User;

@Transactional
@Repository("cartDao")
public class CartDaoimpl implements CartDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addCartItem(Cart cart) {

		sessionFactory.getCurrentSession().saveOrUpdate(cart);

	}

	@Override
	public void removeCartItem(Cart cart, CartItem cartItem) {
		int count = 0;
		for(CartItem car : cart.getCartItems()){
			if (car.getId() == cartItem.getId()) {
				System.out.println("----------------------------------------------------- found");
			}else{
				count++;
			}
		}
		List<CartItem> cis = cart.getCartItems();
		
		cart.setGrandTotal(cart.getGrandTotal() - cartItem.getSubTotal());
		cis.remove(count);
		cart.setCartItems(cis);
		addCartItem(cart);

	}

	@Override
	public void deleteCart(Cart cart) {
		
		sessionFactory.getCurrentSession().delete(cart);

	}

	@Override
	public Cart getCartByUser(User user) {
		
		try{
			return sessionFactory.getCurrentSession().createQuery("FROM Cart WHERE USER_ID = '"+user.getId()+"'", Cart.class).getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public CartItem getCartItemById(int id) {
		
		return sessionFactory.getCurrentSession().get(CartItem.class, id);
	}

}
