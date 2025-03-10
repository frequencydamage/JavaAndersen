package org.novak.java.model.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInMemoryRepositoryImpl implements ReservationRepository {

    private static ReservationInMemoryRepositoryImpl instance;
    private Map<Integer, Reservation> reservations = new HashMap<>();

    private ReservationInMemoryRepositoryImpl() {
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservations.values().stream()
                .toList();
    }

    @Override
    public Reservation getReservationByReservationId(int reservationId) {
        return reservations.get(reservationId);
    }

    @Override
    public void createReservation(Reservation reservation) {
        reservations.put(reservation.getReservationId(), reservation);
    }

    @Override
    public void deleteReservationByReservationId(int reservationId) {
        reservations.remove(reservationId);
    }

    @Override
    public void deleteReservationByWorkspaceId(int workspaceId) {
        reservations.values().stream()
                .filter(reservation -> reservation.getWorksSpaceId() == workspaceId)
                .findFirst()
                .ifPresent(reservation -> reservations.remove(reservation.getReservationId()));
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationInMemoryRepositoryImpl();
        }

        return instance;
    }
}
