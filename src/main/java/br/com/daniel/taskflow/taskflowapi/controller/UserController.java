package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public User CreateUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
