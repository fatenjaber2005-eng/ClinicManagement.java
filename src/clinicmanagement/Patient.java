/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicmanagement;
import java.io.Serializable;

public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 5L;

    private int numberOfBookings;

    public Patient() { super(); }

    public Patient(int numberOfBookings, String personName, String personId, String personAddress,
                   String personPhoneNumber, String personEmail, String personGender) {
        super(personName, personId, personAddress, personPhoneNumber, personEmail, personGender);
        this.numberOfBookings = numberOfBookings;
    }

    // copy constructor
    public Patient(Patient other) {
        super(other.getPersonName(), other.getPersonId(), other.getPersonAddress(),
                other.getPersonPhoneNumber(), other.getPersonEmail(), other.getPersonGender());
        this.numberOfBookings = other.numberOfBookings;
    }

    public int getNumberOfBookings() { return numberOfBookings; }
    public void setNumberOfBookings(int numberOfBookings) { this.numberOfBookings = numberOfBookings; }

    public void increaseNumberOfBookings() { this.numberOfBookings++; }

    public void decreaseNumberOfBookings() {
        if (this.numberOfBookings > 0) this.numberOfBookings--;
    }

    @Override
    public String toString() {
        return super.toString() + " Patient{" +
                "numberOfBookings=" + numberOfBookings +
                '}';
    }
}