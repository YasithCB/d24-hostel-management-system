package lk.d24.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.ReserveBO;
import lk.d24.hostelManagementSystem.bo.custom.UserBO;
import lk.d24.hostelManagementSystem.dto.UserDTO;
import lk.d24.hostelManagementSystem.util.FactoryConfiguration;
import lk.d24.hostelManagementSystem.util.NavigationUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 1:49 PM
 * project D24-hostel-management-system
 */

public class MainFormController {
    public JFXButton btnReserveARooms;
    public JFXButton btnStudent;
    public JFXButton btnRooms;
    public AnchorPane apnMain;
    public Label month;
    public Label day;
    public Label year;
    public JFXButton btnDashboard;
    public ImageView imgHome;
    public Label lblLogout;
    public Label lblUserName;
    public AnchorPane apnMainForm;

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVE);

    public static UserDTO userDTO;

    public void initialize(){
        loadHibernate();
        setDate();
        resetMonthlyPayments();
        setUserName();

        imgHome.setOnMouseClicked(event -> {
            try {
                NavigationUtil.setChildApn(apnMain,"DashboardForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnDashboard.setOnMouseClicked(event -> {
            try {
                NavigationUtil.setChildApn(apnMain,"DashboardForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnStudent.setOnMouseClicked(event -> {
            try {
                NavigationUtil.setChildApn(apnMain,"StudentForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnRooms.setOnMouseClicked(event -> {
            try {
                NavigationUtil.setChildApn(apnMain,"RoomForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnReserveARooms.setOnMouseClicked(event -> {
            try {
                NavigationUtil.setChildApn(apnMain,"ReserveRoom");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        lblLogout.setOnMouseClicked(event -> {
            try {
                NavigationUtil.replaceApn(apnMainForm,"LoginForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            NavigationUtil.setChildApn(apnMain,"DashboardForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUserName() {
//        lblUserName.setText(userDTO.getUserName());
    }

    /**
     * if there is a new month, it must reset monthly payments of all students
     * there fore it marks as "Not Paid" as for all students
     */
    private void resetMonthlyPayments() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            reserveBO.markAllAsNotPaid();
        }
    }

    private void setDate() {
        day.setText(String.valueOf(LocalDate.now().getDayOfMonth()));
        month.setText(String.valueOf(LocalDate.now().getMonth()));
        year.setText(String.valueOf(LocalDate.now().getYear()));
    }

    private void loadHibernate() {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.close();
    }
}
