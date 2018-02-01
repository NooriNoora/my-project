package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
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
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(cart);
		session.close();
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
		Session session = sessionFactory.openSession();
		session.delete(cart);
		session.close();

	}

	@Override
	public Cart getCartByUser(User user) {
		
		try{
			Session session = sessionFactory.openSession();
			Cart a2=new Cart();
			a2=session.createQuery("FROM Cart WHERE USER_ID = '"+user.getId()+"'", Cart.class).getSingleResult();
			session.close();
			return a2;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public CartItem getCartItemById(int id) {
		Session session = sessionFactory.openSession();
		CartItem a3=new CartItem();
		a3=session.get(CartItem.class, id);
		session.close();
		return a3;
	}

}
