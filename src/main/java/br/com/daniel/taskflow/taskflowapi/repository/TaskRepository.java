package br.com.daniel.taskflow.taskflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.daniel.taskflow.taskflowapi.model.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	@Query("SELECT DISTINCT t FROM Task t " + "LEFT JOIN FETCH t.creator " + "LEFT JOIN FETCH t.project " + "LEFT JOIN FETCH t.assignedUsers")
	    List<Task> findAllWithDetails();
}
