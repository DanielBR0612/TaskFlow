package br.com.daniel.taskflow.taskflowapi.controller.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequestDTO {
	
	@NotBlank(message = "O título não pode estar em branco")
	private String title;
	
	private String description;
	
	@NotNull
	private Long creatorId;
	
	private Boolean completed;
	
	@NotNull
	private Set<Long> assignedUsersIds;
	
	@NotNull(message = "A task deve pertencer a um projeto")
	private Long projectId;
}
