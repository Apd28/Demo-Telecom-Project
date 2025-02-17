package com.internal.dbapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.internal.dbapi.dto.UserDTO;
import com.internal.dbapi.model.User;

@Service
public interface UserService {

	List<UserDTO> getAllUsers();
	
	User getUserByUsername(String username);
	
	Optional<Integer> validateUser(String username, String password);
	
}
