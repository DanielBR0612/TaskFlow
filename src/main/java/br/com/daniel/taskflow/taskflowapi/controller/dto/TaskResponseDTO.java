package br.com.daniel.taskflow.taskflowapi.controller.dto;

import lombok.Data;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
	private Long id;
	private String title;
	private String desecription;
	private Boolean completed;
	private UserResponseDTO creator;
	private Set<UserResponseDTO> members;
	private ProjectResponseDTO project;
}
