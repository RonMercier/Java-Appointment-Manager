package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Model.FirstLevelDivision;
import main.Util.DBConnection;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivision {
    /**
     * This method retrieves all divisions from the database which have a matching CountryID.
     * @param id CountryID is used to search for the associated Division.
     * @return ObservableList of all Divisions with the provided CountryID
     */
    public static ObservableList<FirstLevelDivision> getAllDivisionsByCountryId(int id){
        String getStatement = "select Division,Division_ID,Country_ID from first_level_divisions where country_id = ?;";
        ObservableList<FirstLevelDivision> fldList = FXCollections.observableArrayList();
        FirstLevelDivision fldResult;


        try {

            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                fldResult = new FirstLevelDivision(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                fldList.add(fldResult);
            }
            return fldList;
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
    }

    /**
     * This method retrieves all divisions.
     * @return ObservableList of all divisions in the database
     */
    public static ObservableList<FirstLevelDivision> getAllDivisions(){
        String getStatement = "SELECT Division_ID,Division,Country_ID FROM first_level_divisions";
        ObservableList<FirstLevelDivision> divisionResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();

            while (rs.next()){
                FirstLevelDivision division = new FirstLevelDivision(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                divisionResults.add(division);
            }
        }catch (SQLException sqlException){
            System.out.println("Couldnt get all divisions");
            return null;
        }

        return divisionResults;

    }

    /**
     * This method retrieves a division based on its id.
     * @param id Division_ID to be used in the database query
     * @return FirstLevelDivision object from the database
     */
    public static FirstLevelDivision getDivisionById(int id){
        String getStatement = "select Division_Id, Division, Country_Id from first_level_divisions where division_id = ?;";
        try {

            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                return new FirstLevelDivision(
                        rs.getInt("Division_Id"),
                        rs.getString("Division"),
                        rs.getInt("Country_Id")
                );

            }

        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
        return null;
    }
}
