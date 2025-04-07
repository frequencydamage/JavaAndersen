package org.novak.java.repository;

import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl extends RepositoryImpl implements ReservationRepository {

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
}
