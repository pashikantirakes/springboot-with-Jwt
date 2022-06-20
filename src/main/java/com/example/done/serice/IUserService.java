package com.example.done.serice;

import com.example.done.entity.User;

public interface IUserService {
   
	 Integer saveUser(User user);
	 User findByUsername(String username);
}
