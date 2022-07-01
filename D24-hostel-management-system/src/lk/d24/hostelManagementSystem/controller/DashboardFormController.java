package lk.d24.hostelManagementSystem.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.ReserveBO;
import lk.d24.hostelManagementSystem.bo.custom.RoomBO;
import lk.d24.hostelManagementSystem.bo.custom.StudentBO;
import lk.d24.hostelManagementSystem.entity.Room;
import lk.d24.hostelManagementSystem.presentation.tm.StudentNotPaidTM;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author  Yasith C Bandara
 * created 6/29/2022 - 9:49 AM
 * project D24-hostel-management-system
 */

public class DashboardFormController {
    public TableView<StudentNotPaidTM> tblStudentsNotPaid;
    public Label lblStudentCount;
    public Label lblRoomAvailableCount;
    public PieChart pieStudentByGender;

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVE);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){
        loadTable();
        setItemCounts();
        setupPieChart();
    }

    private void setupPieChart() {
        ObservableList<PieChart.Data> studentsByGender = FXCollections.observableArrayList();
        studentsByGender.add(new PieChart.Data("Male",studentBO.getStudentsCountByGender("Male")));
        studentsByGender.add(new PieChart.Data("Female",studentBO.getStudentsCountByGender("Female")));
        studentsByGender.add(new PieChart.Data("Other",studentBO.getStudentsCountByGender("Other")));

        // show detail name and value
        studentsByGender.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName()," ",data.pieValueProperty())
                )
        );
        // add to chart
        pieStudentByGender.setData(studentsByGender);
    }

    private void setItemCounts() {
        lblStudentCount.setText(String.valueOf(studentBO.getStudentCount()));
        lblRoomAvailableCount.setText(String.valueOf(roomBO.getAvailableRoomsCount()));
    }

    private void loadTable() {
        tblStudentsNotPaid.getItems().clear();
        tblStudentsNotPaid.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudentsNotPaid.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblStudentsNotPaid.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));

        tblStudentsNotPaid.setItems(reserveBO.getStudentsNotPaidToTable());
    }
}
