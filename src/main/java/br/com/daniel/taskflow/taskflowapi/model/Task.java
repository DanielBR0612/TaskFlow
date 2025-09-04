package br.com.daniel.taskflow.taskflowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set; 
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O título é obrigatório")
	private String title;
	private String description;
	private Boolean completd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")     
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private User userOwner;
	 
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
	        name = "task_users", 
	        joinColumns = @JoinColumn(name = "task_id"), 
		    inverseJoinColumns = @JoinColumn(name = "user_id") 
	 )
	private Set<User> assignedUsers = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")     
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Project project;
	
}
