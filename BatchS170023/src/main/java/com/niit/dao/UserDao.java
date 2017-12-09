package com.niit.dao;




	import java.util.List;

import com.niit.model.User;

	



	public interface UserDao {
		public void addUser(User user);
		public void upUser(User user);
		public User getUserById(int id);
		public List getAlluser();
		

		

	}


