package org.novak.java.model.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.novak.java.model.workspace.Workspace;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Setter
    @OneToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    @JsonBackReference
    private Workspace workspace;
}