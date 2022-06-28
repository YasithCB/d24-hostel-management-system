package lk.d24.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.d24.hostelManagementSystem.bo.BOFactory;
import lk.d24.hostelManagementSystem.bo.custom.ReserveBO;
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

    private Month currentMonth;
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVE);

    public void initialize(){
        loadHibernate();
        setDate();
        resetMonthlyPayments();

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
    }

    /**
     * if there is a new month, it must reset monthly payments of all students
     * there fore it marks as "Not Paid" as for all students
     */
    private void resetMonthlyPayments() {
        if (currentMonth != LocalDate.now().getMonth()) {
            currentMonth = LocalDate.now().getMonth();
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
