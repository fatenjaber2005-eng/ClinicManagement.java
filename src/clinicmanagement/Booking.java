/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicmanagement;
import java.io.Serializable;

public class Booking implements Serializable {
    private static final long serialVersionUID = 6L;

    private String bookingId;
    private String bookingDate;
    private String details;
    private int department;
    private Doctor doctor;
    private Patient patient;

    public Booking(String bookingId, String bookingDate, String details, int department, Doctor doctor, Patient patient) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.details = details;
        this.department = department;
        this.doctor = doctor;
        this.patient = patient;
    }

    // getters / setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public int getDepartment() { return department; }
    public void setDepartment(int department) { this.department = department; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    private String departmentName() {
        switch (department) {
            case Doctor.GENERAL: return "General Doctor";
            case Doctor.PEDIATRICIAN: return "Pediatrician";
            case Doctor.PSYCHIATRIST: return "Psychiatrist";
            default: return "Unknown";
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + bookingId + '\'' +
                ", date='" + bookingDate + '\'' +
                ", details='" + details + '\'' +
                ", department=" + departmentName() +
                ", doctor=" + (doctor != null ? doctor.getPersonName() + "(" + doctor.getPersonId() + ")" : "None") +
                ", patient=" + (patient != null ? patient.getPersonName() + "(" + patient.getPersonId() + ")" : "None") +
                '}';
    }
}