package org.novak.java.model.reservation;

import org.novak.java.util.DataLoaderUtil;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInMemoryRepositoryImpl implements ReservationRepository, Serializable {

    private static ReservationInMemoryRepositoryImpl instance;
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private static final String FILE_NAME = System.getProperty("reservations.file",
            Paths.get(System.getProperty("user.home"), ".workspace-manager", "reservations.dat").toString());

    private ReservationInMemoryRepositoryImpl() {
    }

    @Override
    public List<Reservation> getAll() {
        return reservations.values().stream()
                .toList();
    }

    @Override
    public Reservation getById(Integer reservationId) {
        return reservations.get(reservationId);
    }

    @Override
    public void create(Reservation reservation) {
        reservations.put(reservation.getReservationId(), reservation);
        saveData();
    }

    @Override
    public void deleteById(Integer reservationId) {
        reservations.remove(reservationId);
        saveData();
    }

    @Override
    public void deleteByWorkspaceId(Integer workspaceId) {
        reservations.values().stream()
                .filter(reservation -> reservation.getWorksSpaceId() == workspaceId)
                .findFirst()
                .ifPresent(reservation -> reservations.remove(reservation.getReservationId()));
        saveData();
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            if (FILE_NAME != null && !FILE_NAME.isEmpty()) {
                instance = DataLoaderUtil.loadData(FILE_NAME);
            }
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
