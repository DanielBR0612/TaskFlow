package br.com.daniel.taskflow.taskflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.daniel.taskflow.taskflowapi.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
