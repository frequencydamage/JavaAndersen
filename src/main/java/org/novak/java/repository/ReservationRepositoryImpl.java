package org.novak.java.repository;

import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationRepository;

import java.util.List;

public class ReservationRepositoryImpl extends Repository implements ReservationRepository {

    private static ReservationRepository instance;

    private ReservationRepositoryImpl() {
    }

    @Override
    public void create(Reservation reservation) {
        super.create(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return getAll(Reservation.class);
    }

    @Override
    public Reservation getById(Integer reservationId) {
        return getById(Reservation.class, reservationId);
    }

    @Override
    public void deleteById(Integer reservationId) {
        Reservation reservation = getById(reservationId);
        if (reservation != null) {
            delete(reservation);
        }
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepositoryImpl();
        }
        return instance;
    }
}
