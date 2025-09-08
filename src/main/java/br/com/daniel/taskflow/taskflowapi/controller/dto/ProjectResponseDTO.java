package br.com.daniel.taskflow.taskflowapi.controller.dto;

import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import br.com.daniel.taskflow.taskflowapi.model.Project;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponseDTO {
	private Long id;
	private String name;
	private UserResponseDTO owner;
	private Set<UserResponseDTO> members;
	
	public static ProjectResponseDTO fromEntity(Project project) {
		if (project == null) {
			return null;
		}
		
		UserResponseDTO ownerDto = UserResponseDTO.asSummary(project.getUserOwner());

	    Set<UserResponseDTO> memberDtos = project.getUserMembers().stream()
	            .map(user -> UserResponseDTO.asSummary(user))
	            .collect(Collectors.toSet());
	    
	    
		return new ProjectResponseDTO(project.getId(), project.getName(), ownerDto, memberDtos);
	}
	
	public static ProjectResponseDTO asSummary(Project project) {
		if (project == null) {
			return null;
		}
		return new ProjectResponseDTO(project.getId(), project.getName(), null, null);
	}
}
