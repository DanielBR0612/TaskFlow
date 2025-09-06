package br.com.daniel.taskflow.taskflowapi.service;

import br.com.daniel.taskflow.taskflowapi.controller.dto.UserCreateDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserResponseDTO create(UserCreateDTO userDTO) {
		
		User newUser = new User();
		
		newUser.getUserName();
		newUser.getPassword();
		newUser.getMail();
		
		User savedUser = userRepository.save(newUser);
		
		return new UserResponseDTO(savedUser.getId(), savedUser.getUserName(), savedUser.getMail());
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getUserName(), user.getMail()))
            .collect(Collectors.toList());
    }
}