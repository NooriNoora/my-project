package com.niit.dao;

import java.util.List;

import com.niit.model.Category;

public interface CategoryDao
{
	public void addCategory(Category category);
	public List<Category> getAllCategory();
	public Category  getCategoryById(int  id);
	public  void delete(int id);

		
}
