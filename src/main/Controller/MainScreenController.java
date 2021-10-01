package main.Controller;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Controller.Util.GeneralController;
import main.DAO.DBAppointment;
import main.DAO.DBContacts;
import main.DAO.DBCustomers;
import main.Model.Appointment;
import main.Model.Contact;
import main.Model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *@author Ron Mercier - 001406973
 *
 * The MainScreenController class handles all the processess related to the Main.fxml page.
 */
public class MainScreenController implements Initializable {

    public TabPane mainTabPane;


    //Customer Table
    public TableView<Customer> customerTableView;
    public TableColumn<Customer,Integer> customerId;
    public TableColumn<Customer,String> customerName;
    public TableColumn<Customer,String> customerAddress;
    public TableColumn<Customer,String> customerZipcode;
    public TableColumn<Customer,String> customerPhone;
    public TableColumn<Customer,String> customerDivision;

    //Appointments This Week Table
    public TableView<Appointment> apptThisWeekTable;
    public TableColumn<Appointment,Integer> apptWeekID;
    public TableColumn<Appointment,String> apptWeekTitle;
    public TableColumn<Appointment,String> apptWeekDesc;
    public TableColumn<Appointment,String> apptWeekLocation;
    public TableColumn<Appointment,Integer> apptWeekContact;
    public TableColumn<Appointment,String> apptWeekType;
    public TableColumn<Appointment,String> apptWeekStart;
    public TableColumn<Appointment,String> apptWeekEnd;
    public TableColumn<Appointment,Integer> apptWeekCustID;

    //Appointments This Month Table
    public TableView<Appointment> apptThisMonthTable;
    public TableColumn<Appointment,Integer> apptMonthID;
    public TableColumn<Appointment,String> apptMonthTitle;
    public TableColumn<Appointment,String> apptMonthDesc;
    public TableColumn<Appointment,String> apptMonthLocation;
    public TableColumn<Appointment,Integer> apptMonthContact;
    public TableColumn<Appointment,String> apptMonthType;
    public TableColumn<Appointment,String> apptMonthStart;
    public TableColumn<Appointment,String> apptMonthEnd;
    public TableColumn<Appointment,Integer> apptMonthCustID;

    //All Appointments Table
    public TableView<Appointment> allApptTable;
    public TableColumn<Appointment,Integer> allApptId;
    public TableColumn<Appointment,String> allApptTitle;
    public TableColumn<Appointment,String> allApptDesc;
    public TableColumn<Appointment,String> allApptLocation;
    public TableColumn<Appointment,Integer> allApptContact;
    public TableColumn<Appointment,String> allApptType;
    public TableColumn<Appointment,String> allApptStart;
    public TableColumn<Appointment,String> allApptEnd;
    public TableColumn<Appointment,Integer> allCustomerId;


    public Label currentUserLbl;

    //Contact Table
    public TableView<Contact> contactTableView;
    public TableColumn<Contact,Integer> contactId;
    public TableColumn<Contact,String> contactName;
    public TableColumn<Contact,String> contactEmail;

    private static Customer modifyCustomer;
    private static Appointment modifyAppointment;


