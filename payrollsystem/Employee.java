package payrollsystem;

import java.time.LocalDate;

public abstract class Employee
        implements Comparable<Employee>, Taxable, BonusCalculable {

    private String id;
    private String name;
    protected double baseSalary;
    private LocalDate joinDate;

    public Employee(String id, String name, double baseSalary, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public abstract double calculateNetSalary();

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.baseSalary, other.baseSalary);
    }

    @Override
    public double calculateTax() {
        return baseSalary * Taxable.tax_rate;
    }

    @Override
    public abstract double calculateBonus(double performanceScore);

    @Override
    public String toString() {
        return "Employee{id='" + id + "', name='" + name
                + "', baseSalary=" + baseSalary
                + ", joinDate=" + joinDate + "}";
    }

}
