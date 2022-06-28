package lk.d24.hostelManagementSystem.bo.custom;

import lk.d24.hostelManagementSystem.bo.SuperBO;
import lk.d24.hostelManagementSystem.dto.UserDTO;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 11:59 AM
 * project D24-hostel-management-system
 */

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);

    UserDTO checkUserExists(String id);
}
