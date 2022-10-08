/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 9:46 PM
 * project D24-hostel-management-system
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.d24.hostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lk/d24/hostelManagementSystem/presentation/LoginForm.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();



    }
}
