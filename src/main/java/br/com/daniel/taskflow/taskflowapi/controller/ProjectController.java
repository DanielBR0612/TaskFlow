package br.com.daniel.taskflow.taskflowapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectResponseDTO;
import br.com.daniel.taskflow.taskflowapi.service.ProjectService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectDTO) {
        ProjectResponseDTO createdProject = projectService.create(projectDTO);
        
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
	
	@GetMapping
	public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
		List<ProjectResponseDTO> projects = projectService.findAll();
		return ResponseEntity.ok(projects);
	}
}