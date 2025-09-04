package br.com.daniel.taskflow.taskflowapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	private String mail;
	
    @ManyToMany(mappedBy = "assignedUsers") 
    @JsonIgnore 
    private Set<Task> tasks = new HashSet<>();
    
    @ManyToMany(mappedBy = "userMembers") 
    @JsonIgnore 
    private Set<Project> projects = new HashSet<>();
}
