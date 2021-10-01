package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Controller.LoginScreenController;
import main.Model.Customer;
import main.Util.DBConnection;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    /**
     * This method creates a new customer in the database
     * @param customer Customer object to be added to the database
     */
    public static void addCustomer(Customer customer){
        String getStatement = "insert into customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)\n" +
                "values (?,?,?,?,NOW(),?,NOW(),?,?);";
        try{
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3,customer.getCustomerZipcode());
            ps.setString(4, customer.getCustomerPhone());
            ps.setString(5, LoginScreenController.getGlobalUser().getUserName());
            ps.setString(6, LoginScreenController.getGlobalUser().getUserName());
            ps.setInt(7,customer.getCustomerDivision());

            ps.execute();

            if (ps.getUpdateCount()>0){


                System.out.println(ps.getUpdateCount()+ " row(s) affected.");
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * This method retrieves a list of all customer from the database.
     * @return ObservableList of all Customer objects
     */
    public static ObservableList<Customer> getAllCustomers(){
        String getCustomerStatement = "SELECT Customer_ID,Customer_Name,Address,Postal_Code,Phone, division_id FROM customers";
        Customer customerResult;
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getCustomerStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();


            while (rs.next()){
                customerResult = new Customer(
                        rs.getInt("Customer_Id"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getInt("Division_ID")
                );
                allCustomers.add(customerResult);
            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allCustomers;
    }

    /**
     * This method retrieves all customers from the database and joins the country and division names from their
     * associated tables using the Division and Country id's
     * @return returns all customers with division and countries
     */
    public static ObservableList<Customer> getAllCustomersWithDivisionAndCountries(){
        String getCustomerStatement = "SELECT c.Customer_ID,c.Customer_Name,c.Address,c.Postal_Code,c.Phone,f.division, co.Country,c.Division_ID,f.COUNTRY_ID\n" +
                "FROM customers c\n" +
                "    join first_level_divisions f on c.Division_ID = f.Division_ID\n" +
                "    join countries co on f.Country_ID = co.country_id;";
        Customer customerResult;
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getCustomerStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();


            while (rs.next()){
                customerResult = new Customer(
                        rs.getInt("Customer_Id"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Division"),
                        rs.getString("Country"),
                        rs.getInt("Division_ID"),
                        rs.getInt("Country_ID")
                );
                allCustomers.add(customerResult);
            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allCustomers;
    }

    /**
     * This method updates a current customer in the database
     * @param customer Customer object to be updated from the database
     */
    public static void modifyCustomer(Customer customer){

        String getStatement = "update customers set Customer_Name = ?,\n" +
                "                     Address = ?,\n" +
                "                     Postal_Code = ?,\n" +
                "                     Phone = ?,\n" +
                "                     Last_Update = NOW(),\n" +
                "                     Last_Updated_By=?,\n" +
                "                     Division_Id = ?\n" +
                "                    where Customer_ID = ?;";


        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(), getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerZipcode());
            ps.setString(4, customer.getCustomerPhone());
            ps.setString(5, LoginScreenController.getGlobalUser().getUserName());
            ps.setInt(6,customer.getCustomerDivision());
            ps.setInt(7, customer.getCustomerId());
            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+" row(s) affected.");
            }
        }catch (SQLException s){
            s.printStackTrace();

        }

    }

    /**
     * This method deletes a customer from the database using the provided id.
     * @param Id Customer object to be deleted from the database
     */
    public static void deleteCustomer(int Id){
        String getStatement = "delete from customers where Customer_ID = ?";

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,Id);
            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+" row(s) affected.");
            }
        }catch (SQLException s){
            System.out.println("Nothing Deleted");
        }
    }

}
