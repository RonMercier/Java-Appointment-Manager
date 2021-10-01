package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Model.Report;
import main.Util.DBConnection;
import main.Util.DBQuery;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Ron Mercier - 001406973
 *
 * This class controls and handles all processes related to the 'ReportTwo.fxml' page.
 */
public class ReportTwoController implements Initializable {
//////////////////////////////////////////////Anika Costa Table////////////////////////////////////////////////////////
    public TableView<Report> CostaScheduleTable;
    public TableColumn<Report,String> CostaContactName;
    public TableColumn<Report,String> CostaAppointmentID;
    public TableColumn<Report,String> CostaAppointmentTitle;
    public TableColumn<Report,String> CostaAppointmentDesc;
    public TableColumn<Report,String> CostaAppointmentStart;
    public TableColumn<Report,String> CostaAppointmentEnd;
    public TableColumn<Report,String> CostaCustomerID;

//////////////////////////////////////////Daniel Garcia Table//////////////////////////////////////////////////////////
    public TableView<Report> GarciaScheduleTable;
    public TableColumn<Report,String> GarciaContactName;
    public TableColumn<Report,String> GarciaAppointmentID;
    public TableColumn<Report,String> GarciaAppointmentTitle;
    public TableColumn<Report,String> GarciaAppointmentDesc;
    public TableColumn<Report,String> GarciaAppointmentStart;
    public TableColumn<Report,String> GarciaAppointmentEnd;
    public TableColumn<Report,String> GarciaCustomerID;

//////////////////////////////////////////Li Lee Table/////////////////////////////////////////////////////////////////
    public TableView<Report> LeeScheduleTable;
    public TableColumn<Report,String> LeeContactName;
    public TableColumn<Report,String> LeeAppointmentID;
    public TableColumn<Report,String> LeeAppointmentTitle;
    public TableColumn<Report,String> LeeAppointmentDesc;
    public TableColumn<Report,String> LeeAppointmentStart;
    public TableColumn<Report,String> LeeAppointmentEnd;
    public TableColumn<Report,String> LeeCustomerID;

    /**
     * This initialize method holds tbe table build methods
     * @param url Uniform Resource Locator, a pointer to a "resource" on the World Wide Web.
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CostaTableBuild();
        GarciaTableBuild();
        LeeTableBuild();
    }

    /**
     *Builds the table for Contact one
     */
    public void CostaTableBuild(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 1 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        CostaScheduleTable.setItems(reportFilteredList);
        CostaContactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        CostaAppointmentID.setCellValueFactory(new PropertyValueFactory<>("var2"));
        CostaAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        CostaAppointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        CostaAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        CostaAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        CostaCustomerID.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }

    /**
     *Builds the table for Contact two
     */
    public void GarciaTableBuild(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 2 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        GarciaScheduleTable.setItems(reportFilteredList);
        GarciaContactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        GarciaAppointmentID.setCellValueFactory(new PropertyValueFactory<>("var2"));
        GarciaAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        GarciaAppointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        GarciaAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        GarciaAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        GarciaCustomerID.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }

    /**
     *Builds the table for Contact three
     */
    public void LeeTableBuild(){
        String getStatement = "select con.Contact_Name, apt.Appointment_ID , apt.Title, apt.Description,apt.Start,apt.End, Customer_ID\n" +
                "from appointments apt join contacts con on apt.Contact_ID = con.Contact_ID where apt.Contact_ID = 3 order by con.Contact_ID, apt.Start;";
        ObservableList<Report> reportResults = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs != null){
                while (rs.next()){
                    Report r = new Report(
                            rs.getString(1),
                            String.valueOf(rs.getInt(2)),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getTimestamp(5)),
                            String.valueOf(rs.getTimestamp(6)),
                            String.valueOf(rs.getInt(7))
                    );
                    reportResults.add(r);
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }

        FilteredList<Report> reportFilteredList = new FilteredList<>(reportResults);
        LeeScheduleTable.setItems(reportFilteredList);
        LeeContactName.setCellValueFactory(new PropertyValueFactory<>("var1"));
        LeeAppointmentID.setCellValueFactory(new PropertyValueFactory<>("var2"));
        LeeAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("var3"));
        LeeAppointmentDesc.setCellValueFactory(new PropertyValueFactory<>("var4"));
        LeeAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("var5"));
        LeeAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("var6"));
        LeeCustomerID.setCellValueFactory(new PropertyValueFactory<>("var7"));



    }


}
