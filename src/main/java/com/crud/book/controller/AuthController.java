package com.crud.book.controller;



import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.book.dto.JwtResponse;
import com.crud.book.dto.LoginRequest;
import com.crud.book.dto.MessageResponse;
import com.crud.book.dto.SignupRequest;
import com.crud.book.model.ERole;
import com.crud.book.model.RoleEntity;
import com.crud.book.model.UserEntity;
import com.crud.book.repository.RoleRepository;
import com.crud.book.repository.UserRepository;
import com.crud.book.security.jwt.JwtUtils;
import com.crud.book.security.service.UserDetailsImpl;

//cara 1
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

//cara 2
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;


@CrossOrigin(origins = "*", maxAge = 3608)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
//	cara 1
//    private static final Logger logger = LogManager.getLogger(AuthController.class);
    
//    cara 2
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


	AuthenticationManager authenticationManager;


	RoleRepository roleRepository;


	UserRepository userRepository;


	PasswordEncoder encoder;


	JwtUtils jwtUtils;


	@Autowired
	public AuthController(AuthenticationManager authenticationManager, RoleRepository roleRepository,
			UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {

		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String jwt = jwtUtils.generateJwtToken(authentication);
		
		System.out.println("Debug "+jwt);
		System.out.println("Debug "+authentication);
		logger.debug("Debug  ={}", jwt);
		logger.debug("Debug  ={}", authentication);
		logger.debug("Debug  ={}", loginRequest);
		
		

		final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		final List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Username is already taken !"));
		}

		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: Email is already in use!"));
		}

		UserEntity user = new UserEntity(
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles() ;
		Set<RoleEntity> roles = new HashSet<>();


		if(strRoles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;
				case "mod":
					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(modRole);
					break;

				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);

				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully"));


	}


}
