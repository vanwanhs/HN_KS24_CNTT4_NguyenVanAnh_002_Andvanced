package ra.presentation;

import ra.business.EmployeeBusiness;
import ra.entity.Employee;
import ra.helper.InputHelper;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeBusiness employeeBusiness = EmployeeBusiness.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. List Employee
                    2. Add Employee
                    3. Update Employee by ID
                    4. Delete Employee by ID
                    5. Find Employee by name
                    6. Filter Employee by salary
                    7. Sort Employee by salary (DESC)
                    8. Exit
                    """);

            System.out.print("Choose: ");
            int choice = InputHelper.inputInteger(scanner);

            try {
                switch (choice) {

                    case 1 -> employeeBusiness.listAllEmployee();

                    case 2 -> {
                        while (true) {
                            Employee employee = new Employee();
                            employee.inputDate(scanner);

                            employeeBusiness.addEmployee(employee);
                            System.out.println("Added successfully!");

                            System.out.print("Continue? (y/n): ");
                            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter ID to update: ");
                        String id = scanner.nextLine();

                        Optional<Employee> empOpt = employeeBusiness.findEmployeeById(id);

                        if (empOpt.isPresent()) {
                            employeeBusiness.updateEmployee(empOpt.get(), scanner);
                            System.out.println("Updated successfully!");
                        } else {
                            System.out.println("Employee not found!");
                        }
                    }

                    case 4 -> {
                        System.out.print("Enter ID to delete: ");
                        String id = scanner.nextLine();

                        employeeBusiness.deleteEmployee(id);
                        System.out.println("Deleted successfully!");
                    }

                    case 5 -> {
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        List<Employee> result = employeeBusiness.findEmployeesByName(name);
                        employeeBusiness.showEmployee(result);
                    }

                    case 6 -> {
                        System.out.print("Enter salary threshold: ");
                        double salary = InputHelper.inputDouble(scanner);

                        employeeBusiness.filterEmployeeBySalary(salary);
                    }

                    case 7 -> employeeBusiness.sortEmployeeBySalaryDescending();

                    case 8 -> {
                        System.out.println("Exit...");
                        return;
                    }

                    default -> System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}