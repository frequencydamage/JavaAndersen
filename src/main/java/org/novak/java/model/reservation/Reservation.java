package org.novak.java.model.reservation;

import lombok.*;
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

    @Column(name = "workspace_id", nullable = false)
    private Integer worksSpaceId;

    @Id
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "workspace_type", nullable = false)
    private WorkspaceType workspaceType;
}