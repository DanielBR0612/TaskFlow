package br.com.daniel.taskflow.taskflowapi.service;

import br.com.daniel.taskflow.taskflowapi.controller.dto.UserRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.ProjectRepository;
import br.com.daniel.taskflow.taskflowapi.repository.TaskRepository;
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
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Transactional
	public UserResponseDTO create(UserRequestDTO userDTO) {
		
		User newUser = new User();
		
		newUser.setUserName(userDTO.getUserName());
		newUser.setPassword(userDTO.getPassword());
		newUser.setMail(userDTO.getMail());
		
		User savedUser = userRepository.save(newUser);
		
		return new UserResponseDTO(savedUser.getId(), savedUser.getUserName(), savedUser.getMail());
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getUserName(), user.getMail()))
            .collect(Collectors.toList());
    }
	
	@Transactional
	public UserResponseDTO update(Long userId, UserRequestDTO userDTO) {
		User updatedUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		
		updatedUser.setUserName(userDTO.getUserName());
		updatedUser.setPassword(userDTO.getPassword());
		updatedUser.setMail(userDTO.getMail());
		
		User savedUser = userRepository.save(updatedUser);
		
		return UserResponseDTO.fromEntity(savedUser);
	}
	
	@Transactional
	public void delete(Long id) {
	    if (!userRepository.existsById(id)) {
	        throw new RuntimeException("Usuário não encontrado com o id: " + id);
	    }

		if (projectRepository.existsByUserOwnerId(id) || taskRepository.existsByCreatorId(id)) {
	        throw new IllegalStateException("Não é possível deletar o usuário pois ele é dono de projetos ou tarefas existentes.");
	    }
	    
	    userRepository.deleteById(id);
	}
}