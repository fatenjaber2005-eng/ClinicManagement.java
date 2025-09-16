
package clinicmanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClinicManagement {
    private static Manager manager;
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static final String DATA_CLINIC = "data.dat";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readData(); // load saved data if exists

        if (manager == null) {
            System.out.println("No manager found — please add a manager first.");
            addManager();
        }

        while (true) {
            printMainMenu();
            int choice = getIntInput("Enter your choice (1-10): ", 1, 10);
            switch (choice) {
                case 1 : addEmployeeMenu();
                case 2 : addPatient();
                case 3 : addBooking();
                case 4 : showDoctorMenu();
                case 5 : showBookingForPatient();
                case 6 : showSalariesMenu();
                case 7 : deleteBooking();
                case 8 : countBookingForPatient();
                case 9 : saveData();
                case 10 :
                { 
                    saveData(); 
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n-------- Medical Clinic Management System ---------");
        System.out.println("1- Add Employee");
        System.out.println("2- Add Patient");
        System.out.println("3- Add Booking");
        System.out.println("4- Show Doctors");
        System.out.println("5- Show bookings for a specific Patient");
        System.out.println("6- Show Employees’ Salaries");
        System.out.println("7- Delete booking");
        System.out.println("8- Count bookings for a Patient");
        System.out.println("9- Save data to a binary file");
        System.out.println("10- Exit");
    }

    // ===== input helpers =====
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.println("Please enter a number between " + min + " and " + max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input — please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double v = Double.parseDouble(line);
                if (v < min || v > max) {
                    System.out.println("Please enter a value between " + min + " and " + max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input — please enter a number.");
            }
        }
    }

    // ===== Add employee / manager / doctor =====
    private static void addEmployeeMenu() {
        while (true) {
            System.out.println("\n...... Add Employee ......");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Doctor");
            System.out.println("3. Back to main menu");
            int choice = getIntInput("Enter your choice (1-3): ", 1, 3);
            if (choice == 1) { addManager(); return; }
            if (choice == 2) { addDoctor(); return; }
            if (choice == 3) return;
        }
    }

    private static void addManager() {
        System.out.println("\n...... Add Manager ......");
        if (manager != null) {
            System.out.println("Manager already exists:");
            System.out.println(manager);
            System.out.println("1. Replace manager\n2. Keep current manager");
            int c = getIntInput("Enter choice: ", 1, 2);
            if (c == 2) return;
        }

        String name = getStringInput("Manager Name: ");
        String id = getStringInput("Manager ID: ");
        String address = getStringInput("Manager Address: ");
        String phone = getStringInput("Manager Phone: ");
        String email = getStringInput("Manager Email: ");
        String gender = getStringInput("Manager Gender: ");
        double salary = getDoubleInput("Basic salary: ", 0.0, Double.MAX_VALUE);
        String hireDate = getStringInput("Hire Date (e.g. 14/09/2025): ");
        double bonus = getDoubleInput("Manager Bonus: ", 0.0, Double.MAX_VALUE);

        manager = new Manager(bonus, salary, hireDate, name, id, address, phone, email, gender);
        System.out.println("Manager added/updated successfully.");
    }

    private static void addDoctor() {
        System.out.println("\n...... Add Doctor ......");
        String name = getStringInput("Doctor Name: ");
        String id = getStringInput("Doctor ID: ");
        String address = getStringInput("Doctor Address: ");
        String phone = getStringInput("Doctor Phone: ");
        String email = getStringInput("Doctor Email: ");
        String gender = getStringInput("Doctor Gender: ");
        double salary = getDoubleInput("Basic salary: ", 0.0, Double.MAX_VALUE);
        String hireDate = getStringInput("Hire Date (e.g. 10/09/2025): ");
        int bookingsCompleted = getIntInput("Completed Bookings: ", 0, Integer.MAX_VALUE);
        int dep = getIntInput("Department (1-General, 2-Pediatrician, 3-Psychiatrist): ", 1, 3);

        Doctor doctor = new Doctor(bookingsCompleted, dep, salary, hireDate, name, id, address, phone, email, gender);
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    // ===== Add patient =====
    private static void addPatient() {
        System.out.println("\n...... Add Patient ......");
        String name = getStringInput("Patient Name: ");
        String id = getStringInput("Patient ID: ");
        String address = getStringInput("Patient Address: ");
        String phone = getStringInput("Patient Phone: ");
        String email = getStringInput("Patient Email: ");
        String gender = getStringInput("Patient Gender: ");
        int bookings = getIntInput("Number of Bookings: ", 0, Integer.MAX_VALUE);

        Patient patient = new Patient(bookings, name, id, address, phone, email, gender);
        patients.add(patient);
        System.out.println("Patient added successfully.");
    }

    // ===== Add booking =====
    private static void addBooking() {
        System.out.println("\n...... Add Booking ......");
        if (doctors.isEmpty() || patients.isEmpty()) {
            System.out.println("Cannot add booking — you need at least one doctor and one patient.");
            return;
        }

        String bookingId = getStringInput("Booking ID: ");
        String date = getStringInput("Booking date (dd/mm/yyyy): ");
        String details = getStringInput("Details: ");
        int dept = getIntInput("Department (1-General,2-Pediatrician,3-Psychiatrist): ", 1, 3);

        // show doctors in department
        System.out.println("\nAvailable Doctors for " + getDepartmentName(dept) + ":");
        ArrayList<Doctor> deptDoctors = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getDepartment() == dept) {
                System.out.println(d.getPersonId() + " - " + d.getPersonName());
                deptDoctors.add(d);
            }
        }
        if (deptDoctors.isEmpty()) {
            System.out.println("No doctors available for this department.");
            return;
        }

        String docId = getStringInput("Enter Doctor ID: ");
        Doctor selectedDoctor = null;
        for (Doctor d : deptDoctors) if (d.getPersonId().equals(docId)) { selectedDoctor = d; break; }
        if (selectedDoctor == null) {
            System.out.println("Invalid Doctor ID.");
            return;
        }

        System.out.println("\nAvailable Patients:");
        for (Patient p : patients) System.out.println(p.getPersonId() + " - " + p.getPersonName());
        String patId = getStringInput("Enter Patient ID: ");
        Patient selectedPatient = null;
        for (Patient p : patients) if (p.getPersonId().equals(patId)) { selectedPatient = p; break; }
        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        Booking booking = new Booking(bookingId, date, details, dept, selectedDoctor, selectedPatient);
        bookings.add(booking);

        selectedDoctor.setNumberOfCompletedBookings(selectedDoctor.getNumberOfCompletedBookings() + 1);
        selectedPatient.increaseNumberOfBookings();

        System.out.println("Booking added successfully!");
    }

    // ===== Show doctors =====
    private static void showDoctorMenu() {
        while (true) {
            System.out.println("\n...... Show Doctors ......");
            System.out.println("1. General Doctors");
            System.out.println("2. Pediatricians");
            System.out.println("3. Psychiatrists");
            System.out.println("4. All Doctors");
            System.out.println("5. Back to main menu");
            int choice = getIntInput("Enter your choice (1-5): ", 1, 5);
            switch (choice) {
                case 1 : 
                    showDoctorsByDepartment(Doctor.GENERAL);
                case 2 : 
                    showDoctorsByDepartment(Doctor.PEDIATRICIAN);
                case 3 : 
                    showDoctorsByDepartment(Doctor.PSYCHIATRIST);
                case 4 : 
                    showAllDoctors();
                case 5 : {
                    return; 
                }
            }
        }
    }

    private static void showDoctorsByDepartment(int department) {
        System.out.println("\n...... " + getDepartmentName(department) + " ......");
        boolean found = false;
        for (Doctor doc : doctors) {
            if (doc.getDepartment() == department) {
                System.out.println(doc);
                System.out.println("===================");
                found = true;
            }
        }
        if (!found) System.out.println("No doctors found in this department.");
    }

    private static void showAllDoctors() {
        System.out.println("\n...... All Doctors ......");
        if (doctors.isEmpty()) { System.out.println("No doctors found."); return; }
        for (Doctor doc : doctors) {
            System.out.println(doc);
            System.out.println("===================");
        }
    }

    // ===== Show bookings for a patient =====
    private static void showBookingForPatient() {
        System.out.println("\n...... Bookings for Patient ......");
        if (patients.isEmpty()) { System.out.println("No patients available."); return; }

        System.out.println("Available Patients:");
        for (Patient p : patients) System.out.println(p.getPersonId() + " - " + p.getPersonName());
        String patientId = getStringInput("Enter Patient ID: ");
        Patient selectedPatient = null;
        for (Patient p : patients) if (p.getPersonId().equals(patientId)) { selectedPatient = p; break; }

        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        System.out.println("\nBookings for " + selectedPatient.getPersonName() + ":");
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getPatient().getPersonId().equals(selectedPatient.getPersonId())) {
                System.out.println(b);
                System.out.println("===================");
                found = true;
            }
        }
        if (!found) System.out.println("No bookings found for this patient.");
    }

    // ===== Salaries =====
    private static void showSalariesMenu() {
        while (true) {
            System.out.println("\n...... Employees' Salaries ......");
            System.out.println("1. Manager Salary");
            System.out.println("2. Specific Doctor Salary");
            System.out.println("3. Back to main menu");
            int choice = getIntInput("Enter your choice (1-3): ", 1, 3);
            switch (choice) {
                case 1 : {
                    if (manager != null) {
                        System.out.println("\nManager Salary Details:");
                        System.out.println(manager);
                        System.out.println("Total Salary = " + manager.getSalary());
                    } else System.out.println("No manager found.");
                }
                case 2 :
                   showSpecificDoctorSalary();
                case 3 :
                {
                    return;
                }
            }
        }
    }

    private static void showSpecificDoctorSalary() {
        System.out.println("\n...... Doctor Salary ......");
        if (doctors.isEmpty()) { System.out.println("No doctors available."); return; }
        System.out.println("Available Doctors:");
        for (Doctor d : doctors) System.out.println(d.getPersonId() + " - " + d.getPersonName());
        String docId = getStringInput("Enter Doctor ID: ");
        Doctor selectedDoctor = null;
        for (Doctor d : doctors) if (d.getPersonId().equals(docId)) { selectedDoctor = d; break; }
        if (selectedDoctor == null) {
            System.out.println("Invalid doctor ID.");
            return;
        }
        System.out.println("\nSalary Details for " + selectedDoctor.getPersonName() + ":");
        System.out.println(selectedDoctor);
        System.out.println("Total Salary = " + selectedDoctor.getSalary());
    }

    // ===== Delete booking =====
    private static void deleteBooking() {
        System.out.println("\n...... Delete Booking ......");
        if (bookings.isEmpty()) { System.out.println("No bookings available to delete."); return; }
        System.out.println("Available bookings:");
        for (Booking b : bookings) {
            System.out.println("Id:" + b.getBookingId() + " - Dept: " + getDepartmentName(b.getDepartment()) + " - Patient: " + b.getPatient().getPersonName());
        }
        String bookId = getStringInput("Enter Booking ID to delete: ");
        Booking bookingToDelete = null;
        for (Booking b : bookings) if (b.getBookingId().equals(bookId)) { bookingToDelete = b; break; }
        if (bookingToDelete == null) {
            System.out.println("Invalid booking ID.");
            return;
        }

        Doctor doc = bookingToDelete.getDoctor();
        if (doc != null) doc.setNumberOfCompletedBookings(Math.max(0, doc.getNumberOfCompletedBookings() - 1));
        Patient pat = bookingToDelete.getPatient();
        if (pat != null) pat.decreaseNumberOfBookings();

        bookings.remove(bookingToDelete);
        System.out.println("Booking deleted successfully.");
    }

    // ===== Count bookings for patient =====
    private static void countBookingForPatient() {
        System.out.println("\n..... Count Bookings for Patient ......");
        if (patients.isEmpty()) { System.out.println("No patients available."); return; }
        System.out.println("Available Patients:");
        for (Patient p : patients) System.out.println(p.getPersonId() + " - " + p.getPersonName());
        String patId = getStringInput("Enter Patient ID: ");
        Patient selectedPatient = null;
        for (Patient p : patients) if (p.getPersonId().equals(patId)) { selectedPatient = p; break; }
        if (selectedPatient == null) { System.out.println("Invalid patient ID."); return; }
        System.out.println("\nPatient " + selectedPatient.getPersonName() + " has " + selectedPatient.getNumberOfBookings() + " booking(s).");
    }

    private static String getDepartmentName(int department) {
                switch (department) {
            case Doctor.GENERAL :
                    return "General Doctor";
            case Doctor.PEDIATRICIAN :
                return "Pediatrician";
            case Doctor.PSYCHIATRIST :
               return "Psychiatrist";
            default :
            return "Unknown";
        }
    }

    // ===== Save / read data =====
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_CLINIC))) {
            oos.writeObject(manager);
            oos.writeObject(doctors);
            oos.writeObject(patients);
            oos.writeObject(bookings);
            System.out.println("Data saved successfully to " + DATA_CLINIC);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    
    private static void readData() {
        File f = new File(DATA_CLINIC);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            manager = (Manager) ois.readObject();
            doctors = (ArrayList<Doctor>) ois.readObject();
            patients = (ArrayList<Patient>) ois.readObject();
            bookings = (ArrayList<Booking>) ois.readObject();
            System.out.println("Data loaded successfully from " + DATA_CLINIC);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    
}


