package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Controller.Util.GeneralController;
import main.DAO.DBAppointment;
import main.DAO.DBContacts;
import main.DAO.DBCustomers;
import main.Exception.BusinessHoursException;
import main.Exception.ValidationException;
import main.Model.Appointment;
import main.Model.Contact;
import main.Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @author Ron Mercier - 001406973
 *
 * The Appointment Screen Controller handles all the processes related to customer appointments.  Lambda Expressions
 * were implemented in this controller to simplify the process of adding and modifying appointments to the
 * database.
 *
 *
 */
public class AppointmentScreenController implements Initializable {
    public Label headerLbl;
    public TextField apptIDTxt;
    public TextField title_textfield;
    public TextField description_txt;
    public TextField location_textfield;
    public ChoiceBox<Contact> contact_choicebox;
    public DatePicker start_datepicker;
    public ChoiceBox<LocalTime> start_time_combobox;
    public DatePicker end_datepicker;
    public ChoiceBox<LocalTime> end_time_combobox;
    public ChoiceBox<Customer> customer_combobox;
    public RadioButton urgent_radio;
    public RadioButton regular_radio;
    public Label contactLbl;
    public Label customerLbl;

    private final ObservableList<LocalTime> appointmentTimes = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();

    AppointmentInterface modifyAppointment = (type, start, end) -> new Appointment(
            Integer.parseInt(apptIDTxt.getText()),
            title_textfield.getText(),
            description_txt.getText(),
            location_textfield.getText(),
            contact_choicebox.getValue().getContactId(),
            type,
            Timestamp.valueOf(start),
            Timestamp.valueOf(end),
            customer_combobox.getValue().getCustomerId()

    );
    AppointmentInterface addAppointment = ((type,start, end) -> new Appointment(
            title_textfield.getText(),
            description_txt.getText(),
            location_textfield.getText(),
            contact_choicebox.getValue().getContactId(),
            type,
            Timestamp.valueOf(start),
            Timestamp.valueOf(end),
            customer_combobox.getValue().getCustomerId()

    ));



    public static Appointment selectedAppointment;

