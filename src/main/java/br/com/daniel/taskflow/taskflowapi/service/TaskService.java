package br.com.daniel.taskflow.taskflowapi.service;

import br.com.daniel.taskflow.taskflowapi.controller.dto.TaskRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.TaskResponseDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectResponseDTO;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import br.com.daniel.taskflow.taskflowapi.model.Task;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.ProjectRepository;
import br.com.daniel.taskflow.taskflowapi.repository.TaskRepository;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class TaskService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional
	public TaskResponseDTO create(TaskRequestDTO taskDTO) {
		
		Task newTask = new Task();
		
		populateTaskFromDTO(newTask, taskDTO);
		
		Task savedTask = taskRepository.save(newTask);
		
		return TaskResponseDTO.fromEntity(savedTask);
	}
	
	@Transactional(readOnly = true)
	public List<TaskResponseDTO> findAll() {
	    return taskRepository.findAllWithDetails().stream()
	            .map(task -> {
	            	UserResponseDTO creatorDTO = UserResponseDTO.asSummary(task.getCreator());

                    Set<UserResponseDTO> assignedUsersDTOs = task.getAssignedUsers().stream()
                            .map(UserResponseDTO::asSummary)
                            .collect(Collectors.toSet());
                    
                    ProjectResponseDTO projectDTO = ProjectResponseDTO.fromEntity(task.getProject());
                    
	                return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted(), creatorDTO, assignedUsersDTOs, projectDTO); 
	            })
	            .collect(Collectors.toList());
	}
	
	public void populateTaskFromDTO(Task task, TaskRequestDTO taskDTO) {
		User creator = userRepository.findById(taskDTO.getCreatorId())
	            .orElseThrow(() -> new RuntimeException("Usuário criador não encontrado!"));
		
		Set<User> assignedMembers = new HashSet<>();
		if (taskDTO.getAssignedUsersIds() != null && !taskDTO.getAssignedUsersIds().isEmpty()) {
            assignedMembers = new HashSet<>(userRepository.findAllById(taskDTO.getAssignedUsersIds()));
        }
		
		Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
		
		if (taskDTO.getCompleted() != null) {
	        task.setCompleted(taskDTO.getCompleted());
	    } else if (task.getId() == null) { 
	        task.setCompleted(false);
	    }

		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setCreator(creator);
		task.setAssignedUsers(assignedMembers);
		task.setProject(project);
	}
	
	@Transactional
	public TaskResponseDTO update(Long taskId, TaskRequestDTO taskDTO) {
		Task updatedTask = taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("A task não foi encontrada"));
		
		populateTaskFromDTO(updatedTask, taskDTO);
		
		Task savedTask = taskRepository.save(updatedTask);
		
		return TaskResponseDTO.fromEntity(savedTask);
	}
	
	@Transactional
	public void delete(Long taskID) {
		Task task = taskRepository.findById(taskID) 
	            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + taskID));
	    
	    Project project = task.getProject();
	    if (project != null) {
	        project.getTasks().remove(task);
	    }

	    for (User user : new HashSet<>(task.getAssignedUsers())) {
	        user.getTasks().remove(task); 
	    }
	    
	    taskRepository.deleteById(taskID);
	}
}
