package lk.d24.hostelManagementSystem.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.custom.ReserveBO;
import lk.d24.hostelManagementSystem.dao.DAOFactory;
import lk.d24.hostelManagementSystem.dao.custom.ReserveDAO;
import lk.d24.hostelManagementSystem.dto.ReserveDTO;
import lk.d24.hostelManagementSystem.entity.Reserve;
import lk.d24.hostelManagementSystem.presentation.tm.StudentNotPaidTM;

import java.util.ArrayList;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:38 PM
 * project D24-hostel-management-system
 */

public class ReserveBOImpl implements ReserveBO {

    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public ObservableList<ReserveDTO> getAllReserves() {
        ObservableList<ReserveDTO> dtos = FXCollections.observableArrayList();
        ArrayList<Reserve> all = reserveDAO.getAll();
        for (Reserve reserve : all) {
            dtos.add(new ReserveDTO(
                    reserve.getRoom(),
                    reserve.getStudent(),
                    reserve.getDate(),
                    reserve.getKeyMoney(),
                    reserve.getMonthlyPaidTotal(),
                    reserve.getIsPayForThisMonth()
            ));
        }
        return dtos;
    }

    @Override
    public boolean saveReserve(ReserveDTO dto) {
        return reserveDAO.save(new Reserve(
                dto.getRoom(),
                dto.getStudent(),
                dto.getDate(),
                dto.getKeyMoney(),
                dto.getMonthlyPaidTotal(),
                dto.getIsPayForThisMonth()
        ));
    }

    @Override
    public boolean updateReserve(ReserveDTO dto) {
        return reserveDAO.update(new Reserve(
                dto.getRoom(),
                dto.getStudent(),
                dto.getDate(),
                dto.getKeyMoney(),
                dto.getMonthlyPaidTotal(),
                dto.getIsPayForThisMonth()
        ));
    }

    @Override
    public boolean makeMonthlyPayment(String id, double payment) {
        return reserveDAO.makeMonthlyPayment(id,payment);
    }

    @Override
    public int markAllAsNotPaid() {
        return reserveDAO.markAllAsNotPaid();
    }

    @Override
    public ObservableList<StudentNotPaidTM> getStudentsNotPaidToTable() {
        ObservableList<StudentNotPaidTM> notPaidTMS = FXCollections.observableArrayList();
        ArrayList<Reserve> all = reserveDAO.getAll();
        for (Reserve reserve : all) {
            if (reserve.getIsPayForThisMonth().equals("Not Paid")){
                notPaidTMS.add(new StudentNotPaidTM(
                        reserve.getStudent().getStudentId(),
                        reserve.getRoom().getRoomId(),
                        reserve.getStudent().getName()
                ));
            }
        }
        return notPaidTMS;
    }

    @Override
    public boolean deleteReserve(String id) {
        return reserveDAO.delete(id);
    }
}
