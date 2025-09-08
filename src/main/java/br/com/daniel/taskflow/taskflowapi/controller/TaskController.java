package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.model.Task;
import br.com.daniel.taskflow.taskflowapi.service.TaskService;
import br.com.daniel.taskflow.taskflowapi.controller.dto.TaskRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
		
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping
	public ResponseEntity<Task> CreateTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
		Task createdTask = taskService.create(taskRequestDTO);
		
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
		List<TaskResponseDTO> tasks = taskService.findAll();
		
		return ResponseEntity.ok(tasks);
	}
}
