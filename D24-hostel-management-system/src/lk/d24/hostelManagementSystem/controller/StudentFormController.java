package lk.d24.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.StudentBO;
import lk.d24.hostelManagementSystem.dto.StudentDTO;
import lk.d24.hostelManagementSystem.util.ClearDataUtil;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 2:26 PM
 * project D24-hostel-management-system
 */

public class StudentFormController {
    public TableView<StudentDTO> tblStudent;
    public Label lblStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDob;
    public JFXRadioButton radMale;
    public ToggleGroup gender;
    public JFXRadioButton radFemale;
    public JFXRadioButton radOther;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnNew;

    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        generateId();
        loadTable();
        autoFillData();

        btnSave.setOnMouseClicked(event -> {
            saveUpdateStudent();
        });
        btnDelete.setOnMouseClicked(event -> {
            deleteStudent();
        });
        btnNew.setOnMouseClicked(event -> {
            clearData();
        });
    }

    private void autoFillData() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                btnDelete.setDisable(false);
                btnSave.setText("Update");
                txtName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                txtAddress.setText(newValue.getAddress());
                txtDob.setValue(newValue.getDob());
                switch (newValue.getGender()){
                    case "Male" : radMale.setSelected(true);break;
                    case "Female" : radFemale.setSelected(true);break;
                    case "Other" : radOther.setSelected(true);
                }
            }else {
                btnDelete.setDisable(true);
                btnSave.setText("Save");
            }
        });
    }

    private void deleteStudent() {
        if (studentBO.deleteStudent("S001")) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
            initialize();
        }else
            new Alert(Alert.AlertType.ERROR,"Oops!").show();
    }

    private void saveUpdateStudent() {
        if (btnSave.getText().equals("Save")) {
            if (studentBO.saveStudent(new StudentDTO(
                    lblStudentId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    txtDob.getValue(),
                    ((RadioButton) gender.getSelectedToggle()).getText(),

                    LocalDate.now()
            ))) {
                new Alert(Alert.AlertType.INFORMATION,"Student Saved!").show();
                initialize();
            }else
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
        } else {
            if (studentBO.UpdateStudent(new StudentDTO(
                    lblStudentId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    txtDob.getValue(),
                    ((RadioButton) gender.getSelectedToggle()).getText(),
                    LocalDate.now()
            ))) {
                new Alert(Alert.AlertType.INFORMATION, "Student Updated!").show();
                initialize();
            } else
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
        initialize();
        clearData();

    }

    private void clearData() {
        ClearDataUtil.clearTextFields(txtAddress,txtName,txtContact);
        tblStudent.getSelectionModel().clearSelection();
    }

    private void loadTable() {
        tblStudent.getItems().clear();
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        tblStudent.setItems(studentBO.getAllStudents());
    }

    private void generateId() {
        lblStudentId.setText(studentBO.generateNextStudentId());
    }
}
