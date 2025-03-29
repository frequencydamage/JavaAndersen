package org.novak.java.repository;

import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationRepository;
import org.novak.java.model.workspace.WorkspaceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ReservationRepositoryImpl extends Repository implements ReservationRepository {

    private static ReservationRepositoryImpl instance;

    private final String QUERY_CREATE = "INSERT INTO reservation (reservation_Id, workspace_Id, workspace_type) VALUES (?, ?, ?)";
    private final String QUERY_GET_ALL = "SELECT * FROM reservation";
    private final String QUERY_GET_BY_ID = "SELECT * FROM reservation WHERE reservation_Id = ?";
    private final String QUERY_DELETE_BY_ID = "DELETE FROM reservation WHERE reservation_Id = ?";

    private ReservationRepositoryImpl() {
    }

    @Override
    public void create(Reservation reservation) {
        executeUpdate(QUERY_CREATE, statement -> {
            statement.setInt(1, reservation.getReservationId());
            statement.setInt(2, reservation.getWorksSpaceId());
            statement.setString(3, reservation.getWorkspaceType().toString());
        });
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservationList = new ArrayList<>();

        executeSelect(QUERY_GET_ALL, statement -> {
        }, results -> {
            while (results.next()) {
                reservationList.add(mapToReservation(results));
            }
        });

        return reservationList;
    }

    @Override
    public Reservation getById(Integer reservationId) {
        AtomicReference<Reservation> reservation = new AtomicReference<>(null);

        executeSelect(QUERY_GET_BY_ID,
                statement -> statement.setInt(1, reservationId),
                results -> {
                    if (results.next()) {
                        reservation.set(mapToReservation(results));
                    }
                });

        return reservation.get();
    }


    @Override
    public void deleteById(Integer reservationId) {
        executeUpdate(QUERY_DELETE_BY_ID, statement -> statement.setInt(1, reservationId));
    }

    private Reservation mapToReservation(ResultSet result) throws SQLException {
        return new Reservation(
                result.getInt("workspace_id"),
                result.getInt("reservation_id"),
                WorkspaceType.valueOf(result.getString("workspace_type")));
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepositoryImpl();
        }

        return instance;
    }
}
