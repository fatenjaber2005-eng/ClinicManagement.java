/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicmanagement;
import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    private static final long serialVersionUID = 3L;

    private double managerBonus;

    public Manager() { 
        super(); 
    }

    public Manager(double managerBonus, double basicSalary, String hireDate,
                   String personName, String personId, String personAddress,
                   String personPhoneNumber, String personEmail, String personGender) {
        super(basicSalary, hireDate, personName, personId, personAddress, personPhoneNumber, personEmail, personGender);
        this.managerBonus = managerBonus;
    }

    public double getManagerBonus() { 
        return managerBonus;
    }
    public void setManagerBonus(double managerBonus) { 
        this.managerBonus = managerBonus; 
    }

    @Override
    public String toString() {
        return super.toString() + " Manager{" +
                "managerBonus=" + managerBonus +
                '}';
    }

    @Override
    public double getSalary() {
        return getBasicSalary() + getLivingExpense() + managerBonus;
    }
}