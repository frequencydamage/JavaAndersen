package org.novak.java.model.workspace;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Workspace {

    private Integer id;
    private Double price;
    private WorkspaceType workspaceType;
    @Setter
    private Boolean isAvailable;
}