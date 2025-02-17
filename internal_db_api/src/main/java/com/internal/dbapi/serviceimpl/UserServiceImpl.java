package com.internal.dbapi.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internal.dbapi.dto.UserDTO;
import com.internal.dbapi.model.User;
import com.internal.dbapi.repository.UserRepository;
import com.internal.dbapi.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;

	@Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getAccountId(), user.getUsername()))
                .collect(Collectors.toList());
    }

	@Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	@Override
	public Optional<Integer> validateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(UserDTO::getAccountId);
    }
}
