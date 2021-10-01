package main.Model;

import main.Exception.ValidationException;
/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all Customer objects
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerZipcode;
    private String customerPhone;
    private int customerDivision;
    private String customerDivisionText;
    private int customerCountry;
    private String customerCountryText;

    /**
     * Args Constructor
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivision Customer Division
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerZipcode, String customerPhone, int customerDivision) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivision = customerDivision;
    }

    /**
     * Args Constructor
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivision Customer Division
     * @param customerCountry Customer Country
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerZipcode, String customerPhone, int customerDivision, int customerCountry) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivision = customerDivision;
        this.customerCountry = customerCountry;
    }

    /**
     * All Args Constructor
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivisionText Customer Division (Text)
     * @param customerCountryText Customer Country (Text)
     * @param customerDivision Customer Division
     * @param customerCountry Customer Country
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerZipcode, String customerPhone, String customerDivisionText, String customerCountryText,int customerDivision,int customerCountry) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivisionText = customerDivisionText;
        this.customerCountryText = customerCountryText;
        this.customerCountry = customerCountry;
        this.customerDivision = customerDivision;
    }

    /**
     * Args Constructor
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivisionText Customer Division (Text)
     * @param customerCountryText Customer Country (Text)
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerZipcode, String customerPhone, String customerDivisionText, String customerCountryText) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivisionText = customerDivisionText;
        this.customerCountryText = customerCountryText;
    }

    /**
     * Args Constructor
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerZipcode, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
    }

    /**
     * Args Constructor

     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivision Customer Division
     */
    public Customer(String customerName, String customerAddress, String customerZipcode, String customerPhone, int customerDivision) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivision = customerDivision;
    }

    /**
     * Args Constructor
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerZipcode Customer Zipcode
     * @param customerPhone Customer Phone
     * @param customerDivisionText Customer Division (Text)
     */
    public Customer(String customerName, String customerAddress, String customerZipcode, String customerPhone, String customerDivisionText) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipcode = customerZipcode;
        this.customerPhone = customerPhone;
        this.customerDivisionText = customerDivisionText;
    }


    /**
     *
     * @return Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId Customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return Customer Name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName Customer Name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return Customer Address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @param customerAddress Customer Address
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * @return Customer Zipcode
     */
    public String getCustomerZipcode() {
        return customerZipcode;
    }

    /**
     *
     * @param customerZipcode Customer Zipcode
     */
    public void setCustomerZipcode(String customerZipcode) {
        this.customerZipcode = customerZipcode;
    }

    /**
     *
     * @return Customer Phone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *
     * @param customerPhone Customer Phone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     *
     * @return Customer Division
     */
    public int getCustomerDivision() {
        return customerDivision;
    }

    /**
     *
     * @param customerDivision Customer Division
     */
    public void setCustomerDivision(int customerDivision) {
        this.customerDivision = customerDivision;
    }

    /**
     *
     * @return Customer Division as Text
     */
    public String getCustomerDivisionText() {
        return customerDivisionText;
    }

    /**
     *
     * @param customerDivisionText Customer Division as Text
     */
    public void setCustomerDivisionText(String customerDivisionText) {
        this.customerDivisionText = customerDivisionText;
    }

    /**
     *
     * @return Customer Country
     */
    public int getCustomerCountry() {
        return customerCountry;
    }

    /**
     *
     * @param customerCountry Customer Country
     */
    public void setCustomerCountry(int customerCountry) {
        this.customerCountry = customerCountry;
    }

    /**
     *
     * @return Customer Country as Text
     */
    public String getCustomerCountryText() {
        return customerCountryText;
    }

    /**
     *
     * @param customerCountryText Customer Country as Text
     */
    public void setCustomerCountryText(String customerCountryText) {
        this.customerCountryText = customerCountryText;
    }

    /**
     * Custom toString method "#: customer name"
     * @return returns customer name
     */
    @Override
    public String toString() {
        return customerId+": "+customerName;
    }

    /**
     * Validation method
     * @return true if no exception is thrown. Otherwise, it alerts the user.
     * @throws ValidationException shows a message when an error has occurred
     */
    public boolean isValid() throws ValidationException {
        // Name is required
        if (getCustomerName().equals("")) {
            throw new ValidationException("The name field cannot be empty.");
        }

        // Address is required
        if (getCustomerAddress().equals("")) {
            throw new ValidationException("The inventory count must be greater than 0.");
        }

        // Zipcode is required
        if (getCustomerZipcode().equals("")) {
            throw new ValidationException("The price must be greater than $0");
        }

        // Phone is required
        if (getCustomerPhone().equals("")) {
            throw new ValidationException("The minimum inventory must be greater than 0.");
        }

        return true;
    }
}


