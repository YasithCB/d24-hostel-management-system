package lk.d24.hostelManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 11:57 AM
 * project D24-hostel-management-system
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String roomId;
    private String type;
    private double monthlyRental;
    private int roomsQty;
    private int availableQty;
    private LocalDate dateCreated;
}
