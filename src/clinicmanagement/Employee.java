/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicmanagement;
import java.io.Serializable;

public abstract class Employee extends Person implements Serializable {
    private static final long serialVersionUID = 2L;

    private double basicSalary;
    private double livingExpense; // computed as 5.5% of basic
    private String hireDate;

    public Employee() { super(); }

    public Employee(double basicSalary, String hireDate,
                    String personName, String personId, String personAddress,
                    String personPhoneNumber, String personEmail, String personGender) {
        super(personName, personId, personAddress, personPhoneNumber, personEmail, personGender);
        this.basicSalary = basicSalary;
        this.livingExpense = basicSalary * 0.055;
        this.hireDate = hireDate;
    }

    // getters / setters
    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
        this.livingExpense = basicSalary * 0.055;
    }

    public double getLivingExpense() { return livingExpense; }
    // no setter for livingExpense; it's computed

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    @Override
    public String toString() {
        return super.toString() + " Employee{" +
                "basicSalary=" + basicSalary +
                ", livingExpense=" + livingExpense +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }

    // abstract method to calculate salary
    public abstract double getSalary();
}
   

