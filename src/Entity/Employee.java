package Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person{

    private BigDecimal salary;
    private final String role;

    public Employee(String name, LocalDate age, BigDecimal salary, String role) {
        super(name, age);
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal value) {
        this.salary = value;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(salary, employee.salary) && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, role);
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f, %s", super.toString(), salary, role);
    }
}
