package main.Controller.Util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Util.DBConnection;
import java.io.IOException;

/**
 *  @author Ron Mercier - 001406973
 *
 * This general controller is used for all controllers of the project to lessen the amount of code that is used
 * repetitively in each of the controllers
 */
public class GeneralController {

    /**
     * This method is used to transition from one page to another for each of the controllers.
     * @param actionEvent action event to change from one tab to another
     * @param pageName the name of the current page
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void changeTab(ActionEvent actionEvent, String pageName) throws IOException {
        DBConnection.startConnection();
        Parent root = FXMLLoader.load(GeneralController.class.getResource("/main/View/"+pageName+".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    /**
     * This method creates a main tab that brings the user back to the customer tab upon closing of tab.
     * @param tabPane tab pane
     * @param tabName the name of the tab
     * @param pageName the name of the page
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void showCustomerTab(TabPane tabPane, String tabName, String pageName) throws IOException {
        DBConnection.startConnection();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Parent root = FXMLLoader.load(GeneralController.class.getResource("/main/View/"+ pageName +".fxml"));
        Tab tab = new Tab(tabName,root);
        tab.setTooltip(new Tooltip(tabName));

        tabPane.getTabs().add(tab);
        selectionModel.select(tab);
        tabPane.setSelectionModel(selectionModel);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tab.setOnClosed(e -> tabPane.getSelectionModel().select(tabPane.getTabs().get(0)));

    }

    /**
     *This method creates a main tab that brings the user back to the appointment tab upon closing of tab.
     * @param tabPane tab pane for appointment tab
     * @param tabName Name of the tab
     * @param pageName Name of the page
     * @throws IOException Signals that an I/O exception  has occurred.
     */
    public static void showAppointmentTab(TabPane tabPane, String tabName, String pageName) throws IOException {
        DBConnection.startConnection();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Parent root = FXMLLoader.load(GeneralController.class.getResource("/main/View/"+pageName+".fxml"));
        Tab tab = new Tab(tabName,root);
        tab.setTooltip(new Tooltip(tabName));

        tabPane.getTabs().add(tab);
        selectionModel.select(tab);
        tabPane.setSelectionModel(selectionModel);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tab.setOnClosed(e -> tabPane.getSelectionModel().select(tabPane.getTabs().get(1)));

    }

    /**
     * This method creates a main tab that brings the user back to the report tab upon closing of tab.
     * @param tabPane tab pane for report tab
     * @param tabName Name of the tab
     * @param pageName Name of the page
     * @throws IOException Signals that an I/O exception  has occurred.
     */
    public static void showReportTab(TabPane tabPane, String tabName, String pageName) throws IOException {
        DBConnection.startConnection();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Parent root = FXMLLoader.load(GeneralController.class.getResource("/main/View/"+pageName+".fxml"));
        Tab tab = new Tab(tabName,root);
        tab.setTooltip(new Tooltip(tabName));

        tabPane.getTabs().add(tab);
        selectionModel.select(tab);
        tabPane.setSelectionModel(selectionModel);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tab.setOnClosed(e -> tabPane.getSelectionModel().select(tabPane.getTabs().get(3)));

    }

    /**
     * This method is used to alert the user of any given errors in the application.  Used in general controller
     * to minimize code.
     * @param alertType The type of alert that is shown
     * @param title alert title
     * @param header alert header
     * @param content alert content
     * @return returns an alert
     */
    public static Alert alertUser(Alert.AlertType alertType,String title, String header, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert;
    }
}