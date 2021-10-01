package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Model.Country;
import main.Util.DBConnection;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {
    /**
     * Gets all countries from the database
     * @return Returns all countries from the database.
     */
    public static ObservableList<Country> getAllCountries(){
        String getStatement = "select Country_ID,Country from countries;";
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        Country countryResult;


        try {

            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                countryResult = new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country")
                );
                countryList.add(countryResult);
            }
            return countryList;
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param id country id
     * @return Returns a country from the database by their id
     */
    public static Country getCountryById(int id){
        String getStatement = "select Country_ID,Country from countries where country_id = ?;";
        try {

            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                return new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country")
                );

            }

        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
        return null;
    }
}
