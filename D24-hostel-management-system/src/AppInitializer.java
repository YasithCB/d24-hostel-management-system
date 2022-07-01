/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 9:46 PM
 * project D24-hostel-management-system
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.d24.hostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/d24/hostelManagementSystem/presentation/LoginForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://example.com/javaicon.png"));
        primaryStage.show();
    }
}
