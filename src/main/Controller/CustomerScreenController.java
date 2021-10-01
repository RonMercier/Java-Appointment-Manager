package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Controller.Util.GeneralController;
import main.DAO.DBCountry;
import main.DAO.DBCustomers;
import main.DAO.DBFirstLevelDivision;
import main.Exception.ValidationException;
import main.Model.Country;
import main.Model.Customer;
import main.Model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ron Mercier - 001406973
 *
 * The customer screen controller handles all processes related to adding and modifying customers.  Lambda Expressions
 * were implemented in this controller to simplify the process of handling customer data in the database.
 */
public class CustomerScreenController implements Initializable {
    public Label headerLbl;
    public TextField id_textfield;
    public TextField name_textfield;
    public TextField address_textfield;
    public TextField zipcode_textfield;
    public TextField phone_textfield;
    public ChoiceBox<Country> country_choicebox;
    public ChoiceBox<FirstLevelDivision> division_choicebox;
    public Label countryLbl;
    public Label divisionLbl;


    public Customer customerToModify;
    ObservableList<Country> allCountries =FXCollections.observableArrayList();
    ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
    ObservableList<FirstLevelDivision> divisionsByCountryId = FXCollections.observableArrayList();

    CustomerInterface modifyCustomer = ()-> new Customer(
            Integer.parseInt(id_textfield.getText()),
            name_textfield.getText(),
            address_textfield.getText(),
            zipcode_textfield.getText(),
            phone_textfield.getText(),
            division_choicebox.getValue().getDivisionId()
    );

    CustomerInterface addCustomer = ()-> new Customer(
            name_textfield.getText(),
            address_textfield.getText(),
            zipcode_textfield.getText(),
            phone_textfield.getText(),
            division_choicebox.getValue().getDivisionId()
    );

    /**
     * Initializes the Customer screen and populates the combo boxes with data from the Country and
     * FirstLevelDivision info from the database.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allCountries.setAll(DBCountry.getAllCountries());
        divisions.setAll(DBFirstLevelDivision.getAllDivisions());

        for (Country c: allCountries){
            country_choicebox.getItems().add(c);
        }

        for (FirstLevelDivision d: divisions){
            division_choicebox.getItems().add(d);
        }
        setCustomerToModify(MainScreenController.getModifyCustomer());



        if (customerToModify != null){

            headerLbl.setText("Modify Customer");
            id_textfield.setText(String.valueOf(customerToModify.getCustomerId()));
            name_textfield.setText(customerToModify.getCustomerName());
            address_textfield.setText(customerToModify.getCustomerAddress());
            zipcode_textfield.setText(customerToModify.getCustomerZipcode());
            phone_textfield.setText(customerToModify.getCustomerPhone());
            countryLbl.setText("Country ");
            country_choicebox.setValue(getCountryById(customerToModify.getCustomerCountry()));

            division_choicebox.setItems(getDivisionsByCountryId(customerToModify.getCustomerCountry()));

            division_choicebox.setValue(getDivisionById(customerToModify.getCustomerDivision()));

            divisionLbl.setText("Division ");


//        This lambda expression shows which division data will be shown based on the user's country choice.
            country_choicebox.getSelectionModel().selectedItemProperty().addListener((observableValue, country, t1) -> {
                divisionsByCountryId.setAll(getDivisionsByCountryId(observableValue.getValue().getCountryId()));
                division_choicebox.getItems().removeAll();
                division_choicebox.setItems(divisionsByCountryId);
            });

        }
        else {
            headerLbl.setText("Add Customer");

            division_choicebox.setDisable(true);
            //This lambda expression shows which division data will be shown based on the user's country choice.
            country_choicebox.getSelectionModel().selectedItemProperty().addListener((observableValue, country, t1) -> {
                divisionsByCountryId.setAll(getDivisionsByCountryId(observableValue.getValue().getCountryId()));
                division_choicebox.setDisable(false);
                division_choicebox.getItems().removeAll();
                division_choicebox.setItems(divisionsByCountryId);
            });
        }
    }

    /**
     * The save method for the customer form is used to add new customers or modify existing customers in the database.
     *
     * @param actionEvent action event to save customer to database
     * @throws IOException for save method
     */
    public void save(ActionEvent actionEvent) throws IOException {



                if (customerToModify != null) {

                try{
                    custFormValidation();

                    Customer c = modifyCustomer.newCustomer();

                    try {
                        c.isValid();
                        DBCustomers.modifyCustomer(c);
                        GeneralController.changeTab(actionEvent,"Main");
                    }catch (ValidationException v){
                        Alert alert = GeneralController.alertUser(Alert.AlertType.ERROR,"Validation Error","Wrong Input", v.getMessage());
                        alert.showAndWait();
                    }
                }catch (NullPointerException n){
                    Alert alert = GeneralController.alertUser(Alert.AlertType.ERROR, "Validation Error", "Invalid Form Data", n.getMessage());
                    alert.showAndWait();
                }

                }
                else {

                    try {
                        custFormValidation();
                        Customer c = addCustomer.newCustomer();

                        try {
                            c.isValid();
                            DBCustomers.addCustomer(c);
                            GeneralController.changeTab(actionEvent,"Main");
                        }catch (ValidationException v){
                            Alert alert = GeneralController.alertUser(Alert.AlertType.ERROR, "Validation Error", "Wrong Input", v.getMessage());
                            alert.showAndWait();
                        }



                    }catch (NullPointerException n){
                        Alert alert = GeneralController.alertUser(Alert.AlertType.ERROR, "Validation Error", "Invalid Form Data", n.getMessage());
                        alert.showAndWait();
                    }
                }




    }

