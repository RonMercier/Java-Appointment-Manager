package main.Model;

import main.Exception.BusinessHoursException;
import main.Exception.ValidationException;
import main.Util.TimeConverter;
import java.sql.Timestamp;
import java.time.*;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all Appointment objects
 */
public class Appointment {
    private int apptId;
    private String apptTitle;
    private String apptDesc;
    private String apptLocation;
    private int apptContact;
    private String apptType;
    private Timestamp apptStart;
    private Timestamp apptEnd;
    private int apptCustomerId;

    /**
     * No Args Constructor
     */
    public Appointment() {
    }

    /**
     * All Args Constructor
     * @param apptId Appointment ID
     * @param apptTitle Appointment Title
     * @param apptDesc Appointment Description
     * @param apptLocation Appointment Location
     * @param apptContact Appointment Contact
     * @param apptType Appointment Type
     * @param apptStart Appointment Start
     * @param apptEnd Appointment End
     * @param apptCustomerId Appointment Customer ID
     */
    public Appointment(int apptId, String apptTitle, String apptDesc, String apptLocation, int apptContact, String apptType, Timestamp apptStart, Timestamp apptEnd, int apptCustomerId) {
        this.apptId = apptId;
        this.apptTitle = apptTitle;
        this.apptDesc = apptDesc;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptCustomerId = apptCustomerId;
    }

    /**
     * Args Constructor
     * @param apptTitle Appointment Title
     * @param apptDesc Appointment Description
     * @param apptLocation Appointment Location
     * @param apptContact Appointment Contact
     * @param apptType Appointment Type
     * @param apptStart Appointment Start
     * @param apptEnd Appointment End
     * @param apptCustomerId Appointment Customer ID
     */
    public Appointment(String apptTitle, String apptDesc, String apptLocation, int apptContact, String apptType, Timestamp apptStart, Timestamp apptEnd, int apptCustomerId) {
        this.apptTitle = apptTitle;
        this.apptDesc = apptDesc;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptCustomerId = apptCustomerId;
    }

    /**
     *
     * @return Appointment ID
     */
    public int getApptId() {
        return apptId;
    }

    /**
     *
     * @param apptId Setter for Appointment ID
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     *
     * @return Appointment Title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     *
     * @param apptTitle Appointment Title
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     *
     * @return Appointment Description
     */
    public String getApptDesc() {
        return apptDesc;
    }

    /**
     *
     * @param apptDesc Appointment Description
     */
    public void setApptDesc(String apptDesc) {
        this.apptDesc = apptDesc;
    }

    /**
     *
     * @return Appointment Location
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     *
     * @param apptLocation Appointment Location
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /**
     *
     * @return Appointment Contact
     */
    public int getApptContact() {
        return apptContact;
    }

    /**
     *
     * @param apptContact Appointment Contact
     */
    public void setApptContact(int apptContact) {
        this.apptContact = apptContact;
    }

    /**
     *
     * @return Appointment Type
     */
    public String getApptType() {
        return apptType;
    }

    /**
     *
     * @param apptType Appointment Type
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     *
     * @return Appointment Start
     */
    public Timestamp getApptStart() {
        return apptStart;
    }

    /**
     *
     * @param apptStart Appointment Start
     */
    public void setApptStart(Timestamp apptStart) {
        this.apptStart = apptStart;
    }

    /**
     *
     * @return Appointment End
     */
    public Timestamp getApptEnd() {
        return apptEnd;
    }

    /**
     *
     * @param apptEnd Appointment End
     */
    public void setApptEnd(Timestamp apptEnd) {
        this.apptEnd = apptEnd;
    }

    /**
     *
     * @return Appointment Customer ID
     */
    public int getApptCustomerId() {
        return apptCustomerId;
    }

    /**
     *
     * @param apptCustomerId Appointment Customer ID
     */
    public void setApptCustomerId(int apptCustomerId) {
        this.apptCustomerId = apptCustomerId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "apptId=" + apptId +
                ", apptTitle='" + apptTitle + '\'' +
                ", apptDesc='" + apptDesc + '\'' +
                ", apptLocation='" + apptLocation + '\'' +
                ", apptContact=" + apptContact +
                ", apptType='" + apptType + '\'' +
                ", apptStart=" + apptStart +
                ", apptEnd=" + apptEnd +
                ", apptCustomerId=" + apptCustomerId +
                '}';
    }

    /**
     * Validation method
     *
     * @return true if no exception is thrown. Otherwise, it alerts the user.
     * @throws ValidationException checks that all form fields are valid
     */
    public boolean isValid() throws ValidationException {
//        Title Required
        if (getApptTitle().equals("")) {
            throw new ValidationException("The title field cannot be empty.");
        }

//        Description Required
        if (getApptDesc().equals("")) {
            throw new ValidationException("The description field cannot be empty.");
        }

//        Location Required
        if (getApptLocation().equals("")) {
            throw new ValidationException("The location field cannot be empty");
        }

        // min must be positive
        if (getApptType().equals("")) {
            throw new ValidationException("The type field cannot be empty.");
        }

        if (getApptStart().after(getApptEnd()) ){
            throw new ValidationException("The appointment start time cannot be before the end time");
        }

        if (getApptEnd().before(getApptStart())){
            throw new ValidationException("The appointment end time cannot be after the start time");
        }

        return true;
    }

    /**
     *
     * @return true if the provided appointment is within valid business hours
     * @throws BusinessHoursException checks that input dates and times fall within business hours.
     */
    public boolean isValidTime() throws BusinessHoursException {
//        Local DateTime
        LocalDate apptStartDate = this.apptStart.toLocalDateTime().toLocalDate();
        LocalTime apptStartTime = this.apptStart.toLocalDateTime().toLocalTime();
        LocalDate apptEndDate = this.apptEnd.toLocalDateTime().toLocalDate();
        LocalTime apptEndTime = this.apptEnd.toLocalDateTime().toLocalTime();


        LocalDateTime midnightLocalDateTime = LocalDateTime.of(apptStartDate,LocalTime.MIDNIGHT);
        LocalDateTime afterConversion = TimeConverter.localToEST(midnightLocalDateTime);
        LocalTime EST_Midnight = afterConversion.toLocalTime();


        int weekDay = apptStartDate.getDayOfWeek().getValue();

        if (!apptStartDate.isEqual(apptEndDate)) {
            throw new BusinessHoursException("An appointment can only be a single day.");
        }
        if (weekDay == 6 || weekDay == 7) {
            throw new BusinessHoursException("An appointment can only be scheduled on weekdays.");
        }
        if (apptStartTime.equals(apptEndTime)){
            throw new BusinessHoursException(("An appointment end time cannot be the same as its start"));
        }

        if (apptStart.before(Timestamp.valueOf(LocalDateTime.of(apptStartDate,EST_Midnight).plusHours(8)))){
            throw new BusinessHoursException("An appointment cannot be scheduled before normal business hours.");
        }
        if (apptEnd.after(Timestamp.valueOf(LocalDateTime.of(apptEndDate,EST_Midnight).plusHours(22)))){
            throw new BusinessHoursException("An appointment cannot be scheduled after normal business hours.");
        }

        if (apptStartDate.isBefore(LocalDate.now()) || apptStartTime.isBefore(LocalTime.MIDNIGHT)) {
            throw new BusinessHoursException("An appointment cannot be scheduled in the past!");
        }
        return true;
    }
}
