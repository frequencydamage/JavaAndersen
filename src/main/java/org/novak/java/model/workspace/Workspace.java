package org.novak.java.model.workspace;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Workspace implements Serializable {

    private Integer id;
    private Double price;
    private WorkspaceType workspaceType;
    @Setter
    private Boolean isAvailable;
}