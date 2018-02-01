package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProductDao;
import com.niit.model.Product;

@Transactional
@Repository
public class ProductDaoimpl implements ProductDao {
	@Autowired
	SessionFactory sesionFactory;

	public Product addProduct(Product product) {
		Session session = sesionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		session.persist(product);
		transaction.commit();
		session.close();
		return product;

	}

	public void Products(Product product) {
		Session session = sesionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		session.saveOrUpdate(product);
		transaction.commit();
		session.close();

	}

	public Product getProductById(int productId) {
		Session session = sesionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		Product al = new Product();
		al = session.get(Product.class, productId);
		transaction.commit();
		session.close();
		return al;
	}

	public List<Product> getAllProduct() {
		Session session = sesionFactory.openSession();
		List<Product> bl = session.createQuery("from Product", Product.class).list();
		session.close();
		return bl;
	}

	public void delete(int id) {
		Session session = sesionFactory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		session.delete(getProductById(id));
		transaction.commit();
		session.close();

	}

}
