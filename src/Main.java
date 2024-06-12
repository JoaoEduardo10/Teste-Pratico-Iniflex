import Entity.Employee;
import Service.EmployeeServices;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeServices employeeServices = new EmployeeServices();
        List<Employee> employees = employeeServices.createEmployees();

        System.out.println("Removendo o funcionário João....\n");
        employeeServices.removeEmployee(employees, "João");
        System.out.println("...................................\n\n\n");

        System.out.println("Printando os funcinarios \n");
        employeeServices.printEmployee(employees);
        System.out.println("...................................\n\n\n");

        System.out.println("Aplicando o aumento no salario...\n");
        employeeServices.applySalaryIncrease(employees, new BigDecimal("0.10"));
        System.out.println("...................................\n\n\n");

        System.out.println("Transformando os funcinario em hashMap divididos por função...\n");
        Map<String, List<Employee>> employeesByRole = employeeServices.groupByRole(employees);
        System.out.println("...................................\n\n\n");

        System.out.println("Imprimindo os funcionários por função\n\n");
        employeeServices.printEmployeesByRole(employeesByRole);
        System.out.println("...................................\n\n\n");

        System.out.println("Imprinmindo os funcionários que fazem aniversário no mês 10 e 12.\n");
        employeeServices.printBirthdays(employees, Arrays.asList(10, 12));
        System.out.println("...................................\n\n\n");

        System.out.println("Imprinmindo o funcionário com a maior idade\n");
        employeeServices.printOlderEmployee(employees);
        System.out.println("...................................\n\n\n");

        System.out.println("Imprinmindo a lista de funcionários por ordem alfabética.\n");
        employeeServices.printStaffByAlphabeticOrder(employees);
        System.out.println("...................................\n\n\n");

        System.out.println("Imprinmindo o total dos salários dos funcionários.\n");
        employeeServices.printTotalSalaries(employees);
        System.out.println("...................................\n\n\n");

        System.out.println("Imprinmindo quantos salários mínimos ganha cada funcionário.\n");
        employeeServices.printMinimumSalaries(employees);
        System.out.println("...................................\n\n\n");
    }
}