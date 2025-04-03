package org.novak.java.model.workspace;

import lombok.*;
import org.novak.java.model.reservation.Reservation;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "reservation")
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

    @Setter
    @OneToOne(mappedBy = "workspace", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Reservation reservation;
}