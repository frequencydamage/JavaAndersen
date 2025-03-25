package org.novak.java.model.workspace;

import lombok.Data;

import java.io.Serializable;

@Data
public class Workspace implements Serializable {

    private Integer id;
    private Double price;
    private WorkspaceType workspaceType;
    private Boolean isAvailable;

    public Workspace(Integer id, Double price, WorkspaceType workspaceType, Boolean isAvailable) {
        this.id = id;
        this.price = price;
        this.workspaceType = workspaceType;
        this.isAvailable = isAvailable;
    }
}