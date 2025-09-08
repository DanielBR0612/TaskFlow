package br.com.daniel.taskflow.taskflowapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Data;

@Data
public class ProjectRequestDTO {
	@NotBlank(message = "O nome do projeto é obrigatório")
    private String name;

    @NotNull(message = "O ID do dono do projeto é obrigatório")
    private Long ownerId;

    private Set<Long> memberIds;
}
