package org.novak.java.model.reservation;

import lombok.Data;
import org.novak.java.model.workspace.WorkspaceType;

import java.io.Serializable;

@Data
public class Reservation implements Serializable {

    private Integer worksSpaceId;
    private Integer reservationId;
    private WorkspaceType workspaceType;

    public Reservation(Integer worksSpaceId, WorkspaceType workspaceType, Integer reservationId) {
        this.reservationId = reservationId;
        this.worksSpaceId = worksSpaceId;
        this.workspaceType = workspaceType;
    }
}