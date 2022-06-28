package lk.d24.hostelManagementSystem.dao.custom;

import lk.d24.hostelManagementSystem.dao.CrudDAO;
import lk.d24.hostelManagementSystem.entity.Reserve;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 1:47 PM
 * project D24-hostel-management-system
 */

public interface ReserveDAO extends CrudDAO<Reserve> {
    boolean makeMonthlyPayment(String id, double payment);

    int markAllAsNotPaid();
}
