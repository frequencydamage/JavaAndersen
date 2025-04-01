package org.novak.java.model.reservation;

import lombok.*;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceType;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Setter
    @OneToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;
}