package com.example.done.sericeImpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.done.entity.User;
import com.example.done.repo.UserRepository;
import com.example.done.serice.IUserService;
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   User user=findByUsername(username);
   if(user==null) 
	   throw new UsernameNotFoundException("User not exist");
		
		return new org.springframework.security.core.userdetails.User
				(username,user.getPassword(),
						user.getRoles()
						.stream()
						.map(role->new SimpleGrantedAuthority(role))
						.collect(Collectors.toSet()))
				
				;
	}

	@Override
	public Integer saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user).getId();
	}

	@Override
	public User findByUsername(String username) {
      Optional<User> opt=  repo.findByUsername(username);
      if(opt.isPresent()) 
    	  return opt.get();
      else
		return null;
	}

}
