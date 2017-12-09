package com.niit.dao;
import java.util.List;
import com.niit.model.Product;
public interface ProductDao {
    
    public Product addProduct(Product product);
    public void Products(Product product);
    public Product getProductById(int id); 
    public List<Product> getAllProduct();
    public void delete(int id);
    
}
