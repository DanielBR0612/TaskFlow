package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserCreateDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.service.UserService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserRepository userRepository;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> CreateUser(@Valid @RequestBody UserCreateDTO userDTO)  {
		UserResponseDTO createdUser = userService.create(userDTO);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
	}
}