    /**
     * This cancel action brings the user back to the main page.
     *
     * @param actionEvent action event for cancel method
     * @throws IOException exception for cancel method.
     */
    public void cancel(ActionEvent actionEvent) throws IOException {

        GeneralController.changeTab(actionEvent,"Main");

    }

    //Getter and Setters

    /**
     *
     * @return customerToModify
     */
    public Customer getCustomerToModify() {
        return customerToModify;
    }


    /**
     *
     * @param customerToModify customerToModify
     */
    public void setCustomerToModify(Customer customerToModify) {
        this.customerToModify = customerToModify;
    }


    /**
     * This method checks that no form fields were left blank.
     * @return True if all form fields are filled
     * @throws NullPointerException exception for customer form validation
     */
    public boolean custFormValidation() throws NullPointerException{

        if (name_textfield.getText().equals("")){
            throw new NullPointerException("Name field cannot be empty");
        }
        if (address_textfield.getText().equals("")){
            throw new NullPointerException("Address field cannot be empty");
        }
        if (zipcode_textfield.getText().equals("")){
            throw new NullPointerException("Zipcode field cannot be empty");
        }
        if (phone_textfield.getText().equals("")){
            throw new NullPointerException("Phone field cannot be empty");
        }
        if (country_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Country field cannot be empty");
        }
        if (division_choicebox.getValue().toString().equals("")){
            throw new NullPointerException("Division field cannot be empty");
        }


        return true;
    }

    /**
     * Filters through the countries ObservableList and returns the country with the provided Country_ID.
     * @param id ID of requested country.
     * @return Country with provided Country_ID.
     */
    private Country getCountryById(int id){
        Country country = null;

        for (Country c :
                allCountries) {
            if (c.getCountryId() != id){
                continue;
            }else {
                country = c;
            }
        }
        return country;
    }

    /**
     * Filters through the list of divisions and returns the one with the provided Division_ID.
     * @param id ID of requested division.
     * @return Division with provided Division_ID.
     */
    private FirstLevelDivision getDivisionById(int id){
        FirstLevelDivision fld = null;
        for (FirstLevelDivision f :
                divisions) {
            if (f.getDivisionId() != id){
                continue;
            }else {
                fld = f;
            }
        }
        return fld;
    }

    /**
     * Filters through the list of divisions and returns the one with the provided Country_ID.
     * @param id ID of requested Country.
     * @return Divisions with provided Country_ID.
     */
    private ObservableList<FirstLevelDivision> getDivisionsByCountryId(int id){
        ObservableList<FirstLevelDivision> fldList = FXCollections.observableArrayList();

        for (FirstLevelDivision f: divisions
             ) {
            if (f.getCountryId() != id){
                continue;
            }else {
                fldList.add(f);
            }
        }
        return fldList;
    }
}
