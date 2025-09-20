package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.service.UserService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDTO)  {
		UserResponseDTO createdUser = userService.create(userDTO);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		List<UserResponseDTO> users = userService.findAll();
		
        return ResponseEntity.ok(users);
	}
	
	@PutMapping("/{userID}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userID, @Valid @RequestBody UserRequestDTO userDTO) {
		UserResponseDTO updatedUser = userService.update(userID, userDTO);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userID}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userID) {
	    userService.delete(userID);
	    return ResponseEntity.noContent().build();
	}
}
