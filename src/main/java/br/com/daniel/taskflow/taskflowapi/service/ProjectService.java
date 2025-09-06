package br.com.daniel.taskflow.taskflowapi.service;

import br.com.daniel.taskflow.taskflowapi.controller.dto.ProjectCreateDTO;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import br.com.daniel.taskflow.taskflowapi.model.User;
import br.com.daniel.taskflow.taskflowapi.repository.ProjectRepository;
import br.com.daniel.taskflow.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Project create(ProjectCreateDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Usuário dono não encontrado!"));

        Set<User> members = new HashSet<>();
        if (dto.getMemberIds() != null && !dto.getMemberIds().isEmpty()) {
            members = new HashSet<>(userRepository.findAllById(dto.getMemberIds()));
        }

        Project newProject = new Project();
        newProject.setName(dto.getName());
        newProject.setUserOwner(owner);
        newProject.setUserMembers(members);

        return projectRepository.save(newProject);
    }
}