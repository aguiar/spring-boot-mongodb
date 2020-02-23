package com.borba.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borba.springbootmongo.domain.User;
import com.borba.springbootmongo.repository.UserRepository;
import com.borba.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
//		if (user == null) {
//			throw new ObjectNotFoundException("Objeto não encontrado");
//		}
//		return user;
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
