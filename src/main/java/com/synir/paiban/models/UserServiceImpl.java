package com.synir.paiban.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public void add(Users user) {
		repository.save(user);
		
	}

	@Override
	public List<Users> getList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return repository.findAll(PageRequest.of(0, 10, sort)).getContent();
	}

	@Override
	public Users findByUsernameAndPassword(String name, String password) {
		
		return repository.findUsersByUsernameAndPassword(name, password);
	}

	@Override
	public Users findByUsername(String name) {
		
		return repository.findUsersByUsername(name);
	}

}
