package br.com.daniel.taskflow.taskflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.daniel.taskflow.taskflowapi.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
