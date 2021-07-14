package com.gnsofttr.todoproject.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gnsofttr.todoproject.dao.UserRepository;
import com.gnsofttr.todoproject.dto.UserRegistrationDto;
import com.gnsofttr.todoproject.model.Role;
import com.gnsofttr.todoproject.model.User;



@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(Role -> new SimpleGrantedAuthority(Role.getName())).collect(Collectors.toList());
	}

	
	@Secured(value = "ADMIN")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	
	@Secured(value = "ADMIN")
	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	@Secured(value = "ADMIN")
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return user;
	}

	@Secured(value = "ADMIN")
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

	@Secured(value = "ADMIN")
	public List<User> findAll() {

		return  userRepository.findAll();
	}


	  public Optional<User> getByEmail(String username) {
	        return userRepository.getByEmail(username);
	    }
	  
	  public Optional<String> getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            return Optional.of(authentication.getName());
	        }
	        return  Optional.empty();
	    }
}
