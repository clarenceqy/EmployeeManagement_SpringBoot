package com.synir.paiban.models;

import java.util.List;

public interface UserService {
	public void add(Users user);
	public List<Users> getList();
	Users findByUsernameAndPassword(String name, String password);
    Users findByUsername(String name);
}
