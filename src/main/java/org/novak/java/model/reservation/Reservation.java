package org.novak.java.model.reservation;

import org.novak.java.model.workspace.WorkspaceType;

import java.util.Objects;

public class Reservation {

    private int worksSpaceId;
    private WorkspaceType workspaceType;
    private int reservationId;

    public Reservation(int worksSpaceId, WorkspaceType workspaceType, int reservationId) {
        this.reservationId = reservationId;
        this.worksSpaceId = worksSpaceId;
        this.workspaceType = workspaceType;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getWorksSpaceId() {
        return worksSpaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && worksSpaceId == that.worksSpaceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, worksSpaceId);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "worksSpaceId=" + worksSpaceId +
                ", workspaceType=" + workspaceType +
                ", reservationId=" + reservationId +
                '}';
    }
}