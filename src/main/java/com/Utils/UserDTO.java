package com.Utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
	Long id;
	@NotBlank(message = "Username required")
	String username;
	@NotBlank(message = "Email required")
	@Email
	String email;
	@NotBlank(message = "Password required")
	@Size(min = 8, max = 60, message = "password must have 8 to 20 caracters.")
	String password;
	
}
