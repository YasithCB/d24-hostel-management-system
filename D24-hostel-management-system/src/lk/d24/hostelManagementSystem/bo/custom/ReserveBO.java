package lk.d24.hostelManagementSystem.bo.custom;

import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.SuperBO;
import lk.d24.hostelManagementSystem.dto.ReserveDTO;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:38 PM
 * project D24-hostel-management-system
 */

public interface ReserveBO extends SuperBO {
    ObservableList<ReserveDTO> getAllReserves();

    boolean saveReserve(ReserveDTO reserveDTO);

    boolean updateReserve(ReserveDTO dto);

    boolean makeMonthlyPayment(String s, double parseDouble);

    int markAllAsNotPaid();
}