    /**
     * Initializes the Appointment form and changes the language based on the user's settings to either French or English.<br>
     * If the user's locale fall outside either of those regions, the default language is English.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerList.addAll(Objects.requireNonNull(DBCustomers.getAllCustomers()));
        contactList.addAll(Objects.requireNonNull(DBContacts.getAllContacts()));

        for (int i = 0; i < 24; i++) {
            appointmentTimes.add(LocalTime.of(i,0));
            appointmentTimes.add(LocalTime.of(i,15));
            appointmentTimes.add(LocalTime.of(i,30));
            appointmentTimes.add(LocalTime.of(i,45));

        }

        setSelectedAppointment(MainScreenController.getModifyAppointment());

        contact_choicebox.setItems(contactList);
        customer_combobox.setItems(customerList);

        start_time_combobox.setItems(appointmentTimes);
        start_time_combobox.setMaxWidth(75.0);
        end_time_combobox.setItems(appointmentTimes);
        end_time_combobox.setMaxWidth(75.0);


        if (selectedAppointment != null){
            LocalDateTime startDateTime = selectedAppointment.getApptStart().toLocalDateTime();
            LocalDateTime endDateTime = selectedAppointment.getApptEnd().toLocalDateTime();
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();

            headerLbl.setText("Modify Appointment");
            apptIDTxt.setText(String.valueOf(selectedAppointment.getApptId()));
            title_textfield.setText(selectedAppointment.getApptTitle());
            description_txt.setText(selectedAppointment.getApptDesc());
            location_textfield.setText(selectedAppointment.getApptLocation());

            contact_choicebox.setValue(getContactById(selectedAppointment.getApptContact()));
            customer_combobox.setValue(getCustomerById(selectedAppointment.getApptCustomerId()));


            start_datepicker.setValue(LocalDate.of(startDate.getYear(),startDate.getMonth(),startDate.getDayOfMonth()));
            end_datepicker.setValue(LocalDate.of(endDate.getYear(),endDate.getMonth(),endDate.getDayOfMonth()));
            start_time_combobox.setValue(LocalTime.of(startTime.getHour(),startTime.getMinute()));
            end_time_combobox.setValue(LocalTime.of(endTime.getHour(),endTime.getMinute()));

        }
        else{
            headerLbl.setText("Add Appointment");
        }

    }

    /**
     *
     * @param actionEvent Action event to save appointment to database
     * @throws IOException Exception for save method.
     *
     * The Save method is used to add new appointment or modify existing appointments in the database. Before
     * the appointment is saved, it checks that all fields are filled out and valid.  It also checks the
     * appointment time to make sure it is within business hours and doesn't overlap with an existing appointment.
     *
     */
    public void save(ActionEvent actionEvent) throws IOException {

        try {
            try{
                if (formValidation()){

                LocalDateTime start = LocalDateTime.of(start_datepicker.getValue(),start_time_combobox.getValue());
                LocalDateTime end = LocalDateTime.of(end_datepicker.getValue(),end_time_combobox.getValue());

                    if (selectedAppointment != null) {

                        if (urgent_radio.isSelected()) {

                            try {
                                Appointment appointment = modifyAppointment.newAppointment("Urgent",start,end);


                                if (appointment.isValid() && appointment.isValidTime()) {
                                    if (isModifiedAppointmentOverlapping(appointment.getApptStart().toLocalDateTime().plusSeconds(1),appointment.getApptEnd().toLocalDateTime().minusSeconds(1),appointment.getApptId())){
                                        DBAppointment.modifyAppointment(appointment);
                                        GeneralController.changeTab(actionEvent, "Main");
                                    }
                                }
                            }catch (BusinessHoursException b){
                                Alert a = GeneralController.alertUser(Alert.AlertType.ERROR,"Error","Overlapping appointments",b.getMessage());
                                a.showAndWait();
                            }
                        }
                        if (regular_radio.isSelected()) {

                            try {
                                Appointment appointment = modifyAppointment.newAppointment("Regular",start,end);


                                if (appointment.isValid() && appointment.isValidTime()) {
                                    if (isModifiedAppointmentOverlapping(appointment.getApptStart().toLocalDateTime().plusSeconds(1),appointment.getApptEnd().toLocalDateTime().minusSeconds(1),appointment.getApptId())){
                                        DBAppointment.modifyAppointment(appointment);
                                        GeneralController.changeTab(actionEvent, "Main");
                                    }
                                }
                            }catch (BusinessHoursException b){
                                Alert a = GeneralController.alertUser(Alert.AlertType.ERROR,"Error","Overlapping appointments",b.getMessage());
                                a.showAndWait();
                            }
                        }
                    } else {

                        if (urgent_radio.isSelected()) {

                            try {
                                Appointment appointment = addAppointment.newAppointment("Urgent",start,end);


                                if (appointment.isValid() && appointment.isValidTime()) {
                                    if (isAppointmentOverlapping(appointment.getApptStart().toLocalDateTime().plusSeconds(1),appointment.getApptEnd().toLocalDateTime().minusSeconds(1))){
                                        DBAppointment.addAppointment(appointment);
                                        GeneralController.changeTab(actionEvent, "Main");
                                    }
                                }
                            }catch (BusinessHoursException b){
                                Alert a = GeneralController.alertUser(Alert.AlertType.ERROR,"Error","Overlapping appointments",b.getMessage());
                                a.showAndWait();
                            }
                        }
                        if (regular_radio.isSelected()) {

                            try {
                                Appointment appointment = addAppointment.newAppointment("Regular",start,end);


                                if (appointment.isValid() && appointment.isValidTime()) {
                                    if (isAppointmentOverlapping(appointment.getApptStart().toLocalDateTime().plusSeconds(1),appointment.getApptEnd().toLocalDateTime().minusSeconds(1))){
                                        DBAppointment.addAppointment(appointment);
                                        GeneralController.changeTab(actionEvent, "Main");
                                    }
                                }
                            }catch (BusinessHoursException b){
                                Alert a = GeneralController.alertUser(Alert.AlertType.ERROR,"Error","Overlapping appointments",b.getMessage());
                                a.showAndWait();
                            }
                        }
                    }
                }
            }catch (NullPointerException n){
                n.printStackTrace();

                Alert alert = GeneralController.alertUser(Alert.AlertType.WARNING,"Empty Form Field","There is an error in the form:",n.getMessage());
                alert.showAndWait();
            }
        }catch (ValidationException validationException){
            Alert alert = GeneralController.alertUser(Alert.AlertType.WARNING,"Validation Exception","There is an error in the form:",validationException.getMessage());
            alert.showAndWait();
        }

    }

