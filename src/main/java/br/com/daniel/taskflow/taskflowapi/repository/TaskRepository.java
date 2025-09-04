package br.com.daniel.taskflow.taskflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.daniel.taskflow.taskflowapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
