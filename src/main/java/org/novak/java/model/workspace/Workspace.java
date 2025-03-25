package org.novak.java.model.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Workspace implements Serializable {

    private Integer id;
    private Double price;
    private WorkspaceType workspaceType;
    private Boolean isAvailable;
}