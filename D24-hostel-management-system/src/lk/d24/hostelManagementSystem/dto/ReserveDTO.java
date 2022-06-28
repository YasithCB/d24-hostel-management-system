package lk.d24.hostelManagementSystem.dto;

import lk.d24.hostelManagementSystem.entity.Room;
import lk.d24.hostelManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:53 PM
 * project D24-hostel-management-system
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveDTO {
    private Room room;
    private Student student;
    private LocalDate date;
    private double keyMoney;
    private double monthlyPaidTotal;
    private String isPayForThisMonth;
}
