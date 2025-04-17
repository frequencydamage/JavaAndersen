package org.novak.java.dto.workspaceDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.novak.java.model.workspace.WorkspaceType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceRequestDTO {

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "1499.99", message = "Price must be less than 1500")
    private Double price;

    @NotNull(message = "Workspace type must not be null")
    private WorkspaceType workspaceType;
}
