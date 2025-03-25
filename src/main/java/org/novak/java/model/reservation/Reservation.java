package org.novak.java.model.reservation;

import lombok.*;
import org.novak.java.model.workspace.WorkspaceType;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Reservation implements Serializable {

    private Integer worksSpaceId;
    private Integer reservationId;
    private WorkspaceType workspaceType;
}