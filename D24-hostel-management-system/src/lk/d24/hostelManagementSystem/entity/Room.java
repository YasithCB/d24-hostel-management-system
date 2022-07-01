package lk.d24.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 8:14 PM
 * project D24-hostel-management-system
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Room {
    @Id
    private String roomId;
    private String type;
    private double monthlyRental;
    private int roomsQty;
    private int availableQty;
    private LocalDate dateCreated;

    @OneToMany (mappedBy = "room",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    public Room(String roomId, String type, double monthlyRental, int roomsQty, int availableQty, LocalDate dateCreated) {
        this.roomId = roomId;
        this.type = type;
        this.monthlyRental = monthlyRental;
        this.roomsQty = roomsQty;
        this.availableQty = availableQty;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return roomId;
    }
}
