package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Controller.LoginScreenController;
import main.Model.Appointment;
import main.Util.DBConnection;
import main.Util.DBQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointment {

    /**
     * This method returns an appointment based on the appointment id
     * @param id ID of selected Appointment
     * @return returns an appointment based on the appointment id
     */
    public static Appointment getAppointment(int id){

        String getCustomerStatement = "SELECT * FROM appointments where Customer_ID = ?";
        Appointment appointmentResult;

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getCustomerStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,String.valueOf(id));
            ps.execute();
            ResultSet rs = ps.getResultSet();
            appointmentResult = new Appointment(
                    rs.getInt("Appointment_ID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    rs.getInt("Contact_ID"),
                    rs.getString("Type"),
                    rs.getTimestamp("Start"),
                    rs.getTimestamp("End"),
                    rs.getInt("Customer_ID")
            );

            return appointmentResult;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }

    /**
     * This method returns a list of all appointments from the database.
     * @return All appointments in the database
     */
    public static ObservableList<Appointment> getAllAppointments(){
        String getStatement = "select Appointment_ID,Title,Description,Location,Contact_Id,Type,Start,End,Customer_ID from appointments;";
        Appointment allAppointmentResult;
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                allAppointmentResult = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start"),
                        rs.getTimestamp("End"),
                        rs.getInt("Customer_ID")
                );

                allAppointmentResult.setApptStart(Timestamp.valueOf(allAppointmentResult.getApptStart().toLocalDateTime()));
                allAppointmentResult.setApptEnd(Timestamp.valueOf(allAppointmentResult.getApptEnd().toLocalDateTime()));

                allAppointments.add(allAppointmentResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allAppointments;
    }

    /**
     * This method returns a list of all appointments scheduled for the current week from the database.
     * @return All appointments from the database during this week
     */
    public static ObservableList<Appointment> getAllAppointmentsThisWeek(){
        String getStatement = "select Appointment_ID,Title,Description,Location,Contact_Id,Type,Start,End,Customer_ID\n" +
                "from appointments where Start >CURDATE() and Start < CURDATE() + interval 7 day;";
        Appointment appointmentWeekResult;
        ObservableList<Appointment> allAppointmentsThisWeek = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                appointmentWeekResult = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start"),
                        rs.getTimestamp("End"),
                        rs.getInt("Customer_ID")
                );
                appointmentWeekResult.setApptStart(Timestamp.valueOf(appointmentWeekResult.getApptStart().toLocalDateTime()));
                appointmentWeekResult.setApptEnd(Timestamp.valueOf(appointmentWeekResult.getApptEnd().toLocalDateTime()));

                allAppointmentsThisWeek.add(appointmentWeekResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allAppointmentsThisWeek;
    }

    /**
     * This method returns a list of all appointments scheduled for the current month from the database.
     * @return All appointments from the database during this month
     */
    public static ObservableList<Appointment> getAllAppointmentsThisMonth(){
        String getStatement = "select Appointment_ID,Title,Description,Location,Contact_Id,Type,Start,End,Customer_ID\n" +
                "from appointments where Start >CURDATE() and Start < CURDATE() + interval 30 day;";
        Appointment appointmentMonthResult;
        ObservableList<Appointment> allAppointmentsThisWeek = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                appointmentMonthResult = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start"),
                        rs.getTimestamp("End"),
                        rs.getInt("Customer_ID")
                );
                appointmentMonthResult.setApptStart(Timestamp.valueOf(appointmentMonthResult.getApptStart().toLocalDateTime()));
                appointmentMonthResult.setApptEnd(Timestamp.valueOf(appointmentMonthResult.getApptEnd().toLocalDateTime()));

                allAppointmentsThisWeek.add(appointmentMonthResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allAppointmentsThisWeek;
    }

    /**
     * This method check to see if there are any upcoming appointments in the next 15 minutes.
     * @return Returns true if there is an appointment within 15 minutes of current request time.
     */
    public static ObservableList<Appointment> isAppointmentInNext15Minutes(){
        String getStatement = "select Appointment_ID,Title,Description,Location,Contact_ID,Type,Start,End,Customer_ID\n" +
                "                from appointments where Start >= curtime() and Start <= curtime() + interval 15 minute ;";
        Appointment appointmentWeekResult;
        ObservableList<Appointment> allAppointmentsSoon = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                appointmentWeekResult = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start"),
                        rs.getTimestamp("End"),
                        rs.getInt("Customer_ID")
                );
                allAppointmentsSoon.add(appointmentWeekResult);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return allAppointmentsSoon;



    }

    /**
     * This method adds an appointment to the database once the form is filled out with valid info and submitted.
     * @param appointment Appointment object to be stored in the database
     */
    public static void addAppointment(Appointment appointment){

        String getStatement = "insert into appointments(Title, Description, Location, Type, Start,End,Create_Date, Created_By,Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
                "values (?,?,?,?,?,?,NOW(),?,NOW(),?,?,?,?);";

        try{
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,appointment.getApptTitle());
            ps.setString(2,appointment.getApptDesc());
            ps.setString(3, appointment.getApptLocation());
            ps.setString(4, appointment.getApptType());
            ps.setTimestamp(5,appointment.getApptStart());
            ps.setTimestamp(6,appointment.getApptEnd());
            ps.setString(7, LoginScreenController.getGlobalUser().getUserName());
            ps.setString(8, LoginScreenController.getGlobalUser().getUserName());
            ps.setInt(9,appointment.getApptCustomerId());
//            TODO: Setup loginDAO and make this update User_ID
            ps.setInt(10,1);
            ps.setInt(11,appointment.getApptContact());

            ps.execute();
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(ps.toString());
            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row affected.");
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    /**
     * This method updates an appointment by the appointment id
     * @param appointment Appointment object to be stored in the database
     */
    public static void modifyAppointment(Appointment appointment){
        String getStatement = "update appointments set Title = ?,\n" +
                "                        Description = ?,\n" +
                "                        Location = ?,\n" +
                "                        Type = ?,\n" +
                "                        Start = ?,\n" +
                "                        End = ?,\n" +
                "                        Last_Update = NOW(),\n" +
                "                        Last_Updated_By = ?,\n" +
                "                        Customer_ID = ?,\n" +
                "                        User_ID = ?,\n" +
                "                        Contact_ID = ? where Appointment_ID = ?;";


        try{
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);

            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,appointment.getApptTitle());
            ps.setString(2, appointment.getApptDesc());
            ps.setString(3,appointment.getApptLocation());
            ps.setString(4,appointment.getApptType());
            ps.setTimestamp(5,appointment.getApptStart());
            ps.setTimestamp(6,appointment.getApptEnd());
            ps.setString(7, LoginScreenController.getGlobalUser().getUserName());
            ps.setInt(8,appointment.getApptCustomerId());
            ps.setInt(9,1);
            ps.setInt(10,appointment.getApptContact());
            ps.setInt(11,appointment.getApptId());

            ps.execute();

            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+ " row(s) affected.");
            }

        }catch (SQLException s){
            s.printStackTrace();
            System.out.println("Could not modify appointment Check DBAppointment");
        }



    }

    /**
     * This method deletes an appointment by the appointment id
     * @param id ID of the requested Appointment to be deleted
     */
    public static void deleteAppointment(int id){
        String getCustomerStatement = "DELETE FROM appointments where appointment_id = ?";

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getCustomerStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,String.valueOf(id));
            ps.execute();
            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount() + " row(s) deleted.");

            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("No appointment deleted.");
        }
    }

    /**
     * This method searches the appointments table and checks if any appointments have matching appointment times
     * @param start Starting date/time
     * @param end Ending date/time
     * @return A list of overlapping appointments
     */
    public static ObservableList<Appointment> isOverlapping(LocalDateTime start, LocalDateTime end) {

        String getStatement = "SELECT * FROM appointments "
                + "WHERE (start >= ? AND end <= ?) "
                + "OR (start <= ? AND end >= ?) "
                + "OR (start BETWEEN ? AND ? OR end BETWEEN ? AND ?);";

        ObservableList<Appointment> overlappedApptsResult = FXCollections.observableArrayList();

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement stmt = DBQuery.getPreparedStatement();

            stmt.setTimestamp(1, Timestamp.valueOf(start));
            stmt.setTimestamp(2, Timestamp.valueOf(end));
            stmt.setTimestamp(3, Timestamp.valueOf(start));
            stmt.setTimestamp(4, Timestamp.valueOf(end));
            stmt.setTimestamp(5, Timestamp.valueOf(start));
            stmt.setTimestamp(6, Timestamp.valueOf(end));
            stmt.setTimestamp(7, Timestamp.valueOf(start));
            stmt.setTimestamp(8, Timestamp.valueOf(end));

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Appointment overlappedAppt = new Appointment();
                overlappedAppt.setApptId(rs.getInt("Appointment_Id"));
                overlappedAppt.setApptTitle(rs.getString("title"));
                overlappedAppt.setApptDesc(rs.getString("description"));
                overlappedAppt.setApptLocation(rs.getString("location"));
                overlappedAppt.setApptContact(rs.getInt("contact_id"));
                overlappedAppt.setApptType(rs.getString("type"));
                overlappedAppt.setApptCustomerId(rs.getInt("Customer_ID"));
                overlappedApptsResult.add(overlappedAppt);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return overlappedApptsResult;
    }

    /**
     * This method checks the database to see if a Customer has any appointments
     * @param id Customer_ID for database query
     * @return True if the ResultSet is null
     */
    public static boolean customerHasAppointments(int id){
        String getStatement = "select * from appointments where Customer_ID = ?;";
        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,id);
            ResultSet rs = ps.getResultSet();
            ps.execute();
            if (rs == null) {
              return true;
            }

        }catch (SQLException s){
            s.printStackTrace();
        }
        return false;

    }

    /**
     * This method deletes all Appointments in the database associated with the provided Customer_ID
     * @param id Customer_ID for database query
     */
    public static void deleteAllAppointmentsByCustomerID(int id){
        String getStatement = "delete from appointments where Customer_ID = ?;";

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1,id);
            ps.execute();
            if (ps.getUpdateCount()>0){
                System.out.println(ps.getUpdateCount()+" row(s) affected.");
            }

        }catch (SQLException s){
            s.printStackTrace();
        }
    }

}
