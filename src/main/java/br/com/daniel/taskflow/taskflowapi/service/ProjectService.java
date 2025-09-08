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
    public Project create(ProjectRequestDTO projectDTO) {
        User owner = userRepository.findById(projectDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Usuário dono não encontrado!"));

        Set<User> members = new HashSet<>();
        if (projectDTO.getMemberIds() != null && !projectDTO.getMemberIds().isEmpty()) {
            members = new HashSet<>(userRepository.findAllById(projectDTO.getMemberIds()));
        }

        Project newProject = new Project();
        
        newProject.setName(projectDTO.getName());
        newProject.setUserOwner(owner);
        newProject.setUserMembers(members);

        return projectRepository.save(newProject);
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

}