    /**
     *
     * @param actionEvent Action event to cancel current process
     * @throws IOException  Exception for cancel method
     *
     * Cancel event that brings the user back to the main page.
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        GeneralController.changeTab(actionEvent,"Main");
    }


    /**
     * Getter for selected appointment variable.
     *
     * @return Returns selectedAppointment
     */
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    /**
     *
     * @param selectedAppointment setter for selected appointment.
     */
    public static void setSelectedAppointment(Appointment selectedAppointment) {
        AppointmentScreenController.selectedAppointment = selectedAppointment;
    }

    /**
     * The form validation method checks that all fields are filled.
     *
     * @return returns true if all form fields are filled
     * @throws NullPointerException NullPointerException
     */
    public boolean formValidation() throws NullPointerException{

        if (title_textfield.getText().equals("")){
            throw new NullPointerException("Title field cannot be empty");
        }
        if (description_txt.getText().equals("")){
            throw new NullPointerException("Description field cannot be empty");
        }
        if (location_textfield.getText().equals("")){
            throw new NullPointerException("Location field cannot be empty");
        }
        if (contact_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Contact choice cannot be empty");
        }
        if (start_datepicker.getValue()==null){
            throw new NullPointerException("Start date field cannot be empty");
        }
        if (start_time_combobox.getValue().toString().equals("")){
            throw new NullPointerException("Start time field cannot be empty");
        }
        if (end_datepicker.getValue()==null){
            throw new NullPointerException("End date field cannot be empty");
        }
        if (end_time_combobox.getValue().toString().equals("")){
            throw new NullPointerException("End time field cannot be empty");
        }
        if (customer_combobox.getValue().toString().equals("")){
            throw new NullPointerException("Customer choice cannot be empty");
        }

        return true;
    }

    /**
     * Validates that the appointment being added does not overlap with an existing appointment in the database.
     * @param start Start time
     * @param end End Time
     * @return true if appointment does not overlap another appointment.
     * @throws BusinessHoursException used for appointment overlap
     */
    public boolean isAppointmentOverlapping(LocalDateTime start,LocalDateTime end) throws BusinessHoursException {

        if (DBAppointment.isOverlapping(start,end).size()>=1){
            throw new BusinessHoursException("An appointment cannot be scheduled at the same time as another appointment.");
        }
        return true;
    }

    /**
     * Validates that the appointment being modified does not overlap with an existing appointment in the database.
     * @param start Start time
     * @param end End time
     * @param id Appointment id
     * @return returns true if exception is found
     * @throws BusinessHoursException Exception for business hours
     */
    public boolean isModifiedAppointmentOverlapping(LocalDateTime start, LocalDateTime end, int id) throws BusinessHoursException {

        if (DBAppointment.isOverlapping(start,end).size()>=1){
            if (DBAppointment.isOverlapping(start,end).get(0).getApptId() != id)
                throw new BusinessHoursException("An appointment cannot be scheduled at the same time as another appointment.");
        }
        return true;
    }

    /**
     * Filters through the contactList and returns the contact with the provided Contact_ID.
     * @param id ID of requested contact.
     * @return Contact with provided ID.
     */
    private Contact getContactById(int id){
            Contact con = null;

        for (Contact c: contactList
             ) {
            if (c.getContactId() != id){
                continue;
            }
            else {
                con = c;
                break;
            }
        }
        return con;
    }

    /**
     * Filters through the customerList and returns the customer with the provided Customer_ID.
     * @param id ID of requested customer.
     * @return Customer with provided ID.
     */
    private Customer getCustomerById(int id){
            Customer cust = null;

        for (Customer c: customerList
             ) {
            if (c.getCustomerId() != id){
                continue;
            }
            else {
                cust = c;
            }
        }
        return cust;
    }

}
