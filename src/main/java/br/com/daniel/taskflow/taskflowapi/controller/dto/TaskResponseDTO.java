package br.com.daniel.taskflow.taskflowapi.controller.dto;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import br.com.daniel.taskflow.taskflowapi.model.Task;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponseDTO {
	private Long id;
	private String title;
	private String description;
	private Boolean completed;
	private UserResponseDTO creator;
	private Set<UserResponseDTO> assignedUsers;
	private ProjectResponseDTO project;
	
	public static TaskResponseDTO fromEntity(Task task) {
		
		if (task == null) {
			return null;
		}
		
		UserResponseDTO creatorDto = UserResponseDTO.asSummary(task.getCreator());

	    Set<UserResponseDTO> assignedUsersDtos = task.getAssignedUsers().stream()
	            .map(user -> UserResponseDTO.asSummary(user))
	            .collect(Collectors.toSet());
	    
	    ProjectResponseDTO project = ProjectResponseDTO.asSummary(task.getProject());
	    
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted(), creatorDto, assignedUsersDtos, project);
    }

    public static TaskResponseDTO asSummary(Task task) {
    	if (task == null) {
            return null;
        }
    	
        return new TaskResponseDTO(task.getId(), task.getTitle(), null, null, null, null, null);
    }
}
