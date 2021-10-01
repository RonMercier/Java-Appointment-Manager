package main.Model;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all FirstLevelDivision objects.
 */
public class FirstLevelDivision {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * All Args Constructor
     * @param divisionId Division ID
     * @param divisionName Division Name
     * @param countryId Country ID
     */
    public FirstLevelDivision(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     *
     * @return Division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param divisionId Division ID
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return Division Name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     *
     * @param divisionName Division Name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     *
     * @return Country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId Country ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Custom toString method "division name"
     * @return divisionName
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
