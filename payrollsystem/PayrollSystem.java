package payrollsystem;

import java.time.LocalDate;

public class PayrollSystem {
    public static void main(String[] args) {
        Employee fullTime = new FullTimeEmployee(
                "E001", "Ali", 5000, LocalDate.of(2024, 1, 10), 500
        );

        Employee contractor = new ContractorEmployee(
                "E002", "Sara", LocalDate.of(2024, 3, 15), 25, 160
        );

        System.out.println(fullTime);
        System.out.println("Net salary: " + fullTime.calculateNetSalary());

        System.out.println(contractor);
        System.out.println("Net salary: " + contractor.calculateNetSalary());
    }
}