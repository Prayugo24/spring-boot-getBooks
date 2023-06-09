package com.crud.book.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.book.model.RoleEntity;
import com.crud.book.model.UserEntity;
import com.crud.book.repository.RoleRepository;
import com.crud.book.repository.UserRepository;
import com.crud.book.security.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestAuthController {
	
	UserRepository userRepository;
	
	
	@Autowired
	public TestAuthController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hashRole('ADMIN')")
	public String userAccess(@AuthenticationPrincipal UserDetailsImpl userDetails) {
	    return "User Content: " + userDetails.getUsername();
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		Optional<UserEntity> find = userRepository.findById(userDetails.getId());
		if (find.isPresent()) {
	        UserEntity exist = find.get();
	        Set<RoleEntity> roles = exist.getRoles();
	        for (RoleEntity role : roles) {
	            if (role.getName().equals("ROLE_MODERATOR")) {
	                return "Moderator Board: " + userDetails.getUsername();
	            }
	        }
	    }
	    return "Unauthorized access.";
		
	}

}
