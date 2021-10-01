package main.Model;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles all Contact objects.
 */
public class Contact {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * All Args Constructor
     * @param contactId Contact ID
     * @param contactName Contact Name
     * @param contactEmail Contact Email
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return Contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId Contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return Contact Name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName Contact Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return Contact Email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     *
     * @param contactEmail Contact Email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Custom toString method
     * @return " #: contact name (contact.email@contact.com)"
     */
    @Override
    public String toString() {
        return contactId+ ": " +contactName+ " (" +contactEmail+ ")";
    }
}
