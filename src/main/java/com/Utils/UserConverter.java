package com.Utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.entities.User;



@Component
public class UserConverter {

	public UserDTO entityToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		return dto;
	}

	public List<UserDTO> entitiesToDTOS(List<User> users) {
		return users.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
	}

	public User dtoToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		return user;
	}

	public List<User> dtosToEntities(List<UserDTO> usersDTO) {
		return usersDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
