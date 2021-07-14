package com.gnsofttr.todoproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gnsofttr.todoproject.dto.UserRegistrationDto;
import com.gnsofttr.todoproject.model.User;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(long id);

	void deleteUserById(long id);

	Optional<User> getByEmail(String string);

	Optional<String> getCurrentUser();

	

	

}