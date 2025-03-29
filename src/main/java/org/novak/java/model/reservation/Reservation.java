package org.novak.java.model.reservation;

import lombok.*;
import org.novak.java.model.workspace.WorkspaceType;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Reservation {

    private Integer worksSpaceId;
    private Integer reservationId;
    private WorkspaceType workspaceType;
}