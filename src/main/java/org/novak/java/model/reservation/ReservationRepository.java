package org.novak.java.model.reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> getAllReservations();

    Reservation getReservationByReservationId(int reservationId);

    void createReservation(Reservation reservation);

    void deleteReservationByReservationId(int reservationId);

    void deleteReservationByWorkspaceId(int workspaceId);
}
