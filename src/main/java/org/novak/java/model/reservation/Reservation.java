package org.novak.java.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.novak.java.model.workspace.WorkspaceType;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Reservation implements Serializable {

    private Integer worksSpaceId;
    private Integer reservationId;
    private WorkspaceType workspaceType;
}