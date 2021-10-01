package main.Model;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all Country objects
 */
public class Country {

    private int countryId;
    private String countryName;

    /**
     *  All Args Constructor
     * @param countryId Country ID
     * @param countryName Country Name
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
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
     *
     * @return Country Name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @param countryName Country Name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Custom toString method
     * @return Country Name
     */
    @Override
    public String toString() {
        return countryName;
    }
}
