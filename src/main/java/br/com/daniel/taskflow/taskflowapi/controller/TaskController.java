package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.model.Task;
import br.com.daniel.taskflow.taskflowapi.repository.TaskRepository;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping
	public Task CreateTask(@Valid @RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@GetMapping
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
}
