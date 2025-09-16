
package clinicmanagement;
import java.io.Serializable;

public class Doctor extends Employee implements Serializable {
    private static final long serialVersionUID = 4L;

    private int numberOfCompletedBookings;
    private int department;

    public static final int GENERAL = 1;
    public static final int PEDIATRICIAN = 2;
    public static final int PSYCHIATRIST = 3;

    public Doctor() { super(); }

    public Doctor(int numberOfCompletedBookings, int department, double basicSalary, String hireDate,
                  String personName, String personId, String personAddress,
                  String personPhoneNumber, String personEmail, String personGender) {
        super(basicSalary, hireDate, personName, personId, personAddress, personPhoneNumber, personEmail, personGender);
        this.numberOfCompletedBookings = numberOfCompletedBookings;
        this.department = department;
    }

    // copy constructor
    public Doctor(Doctor other) {
        super(other.getBasicSalary(), other.getHireDate(), other.getPersonName(), other.getPersonId(),
                other.getPersonAddress(), other.getPersonPhoneNumber(), other.getPersonEmail(), other.getPersonGender());
        this.numberOfCompletedBookings = other.numberOfCompletedBookings;
        this.department = other.department;
    }

    // getters / setters
    public int getNumberOfCompletedBookings() { return numberOfCompletedBookings; }
    public void setNumberOfCompletedBookings(int numberOfCompletedBookings) { this.numberOfCompletedBookings = numberOfCompletedBookings; }

    public int getDepartment() { return department; }
    public void setDepartment(int department) { this.department = department; }

    private String departmentName() {
        switch (department) {
            case GENERAL: return "General Doctor";
            case PEDIATRICIAN: return "Pediatrician";
            case PSYCHIATRIST: return "Psychiatrist";
            default: return "Unknown";
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Doctor{" +
                "completedBookings=" + numberOfCompletedBookings +
                ", department=" + departmentName() +
                '}';
    }

    @Override
    public double getSalary() {
        // 2 dollars per completed booking
        return getBasicSalary() + getLivingExpense() + (numberOfCompletedBookings * 2.0);
    }
}