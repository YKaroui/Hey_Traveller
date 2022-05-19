package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Utils.UserConverter;
import com.Utils.UserDTO;
import com.entities.User;
import com.repositories.UserRepository;
import com.services.Implementations.UserService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserConverter userConverter;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			 {
		User employee = userRepository.findById(employeeId)
				.get();
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		User employee = userRepository.findById(employeeId)
				.get();

		userService.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@Transactional
	@PutMapping("/update1/{id}")
	public void update1(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) {
			User user = userConverter.dtoToEntity(userDTO);
		 userRepository.updateById(user.getUsername(), user.getEmail(), id);
	}
	
	@Transactional
	@PutMapping("/update")
	public void update(@RequestBody UserDTO userDTO) {
		User user = userConverter.dtoToEntity(userDTO);

		User userRetrieved = userRepository.findById(user.getId()).get();
		
		userRetrieved.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		log.info("{}", user.getEnabled());
		userRetrieved.setEmail(user.getEmail());
		log.info("{}", userRetrieved.getRole());
		userRetrieved.setUsername(user.getUsername());
		log.info("{}", user.getEnabled());

		userRepository.save(userRetrieved);
	}
	

	@DeleteMapping("/delete")
	public String delete(@RequestBody User user) {
		return userService.delete(user);
	}

	@GetMapping("/retrieveByUsername")
	public User retrieveByUsername(@RequestBody UsernameEmail usernameEmail) {
		return userService.retrieveByUsername(usernameEmail.getUsername());
	}

	@GetMapping("/retrieveByEmail")
	public User retrieveByEmail(@RequestBody UsernameEmail usernameEmail) {
		return userService.retrieveByEmail(usernameEmail.getEmail());
	}

	@GetMapping("/retrieveAll")
	public List<User> retrieveAll() {
		return userService.retrieveAll();
	}

	@GetMapping("/loadUserByUsername")
	public UserDetails loadUserByUsername(String username) {
		return userService.loadUserByUsername(username);
	}
	
	
	@PutMapping("/lockUser/{id}")
	public String lockUser(@PathVariable(value = "id") Long id) {
		return userService.lockUser(id);
	}
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UsernameEmail {
	String username;
	String email;
}
