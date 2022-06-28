package lk.d24.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.UserBO;
import lk.d24.hostelManagementSystem.dto.UserDTO;
import lk.d24.hostelManagementSystem.util.NavigationUtil;
import lombok.SneakyThrows;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/17/2022 - 9:34 PM
 * project D24-hostel-management-system
 */

public class LoginFormController {
    public AnchorPane apnMain;
    public AnchorPane apnWelcome;
    public AnchorPane apnReg;
    public JFXTextField txtUsernameReg;
    public JFXTextField pwPasswordReg;
    public JFXButton btnRegister;
    public JFXTextField pwConfirmPasswordReg;
    public Label lblUserId;
    public AnchorPane apnLogin;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXButton btnLogin;
    public JFXButton btnLoginOnReg;
    public Label lblRegister;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        welcomeTransition();

        btnLogin.setOnMouseClicked(event -> {
            login();
        });
        btnRegister.setOnMouseClicked(event -> {
            register();
        });
        lblRegister.setOnMouseClicked(event -> {
            apnReg.toFront();
            apnLogin.toBack();
        });
        btnLoginOnReg.setOnMouseClicked(event -> {
            apnLogin.toFront();
        });
    }

    private void welcomeTransition() {
//        apnWelcome.setDisable(false);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1),apnWelcome);
        fadeOut.setFromValue(1);
        fadeOut.setDelay(Duration.seconds(1.3));
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeOut.play();
        fadeOut.setOnFinished(event -> {
            apnWelcome.setDisable(true);
        });
    }

    private void register() {
        if (userBO.saveUser(new UserDTO(
                lblUserId.getText(),
                txtUsernameReg.getText(),
                pwPasswordReg.getText(),
                LocalDate.now()
        ))) {
            new Alert(Alert.AlertType.INFORMATION,"Registration Successfull!").show();
        }else
            new Alert(Alert.AlertType.ERROR,"OOps! Something went wrong").show();
    }

    @SneakyThrows
    private void login() {
        /*UserDTO userDTO = userBO.checkUserExists(txtUserName.getText());
        if (userDTO == null) {
            new Alert(Alert.AlertType.ERROR,"User not Found!").show();
        }else {
            if (userDTO.getPassword().equals(txtPassword.getText())) {
                NavigationUtil.replaceApn(apnLogin,"MainForm");
            }else
                new Alert(Alert.AlertType.ERROR,"Password Incorrect!").show();
        }*/
        NavigationUtil.replaceApn(apnLogin,"MainForm");
    }
}