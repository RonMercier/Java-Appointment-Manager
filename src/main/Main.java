package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Util.DBConnection;

/**
 * @author Ron Mercier - 001406973
 *
 * Main class of the application
 *
 * The lambda expressions can be found on the AppointmentScreenController class and the CustomerScreenController class
 *
 */
public class Main extends Application {

    public static void main(String[] args) {

        DBConnection.startConnection();


        launch(args);
        DBConnection.closeConnection();
    }

    /**
     * Starts the JavaFX user interface portion of the application
     * @param primaryStage Primary Stage of the application.
     * @throws Exception shows a message that provides an error description
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginScreen.fxml"));
        primaryStage.setTitle("C195 - Scheduling Service");
        primaryStage.setScene(new Scene(root, 633, 422));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
