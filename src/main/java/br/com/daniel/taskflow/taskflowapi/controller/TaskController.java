package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
		TaskResponseDTO createdTask = taskService.create(taskRequestDTO);
		
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
		List<TaskResponseDTO> tasks = taskService.findAll();
		
		return ResponseEntity.ok(tasks);
	}
	
	@PutMapping("/{taskID}")
	public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long taskID, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
		TaskResponseDTO updatedTask = taskService.update(taskID, taskRequestDTO);
		
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{taskID}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long taskID) {
	    taskService.delete(taskID);
	    return ResponseEntity.noContent().build();
	}
}
