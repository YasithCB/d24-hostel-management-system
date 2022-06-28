package lk.d24.hostelManagementSystem.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.custom.RoomBO;
import lk.d24.hostelManagementSystem.dao.DAOFactory;
import lk.d24.hostelManagementSystem.dao.custom.RoomDAO;
import lk.d24.hostelManagementSystem.dto.RoomDTO;
import lk.d24.hostelManagementSystem.entity.Room;

import java.util.ArrayList;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:33 PM
 * project D24-hostel-management-system
 */

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        return roomDAO.save(new Room(
                roomDTO.getRoomId(),
                roomDTO.getType(),
                roomDTO.getMonthlyRental(),
                roomDTO.getRoomsQty(),
                roomDTO.getAvailableQty(),
                roomDTO.getDateCreated()
        ));
    }

    @Override
    public boolean deleteRoom(String id) {
        return roomDAO.delete(id);
    }

    @Override
    public String generateNextRoomId() {
        ArrayList<Room> all = roomDAO.getAll();
        if (all.size() > 0) {
            return String.format("R%03d",Integer.parseInt(all.get(all.size()-1).getRoomId().replace("R","")) + 1);
        }else
            return "R001";
    }

    @Override
    public ObservableList<RoomDTO> getAllRooms() {
        ObservableList<RoomDTO> dtos = FXCollections.observableArrayList();
        ArrayList<Room> all = roomDAO.getAll();
        for (Room entity : all) {
            dtos.add(new RoomDTO(
                    entity.getRoomId(),
                    entity.getType(),
                    entity.getMonthlyRental(),
                    entity.getRoomsQty(),
                    entity.getAvailableQty(),
                    entity.getDateCreated()
            ));
        }
        System.out.println(dtos);
        return dtos;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return roomDAO.update(new Room(
                roomDTO.getRoomId(),
                roomDTO.getType(),
                roomDTO.getMonthlyRental(),
                roomDTO.getRoomsQty(),
                roomDTO.getAvailableQty(),
                roomDTO.getDateCreated()
        ));
    }

    @Override
    public RoomDTO getSpecificRoom(String id) {
        Room room = roomDAO.getSpecificEntity(id);
        return new RoomDTO(
                room.getRoomId(),
                room.getType(),
                room.getMonthlyRental(),
                room.getRoomsQty(),
                room.getAvailableQty(),
                room.getDateCreated()
        );
    }
}
