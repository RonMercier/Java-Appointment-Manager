package main.Model;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all User objects.
 */
public class User {

    private int userID;
    private String userName;
    private String userPassword;

    /**
     * All Args Constructor
     * @param userID User ID
     * @param userName User Name
     * @param userPassword User Password
     */
    public User(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * Args Constructor
     * @param userName UserName
     * @param userPassword UserPassword
     */
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     *
     * @return User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return User Password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @param userPassword User Password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Custom toString method
     * @return "User{#,userName, userPassword}"
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
