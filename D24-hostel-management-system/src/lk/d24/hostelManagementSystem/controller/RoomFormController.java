package lk.d24.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.RoomBO;
import lk.d24.hostelManagementSystem.dto.RoomDTO;
import lk.d24.hostelManagementSystem.util.ClearDataUtil;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 2:26 PM
 * project D24-hostel-management-system
 */

public class RoomFormController {
    public TableView<RoomDTO> tblRoom;
    public Label lblRoomId;
    public JFXTextField txtMonthlyRent;
    public JFXTextField txtRoomQty;
    public JFXComboBox<String> cmbType;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnNew;

    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){
        generateId();
        loadTable();
        initializeCmb();
        autoFillData();

        btnSave.setOnMouseClicked(event -> {
            saveUpdateRoom();
        });
        btnDelete.setOnMouseClicked(event -> {
            roomBO.deleteRoom(lblRoomId.getText());
            initialize();
        });
        btnNew.setOnMouseClicked(event -> {
            clearData();
            generateId();
            btnSave.setText("Save");
        });
    }

    private void clearData() {
        tblRoom.getSelectionModel().clearSelection();
        ClearDataUtil.clearCmb(cmbType);
        ClearDataUtil.clearTextFields(txtMonthlyRent,txtRoomQty);
    }

    private void saveUpdateRoom() {
        if (btnSave.getText().equals("Save")){
            roomBO.saveRoom(new RoomDTO(
                    lblRoomId.getText(),
                    cmbType.getValue(),
                    Double.parseDouble(txtMonthlyRent.getText()),
                    Integer.parseInt(txtRoomQty.getText()),
                    Integer.parseInt(txtRoomQty.getText()),
                    LocalDate.now()
            ));
        }else {
            roomBO.updateRoom(new RoomDTO(
                    lblRoomId.getText(),
                    cmbType.getValue(),
                    Double.parseDouble(txtMonthlyRent.getText()),
                    Integer.parseInt(txtRoomQty.getText()),
                    Integer.parseInt(txtRoomQty.getText()),
                    LocalDate.now()
            ));
        }
        initialize();
        clearData();
    }

    private void autoFillData() {
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                btnDelete.setDisable(false);
                btnSave.setText("Update");
                cmbType.setValue(newValue.getType());
                txtMonthlyRent.setText(String.valueOf(newValue.getMonthlyRental()));
                txtRoomQty.setText(String.valueOf(newValue.getRoomsQty()));
                lblRoomId.setText(newValue.getRoomId());
            }else {
                btnDelete.setDisable(true);
                btnSave.setText("Save");
            }
        });
    }

    private void initializeCmb() {
        cmbType.getItems().add("Non-AC");
        cmbType.getItems().add("Non-AC | Food");
        cmbType.getItems().add("AC");
        cmbType.getItems().add("AC | Food");
    }

    private void loadTable() {
        tblRoom.getItems().clear();
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("monthlyRental"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomsQty"));
        tblRoom.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        tblRoom.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        tblRoom.setItems(roomBO.getAllRooms());
    }

    private void generateId() {
        lblRoomId.setText(roomBO.generateNextRoomId());
    }
}
