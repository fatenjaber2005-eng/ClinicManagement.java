
package clinicmanagement;
import java.io.Serializable;
/**
 * Name: Faten Samer Jaber
 * Uni num: 123454321
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String personName;
    private String personId;
    private String personAddress;
    private String personPhoneNumber;
    private String personEmail;
    private String personGender;

    public Person() { }

    public Person(String personName, String personId, String personAddress,
                  String personPhoneNumber, String personEmail, String personGender) {
        this.personName = personName;
        this.personId = personId;
        this.personAddress = personAddress;
        this.personPhoneNumber = personPhoneNumber;
        this.personEmail = personEmail;
        this.personGender = personGender;
    }

    // getters & setters
    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }

    public String getPersonId() { return personId; }
    public void setPersonId(String personId) { this.personId = personId; }

    public String getPersonAddress() { return personAddress; }
    public void setPersonAddress(String personAddress) { this.personAddress = personAddress; }

    public String getPersonPhoneNumber() { return personPhoneNumber; }
    public void setPersonPhoneNumber(String personPhoneNumber) { this.personPhoneNumber = personPhoneNumber; }

    public String getPersonEmail() { return personEmail; }
    public void setPersonEmail(String personEmail) { this.personEmail = personEmail; }

    public String getPersonGender() { return personGender; }
    public void setPersonGender(String personGender) { this.personGender = personGender; }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + personName + '\'' +
                ", Id='" + personId + '\'' +
                ", Address='" + personAddress + '\'' +
                ", Phone='" + personPhoneNumber + '\'' +
                ", Email='" + personEmail + '\'' +
                ", Gender='" + personGender + '\'' +
                '}';
    }
}
