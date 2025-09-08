package br.com.daniel.taskflow.taskflowapi.controller.dto;

import br.com.daniel.taskflow.taskflowapi.model.User;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
	private Long id;
	private String userName;
	private String mail;
	
	public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(user.getId(), user.getUserName(), user.getMail());
    }

    public static UserResponseDTO asSummary(User user) {
    	if (user == null) {
            return null;
        }
    	
        return new UserResponseDTO(user.getId(), user.getUserName(), null);
    }
}
