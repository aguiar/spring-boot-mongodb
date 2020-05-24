package com.borba.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borba.springbootmongo.domain.User;
import com.borba.springbootmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User usuario = findById(obj.getId());
		updateData(usuario, obj);
		return repo.save(usuario);
	}
	
	private void updateData(User usuario, User obj) {
		usuario.setName(obj.getName());
		usuario.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
