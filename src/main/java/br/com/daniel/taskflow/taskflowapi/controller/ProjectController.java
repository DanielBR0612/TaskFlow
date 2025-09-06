package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import br.com.daniel.taskflow.taskflowapi.repository.ProjectRepository;
import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectCreateDTO;
import br.com.daniel.taskflow.taskflowapi.service.ProjectService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectService projectService;
	
    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectCreateDTO projectDTO) {
        Project createdProject = projectService.create(projectDTO);
        
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
	
	@GetMapping
	public List<Project> getAllUsers() {
		return projectRepository.findAll();
	}
}