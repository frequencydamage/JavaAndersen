package org.novak.java.model.reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> getAll();

    Reservation getById(Integer reservationId);

    void create(Reservation reservation);

    void deleteById(Integer reservationId);
}
