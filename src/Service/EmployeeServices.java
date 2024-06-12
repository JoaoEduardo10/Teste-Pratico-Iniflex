package Service;

import Entity.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeServices {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat CURRENCY_FORMATTER;

    static {
        CURRENCY_FORMATTER = NumberFormat.getInstance(new Locale("pt", "BR"));
        CURRENCY_FORMATTER.setMinimumFractionDigits(2);
        CURRENCY_FORMATTER.setMaximumFractionDigits(2);
    }

    public List<Employee> createEmployees() {
        return new ArrayList<>(Arrays.asList(
                new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    public void removeEmployee(List<Employee> employees, String name) {
        employees.removeIf(e -> e.getName().equalsIgnoreCase(name));
    }

    public void printEmployee(List<Employee> employees) {
        employees.forEach(e -> System.out.println(formatEmployee(e)));
    }

    public void applySalaryIncrease(List<Employee> employees, BigDecimal percentage) {
        for (Employee e : employees) {
            BigDecimal newSalary = e.getSalary().add(e.getSalary().multiply(percentage));
            e.setSalary(newSalary);
        }
    }

    public Map<String, List<Employee>> groupByRole(List<Employee> employee) {
        return employee.stream().collect(Collectors.groupingBy(Employee::getRole));
    }

    public void printEmployeesByRole(Map<String, List<Employee>> employeesByRole) {
        for (Map.Entry<String, List<Employee>> entry : employeesByRole.entrySet()) {
            System.out.println(entry.getKey() + ":");
            entry.getValue().forEach(e -> System.out.println("  " + formatEmployee(e)));
        }
    }

    public void printBirthdays(List<Employee> employees, List<Integer> months) {
        employees.stream()
                .filter(e -> months.contains(e.getAge().getMonthValue()))
                .forEach(e -> System.out.println(formatEmployee(e)));
    }

    public void printOlderEmployee(List<Employee> employees) {
        Employee older = Collections.max(employees, Comparator.comparing(Employee::getAge));
        long age = calculateAge(older.getAge());
        System.out.printf("Funcionário mais velho: %s, Idade: %d%n", older.getName(), age);
    }

    public void printStaffByAlphabeticOrder(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.println(formatEmployee(e)));
    }

    public void printTotalSalaries(List<Employee> employees) {
        BigDecimal totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("Total dos salários: %s%n", CURRENCY_FORMATTER.format(totalSalary));
    }

    public void printMinimumSalaries(List<Employee> employees) {
        employees.forEach(e -> {
            BigDecimal minimumSalary = e.getSalary().divide(SALARIO_MINIMO, RoundingMode.HALF_UP);
            System.out.printf("%s ganha %s salários mínimos%n", e.getName(), CURRENCY_FORMATTER.format(minimumSalary));
        });
    }

    private String formatEmployee(Employee employee) {
        return String.format("%s, %s, %s, %s",
                employee.getName(),
                employee.getAge().format(DATE_FORMATTER),
                CURRENCY_FORMATTER.format(employee.getSalary()),
                employee.getRole());
    }

    private long calculateAge(LocalDate birthDate) {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
