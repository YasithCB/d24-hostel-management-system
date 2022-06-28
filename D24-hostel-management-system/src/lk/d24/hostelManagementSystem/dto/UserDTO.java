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

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userid;
    private String userName;
    private String password;
    private LocalDate dateCreated;
}
