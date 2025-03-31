package org.novak.java.model.workspace;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "workspace_type", nullable = false)
    private WorkspaceType workspaceType;

    @Setter
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;
}