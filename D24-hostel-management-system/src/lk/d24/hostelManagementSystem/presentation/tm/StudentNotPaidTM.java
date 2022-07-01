package lk.d24.hostelManagementSystem.presentation.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  Yasith C Bandara
 * created 6/29/2022 - 1:02 PM
 * project D24-hostel-management-system
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentNotPaidTM {
    private String studentId;
    private String roomId;
    private String name;
}
