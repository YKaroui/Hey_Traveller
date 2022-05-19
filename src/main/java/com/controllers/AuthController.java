package com.controllers;

//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.entities.User;
//
//@RestController
//@RequestMapping("")
//public class AuthController {
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<?> attemptAuthentication(@RequestBody User user) throws Exception {
//		try {
//			Authentication auth = authenticationManager
//					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//			
//			Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//			String access_token = JWT.create().withSubject(auth.getName())
//					.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//					.withIssuedAt(new Date(System.currentTimeMillis()))
//					.withClaim("role",
//							auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//					.sign(algorithm);
//			String refresh_token = JWT.create().withSubject(auth.getName())
//					.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//					.withIssuedAt(new Date(System.currentTimeMillis())).sign(algorithm);
//
//			Map<String, String> tokens = new HashMap<>();
//			tokens.put("access_token", access_token);
//			tokens.put("refresh_token", refresh_token);
//			
//			return ResponseEntity.ok(tokens);
//		} catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}	
//	}
//}
