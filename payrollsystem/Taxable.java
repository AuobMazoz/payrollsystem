
package payrollsystem;

public interface Taxable {
    double tax_rate = 0.15;

    double calculateTax();
}