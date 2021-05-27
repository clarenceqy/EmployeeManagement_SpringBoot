package com.synir.paiban.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findUsersByUsernameAndPassword(String nickname, String password);
	Users findUsersByUsername(String nickname);

}
