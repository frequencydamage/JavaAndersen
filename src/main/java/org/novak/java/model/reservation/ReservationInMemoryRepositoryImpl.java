package org.novak.java.model.reservation;

import org.novak.java.util.DataLoaderUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInMemoryRepositoryImpl implements ReservationRepository, Serializable {

    private static ReservationInMemoryRepositoryImpl instance;
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private static final String FILE_NAME = "reservations.dat";

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
        saveData();
    }

    @Override
    public void deleteReservationByReservationId(int reservationId) {
        reservations.remove(reservationId);
        saveData();
    }

    @Override
    public void deleteReservationByWorkspaceId(int workspaceId) {
        reservations.values().stream()
                .filter(reservation -> reservation.getWorksSpaceId() == workspaceId)
                .findFirst()
                .ifPresent(reservation -> reservations.remove(reservation.getReservationId()));
        saveData();
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = DataLoaderUtil.loadData(FILE_NAME);

            if (instance == null) {
                instance = new ReservationInMemoryRepositoryImpl();
            }
        }

        return instance;
    }

    private void saveData() {
        DataLoaderUtil.saveData(this, FILE_NAME);
    }
}