    /**
     * This method initializes the Main view and sets the Tab closing policy for the main tabs.
     * @param url Uniform Resource Locator, a pointer to a "resource" on the World Wide Web.
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentUserLbl.setText(LoginScreenController.getGlobalUser().getUserName());
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tableBuild();

    }

    /**
     * This method builds the tables for the main view of the application
     */
    public void tableBuild(){
        FilteredList<Customer> filteredCustomerList = new FilteredList<>(Objects.requireNonNull(DBCustomers.getAllCustomersWithDivisionAndCountries()));
        customerTableView.setItems(filteredCustomerList);
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerZipcode.setCellValueFactory(new PropertyValueFactory<>("customerZipcode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("customerDivisionText"));


        FilteredList<Appointment> filteredWeeklyAppointmentsList = new FilteredList<>(Objects.requireNonNull(DBAppointment.getAllAppointmentsThisWeek()));
        apptThisWeekTable.setItems(filteredWeeklyAppointmentsList);
        apptWeekID.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        apptWeekTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptWeekDesc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        apptWeekLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptWeekContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        apptWeekType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptWeekStart.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptWeekEnd.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptWeekCustID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));


        FilteredList<Appointment> filteredMonthlyAppointmentList = new FilteredList<>(Objects.requireNonNull(DBAppointment.getAllAppointmentsThisMonth()));
        apptThisMonthTable.setItems(filteredMonthlyAppointmentList);
        apptMonthID.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        apptMonthTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptMonthDesc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        apptMonthLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptMonthContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        apptMonthType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptMonthStart.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptMonthEnd.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptMonthCustID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));


        FilteredList<Appointment> filteredAllAppointmentList = new FilteredList<>(Objects.requireNonNull(DBAppointment.getAllAppointments()));
        allApptTable.setItems(filteredAllAppointmentList);
        allApptId.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        allApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        allApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        allApptLocation.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        allApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        allApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        allApptStart.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        allApptEnd.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        allCustomerId.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));


        FilteredList<Contact> filteredContactList = new FilteredList<>(Objects.requireNonNull(DBContacts.getAllContacts()));
        contactTableView.setItems(filteredContactList);
        contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
    }


    //////////////////////////////////////////////Customers/////////////////////////////////////////////////////

    /**
     *This method brings the user to the customer form
     *
     * @param actionEvent action event to add customer to the database
     * @throws IOException signals that an I/O exception has occurred
     */
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        modifyCustomer = null;
        GeneralController.showCustomerTab(mainTabPane,"Add Customer", "CustomerForm");

    }

    /**
     *
     * @param actionEvent action event to modify customer in database
     * @throws IOException Signals that an I/O exception has occurred
     */
    public void modifyCustomer(ActionEvent actionEvent) throws IOException {
        modifyCustomer = customerTableView.getSelectionModel().getSelectedItem();
        GeneralController.showCustomerTab(mainTabPane,modifyCustomer.getCustomerId()+" | "+modifyCustomer.getCustomerName(), "CustomerForm");

    }

    /**
     * This method takes the selected customer from the customer table and deletes them after clicking OK on the
     * confirmation alert.  If there are any appointments attached to the customer, the user will receive an alert
     * of said appointments.  Once confirmed, the data is deleted from the table and the database.
     *
     * @param actionEvent action event to delete customer from database
     * @throws IOException signals that an I/O exception has occurred
     */
    public void deleteCustomer(ActionEvent actionEvent) throws IOException {
        modifyCustomer = customerTableView.getSelectionModel().getSelectedItem();

        Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Customer","Continue Deleting Customer?", modifyCustomer.getCustomerId()+" | "+modifyCustomer.getCustomerName());
        Optional<ButtonType> confirm = confirmDelete.showAndWait();
        if (confirm.isPresent()&&confirm.get()==ButtonType.OK){

            if (DBAppointment.customerHasAppointments(modifyCustomer.getCustomerId())){

                Alert confirmDeleteAppointments = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Customer","Customer Has Appointments", "Would you like to delete the associated appointments?");
                Optional<ButtonType> confirmAppts = confirmDeleteAppointments.showAndWait();

                if (confirmAppts.isPresent()&&confirmAppts.get()==ButtonType.OK){
                    DBAppointment.deleteAllAppointmentsByCustomerID(modifyCustomer.getCustomerId());
                    DBCustomers.deleteCustomer(modifyCustomer.getCustomerId());

                    customerTableView.setItems(DBCustomers.getAllCustomersWithDivisionAndCountries());
                    allApptTable.setItems(DBAppointment.getAllAppointments());
                    apptThisWeekTable.setItems(DBAppointment.getAllAppointmentsThisWeek());
                    apptThisMonthTable.setItems(DBAppointment.getAllAppointmentsThisMonth());

                }


            }

        }

    }

    ///////////////////////////////////////////////Appointments//////////////////////////////////////////////////////

    /**
     * This method brings the user to the Appointment form.
     *
     * @param actionEvent action event to add appointment to database
     * @throws IOException signals that an I/O exception has occurred
     */
    public void addAppointment(ActionEvent actionEvent) throws IOException {
        modifyAppointment = null;
        GeneralController.showAppointmentTab(mainTabPane,"New Appointment","AppointmentScreen");
    }

    /**
     * This method moves the user to the Appointment form and stores a selected Appointment from the table views so to
     * store the appointment data in the Appointment form for editing.
     * @param actionEvent action event to modify existing appointment in the database
     * @throws IOException signals that an I/O exception has occurred
     */
    public void modifyAppointment(ActionEvent actionEvent)throws IOException {
        try {
            modifyAppointment = allApptTable.getSelectionModel().getSelectedItem();
            GeneralController.showAppointmentTab(mainTabPane,"Appointment for " +modifyAppointment.getApptCustomerId(),"AppointmentScreen");
        }catch (NullPointerException n1){
            try {
                modifyAppointment = allApptTable.getSelectionModel().getSelectedItem();
                modifyAppointment = apptThisMonthTable.getSelectionModel().getSelectedItem();
                System.out.println(modifyAppointment.getApptTitle());
            }catch (NullPointerException n2){
                try {
                    modifyAppointment = allApptTable.getSelectionModel().getSelectedItem();
                    modifyAppointment = apptThisWeekTable.getSelectionModel().getSelectedItem();
                    //System.out.println(modifyAppointment.getApptTitle());
                }catch (NullPointerException n3){
                    n3.printStackTrace();
                }
            }
        }
    }

    /**
     * This method deletes an Appointment from the database after the user clicks OK on the alert.
     *
     * @param actionEvent action event to delete appointment from the database
     * @throws IOException  signals that an I/O exception has occurred
     */
    public void deleteAppointment(ActionEvent actionEvent) throws IOException {

        try {
            modifyAppointment = allApptTable.getSelectionModel().getSelectedItem();
            System.out.println(modifyAppointment.getApptTitle());

            Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
            Optional<ButtonType> confirm = confirmDelete.showAndWait();
            if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                DBAppointment.deleteAppointment(modifyAppointment.getApptId());
                allApptTable.setItems(DBAppointment.getAllAppointments());

                Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Appointment Deleted!", "Deletion Confirmed", modifyAppointment.getApptId() +" | "+modifyAppointment.getApptType());
                onDelete.showAndWait();
            }
        }catch (NullPointerException n1){
            try {
                modifyAppointment = apptThisMonthTable.getSelectionModel().getSelectedItem();
                System.out.println(modifyAppointment.getApptTitle());

                Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
                Optional<ButtonType> confirm = confirmDelete.showAndWait();
                if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                    DBAppointment.deleteAppointment(modifyAppointment.getApptId());
                    apptThisMonthTable.setItems(DBAppointment.getAllAppointmentsThisMonth());

                    Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Appointment Deleted", "Deletion Confirmed", modifyAppointment.getApptId() +" | "+modifyAppointment.getApptType());
                    onDelete.showAndWait();
                }
            }catch (NullPointerException n2){
                try {
                    modifyAppointment = apptThisWeekTable.getSelectionModel().getSelectedItem();
                    System.out.println(modifyAppointment.getApptTitle());

                    Alert confirmDelete = GeneralController.alertUser(Alert.AlertType.CONFIRMATION,"Delete Appointment","Continue Deleting Appointment?:", modifyAppointment.getApptTitle()+" | "+modifyAppointment.getApptStart()+" - "+modifyAppointment.getApptEnd());
                    Optional<ButtonType> confirm = confirmDelete.showAndWait();
                    if (confirm.isPresent()&&confirm.get()==ButtonType.OK){
                        DBAppointment.deleteAppointment(modifyAppointment.getApptId());
                        apptThisWeekTable.setItems(DBAppointment.getAllAppointmentsThisWeek());

                        Alert onDelete = GeneralController.alertUser(Alert.AlertType.INFORMATION,"Appointment Deleted", "Deletion Confirmed", modifyAppointment.getApptId() +" | "+modifyAppointment.getApptType());
                        onDelete.showAndWait();
                    }
                }catch (NullPointerException n3){
                    n3.printStackTrace();
                }
            }
        }



    }

    ///////////////////////////////////////////////Getters and Setters/////////////////////////////////////////////////

    /**
     * Getter for modifyCustomer variable.
     * @return Returns modifyCustomer
     */
    public static Customer getModifyCustomer() {
        return modifyCustomer;
    }

    /**
     * setter for modifyCustomer variable.
     * @param modifyCustomer setter for modify customer variable
     */
    public static void setModifyCustomer(Customer modifyCustomer) {
        MainScreenController.modifyCustomer = modifyCustomer;
    }

    /**
     * Getter for modifyAppointment variable.
     * @return Returns modifyAppointment
     */
    public static Appointment getModifyAppointment() {
        return modifyAppointment;
    }

    /**
     * Setter for modifyAppointment variable.
     * @param modifyAppointment setter for modify appointment variable
     */
    public static void setModifyAppointment(Appointment modifyAppointment) {
        MainScreenController.modifyAppointment = modifyAppointment;
    }

    /**
     * Action that opens a new tab in the Main tab view pane with the 'Appointments by Type' report.
     * @param actionEvent action event for Appointments by Type Report
     * @throws IOException signals that an I/O exception has occurred
     */
    public void apptsByTypeReport(ActionEvent actionEvent) throws IOException {
        GeneralController.showReportTab(mainTabPane,"Appointments By Type","ReportOne");
    }

    /**
     * Action that opens a new tab in the Main tab view pane with the 'Contact Schedule' report.
     * @param actionEvent action event for Contact Schedule Report
     * @throws IOException signals that an I/O exception has occurred
     */
    public void contactScheduleReport(ActionEvent actionEvent) throws IOException {
        GeneralController.showReportTab(mainTabPane,"Contact Schedules","ReportTwo");
    }

    /**
     * Action that opens a new tab in the Main tab view pane with the 'Total Appointments by Type' report.
     * @param actionEvent action event for Total Appointments by Customer report
     * @throws IOException signals that an I/O exception has occurred
     */
    public void totalApptsByCustomer(ActionEvent actionEvent) throws IOException {
        GeneralController.showReportTab(mainTabPane,"Total Appts. By Customer","ReportThree");
    }
}
