package lk.d24.hostelManagementSystem.bo.custom;

import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.SuperBO;
import lk.d24.hostelManagementSystem.dto.RoomDTO;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:33 PM
 * project D24-hostel-management-system
 */

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDTO roomDTO);

    boolean deleteRoom(String s);

    String generateNextRoomId();

    ObservableList<RoomDTO> getAllRooms();

    boolean updateRoom(RoomDTO roomDTO);

    RoomDTO getSpecificRoom(String newValue);

    int getAvailableRoomsCount();
}
