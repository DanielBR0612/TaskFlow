package br.com.daniel.taskflow.taskflowapi.service;

import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectRequestDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectResponseDTO;
import br.com.daniel.taskflow.taskflowapi.controller.dto.UserResponseDTO;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.ProjectRepository;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ProjectResponseDTO create(ProjectRequestDTO projectDTO) {
        Project newProject = new Project();
        
        populateProjectFromDTO(newProject, projectDTO);
        
        Project savedProject =  projectRepository.save(newProject);

        return ProjectResponseDTO.fromEntity(savedProject);
    }
    
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAllWithDetails().stream()
                .map(project -> {
                    UserResponseDTO ownerDTO = UserResponseDTO.asSummary(project.getUserOwner());

                    Set<UserResponseDTO> memberDTOs = project.getUserMembers().stream()
                            .map(UserResponseDTO::asSummary)
                            .collect(Collectors.toSet());

                    return new ProjectResponseDTO(project.getId(), project.getName(), ownerDTO, memberDTOs);
                })
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ProjectResponseDTO update(Long projectId, ProjectRequestDTO projectDTO) {
    	Project updatedProject = projectRepository.findById(projectId)
    			.orElseThrow(() -> new RuntimeException("O projeto não foi encontrado"));
    	
    	populateProjectFromDTO(updatedProject, projectDTO);
    	
    	Project savedProject = projectRepository.save(updatedProject);
    	
    	return ProjectResponseDTO.fromEntity(savedProject);
    }
    
    private void populateProjectFromDTO(Project project, ProjectRequestDTO dto) {

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Usuário dono não encontrado!"));
        
        Set<User> members = new HashSet<>();
        if (dto.getMemberIds() != null && !dto.getMemberIds().isEmpty()) {
            members = new HashSet<>(userRepository.findAllById(dto.getMemberIds()));
        }


        project.setName(dto.getName());
        project.setUserOwner(owner);
        project.setUserMembers(members);
    }

}