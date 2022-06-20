package com.example.done.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.done.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

}
