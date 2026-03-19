package ra.business;

import ra.entity.Employee;
import ra.helper.ValidateEmployeeInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeBusiness {

    private static EmployeeBusiness instance;

    public static EmployeeBusiness getInstance() {
        if (instance == null) {
            instance = new EmployeeBusiness();
        }
        return instance;
    }

    private List<Employee> employees = new ArrayList<>();

    // 1. List
    public void listAllEmployee() {
        showEmployee(employees);
    }

    public void showEmployee(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("Empty!");
            return;
        }

        System.out.printf("| %-10s | %-15s | %-5s | %-10s |\n",
                "ID", "Name", "Age", "Salary");

        for (Employee e : list) {
            e.displayData();
        }
    }

    // 2. Add
    public void addEmployee(Employee employee) {
        ValidateEmployeeInput.checkUniqueId(employee.getEmpId(), employees);
        employees.add(employee);
    }

    // 3. Find by ID
    public Optional<Employee> findEmployeeById(String id) {
        return employees.stream()
                .filter(e -> e.getEmpId().equalsIgnoreCase(id))
                .findFirst();
    }

    // 4. Delete
    public void deleteEmployee(String id) {
        boolean removed = employees.removeIf(e -> e.getEmpId().equalsIgnoreCase(id));
        if (!removed) {
            throw new IllegalArgumentException("Employee not found!");
        }
    }

    // 5. Find by name
    public List<Employee> findEmployeesByName(String name) {
        List<Employee> result = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getEmpName().toLowerCase().contains(name.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }

    // 6. Filter
    public void filterEmployeeBySalary(double salary) {
        List<Employee> result = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getSalary() >= salary) {
                result.add(e);
            }
        }

        showEmployee(result);
    }

    // 7. Sort
    public void sortEmployeeBySalaryDescending() {
        employees.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        showEmployee(employees);
    }

    // Update
    public void updateEmployee(Employee employee, Scanner scanner) {
        System.out.println("Updating ID: " + employee.getEmpId());

        System.out.print("New name: ");
        String name = scanner.nextLine();
        if (!name.isBlank()) employee.setEmpName(name);

        System.out.print("New age: ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isBlank()) {
            employee.setAge(ValidateEmployeeInput.validateAge(Integer.parseInt(ageStr)));
        }

        System.out.print("New salary: ");
        String salaryStr = scanner.nextLine();
        if (!salaryStr.isBlank()) {
            employee.setSalary(ValidateEmployeeInput.validateSalary(Double.parseDouble(salaryStr)));
        }
    }
}