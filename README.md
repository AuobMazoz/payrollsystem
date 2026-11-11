# Payroll Management System

A small Java application that models employees with different compensation arrangements and demonstrates core object-oriented programming concepts. The program creates one full-time employee and one contractor, calculates their net salaries, and prints their details.

## Project Objectives

- Represent shared employee information in an abstract superclass.
- Model full-time and contract employees as specialized employee types.
- Apply tax and bonus calculations through interfaces.
- Demonstrate inheritance, method overriding, polymorphism, and natural ordering with `Comparable<Employee>`.
- Run a simple payroll example from a Java `main` method.

> **Scope:** This is an educational example, not a complete payroll product. The current program does not store employee records, process payments, validate payroll inputs, or include bonuses in the displayed net salaries.

## Technologies

- **Java** (JDK 11 or later)
- **Apache NetBeans IDE** (for opening, editing, and running the source code)

The repository contains Java source files in the `payrollsystem` package. No NetBeans project configuration or external library dependency is present in the repository.

## Project Structure

```text
payrollsystem/
├── BonusCalculable.java       # Bonus calculation contract
├── ContractorEmployee.java    # Employee paid using an hourly rate and hours worked
├── Employee.java              # Abstract superclass and shared employee behavior
├── FullTimeEmployee.java      # Salaried employee with an allowance
├── PayrollSystem.java         # Application entry point and example payroll run
└── Taxable.java               # Tax calculation contract and tax-rate constant
```

All six source files declare the `payrollsystem` package.

| File | Purpose |
| --- | --- |
| `Employee.java` | Defines common employee fields (`id`, `name`, `baseSalary`, and `joinDate`), getters, tax calculation, salary comparison, and abstract methods for net salary and bonus. |
| `FullTimeEmployee.java` | Extends `Employee`, adds an allowance, and calculates net salary for a full-time employee. |
| `ContractorEmployee.java` | Extends `Employee`; derives its base salary from hourly rate multiplied by hours worked. |
| `Taxable.java` | Declares the `calculateTax()` contract and a fixed tax-rate constant. |
| `BonusCalculable.java` | Declares the `calculateBonus(double performanceScore)` contract. |
| `PayrollSystem.java` | Contains `main`, creates the sample employees, and prints each employee and net salary. |

## Interfaces and Comparison

### `Taxable`

`Taxable` defines the `calculateTax()` method and the constant `tax_rate`, set to `0.15` (15%). In Java, interface fields are implicitly `public static final`. `Employee` implements the tax calculation as `baseSalary * Taxable.tax_rate`, so tax is calculated from base salary only.

### `BonusCalculable`

`BonusCalculable` declares `calculateBonus(double performanceScore)`. The abstract `Employee` class implements this interface but leaves the method abstract; concrete employee types provide the implementation. Both current employee types calculate a bonus as `baseSalary * performanceScore`.

The source code does not define a required scale or validate the performance score. It is used directly as a multiplier (for example, `0.10` would produce a bonus equal to 10% of base salary).

### `Comparable<Employee>`

`Employee` implements `Comparable<Employee>`. Its `compareTo` method compares employees using `baseSalary` and `Double.compare`. This supports sorting employee objects by base salary in ascending order. It does not use allowance, bonus, net salary, or a secondary field to break ties. The current `main` method does not sort employees.

## Inheritance and Polymorphism

`Employee` is an abstract superclass. It stores and exposes the information common to all employees and provides shared tax and comparison behavior. It also declares `calculateNetSalary()` and `calculateBonus(...)` as abstract methods, requiring subclasses to define those behaviors.

`FullTimeEmployee` and `ContractorEmployee` both extend `Employee` and override these methods. In `PayrollSystem`, each concrete object is assigned to an `Employee` reference:

```java
Employee fullTime = new FullTimeEmployee(/* ... */);
Employee contractor = new ContractorEmployee(/* ... */);
```

When `calculateNetSalary()` is called through either reference, Java dispatches to the implementation for the object's actual type. This is runtime polymorphism.

## Employee Types

### Full-time employee

A `FullTimeEmployee` has a base salary and an additional allowance. Its net salary is:

```text
base salary + allowance - tax
```

Tax is calculated on the base salary, not on the allowance. Its bonus is `baseSalary * performanceScore`.

### Contractor

A `ContractorEmployee` is constructed with an hourly rate and hours worked. The constructor sets the inherited base salary to:

```text
hourly rate * hours worked
```

Its net salary is:

```text
base salary - tax
```

Its bonus is also `baseSalary * performanceScore`. The hourly rate and hours worked are stored in the class, but are not included in its inherited `toString()` output.

## Payroll Calculation Rules

The implemented calculations are:

| Calculation | Rule |
| --- | --- |
| Tax rate | `15%` (`0.15`) |
| Tax | `baseSalary * 0.15` |
| Full-time net salary | `baseSalary + allowance - tax` |
| Contractor base salary | `hourlyRate * hoursWorked` |
| Contractor net salary | `baseSalary - tax` |
| Bonus for either type | `baseSalary * performanceScore` |

**Important:** `calculateNetSalary()` does not add the bonus for either employee type. Although a bonus calculation method is implemented, `PayrollSystem.main` does not call it. The sample net salaries therefore include tax (and the full-time allowance), but no bonus.

## Open and Run in NetBeans

The repository does not include NetBeans metadata such as `nbproject/` or a project build file. To run the source in NetBeans:

1. Install JDK 11 or later and Apache NetBeans.
2. Clone or download this repository.
3. In NetBeans, create a Java project (or open/import the source as a Java project, depending on your NetBeans version).
4. Add the repository's `payrollsystem` directory as a source package/folder, preserving the `payrollsystem` package declaration.
5. Set the main class to `payrollsystem.PayrollSystem` if NetBeans does not detect it automatically.
6. Run the project.

You can also compile and run from a terminal in the repository root:

```bash
javac -d out payrollsystem/*.java
java -cp out payrollsystem.PayrollSystem
```

## Example Output

The following output is based on the values and `toString()` implementation in the current source code:

```text
Employee{id='E001', name='Ali', baseSalary=5000.0, joinDate=2024-01-10}
Net salary: 4750.0
Employee{id='E002', name='Sara', baseSalary=4000.0, joinDate=2024-03-15}
Net salary: 3400.0
```

Calculation details:

- **Ali:** tax = `5000 × 0.15 = 750`; net salary = `5000 + 500 - 750 = 4750`.
- **Sara:** base salary = `25 × 160 = 4000`; tax = `4000 × 0.15 = 600`; net salary = `4000 - 600 = 3400`.

## Java Concepts Demonstrated

- Classes and objects
- Abstract classes and abstract methods
- Inheritance and specialization
- Interfaces and interface constants
- Method overriding and runtime polymorphism
- Encapsulation through fields and getters
- The `super` constructor call
- The `@Override` annotation
- `Comparable<T>` and `Double.compare` for natural ordering
- `LocalDate` for representing join dates
- Arithmetic operations and simple business rules
- A `main` method as the application entry point

## Educational Use

This project is suitable as a starting point for a university assignment on Java object-oriented programming. Its calculation rules are intentionally simple and should not be treated as jurisdiction-specific tax or payroll advice.
