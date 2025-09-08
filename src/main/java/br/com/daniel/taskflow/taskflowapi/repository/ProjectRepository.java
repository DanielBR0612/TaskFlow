package br.com.daniel.taskflow.taskflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.userOwner LEFT JOIN FETCH p.userMembers")
    	List<Project> findAllWithDetails();
}
