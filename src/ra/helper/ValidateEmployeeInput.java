package ra.helper;

import ra.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ValidateEmployeeInput {
    public static String validateId(String empId) {
        String EMPLOYEE_ID_PATTERN = "^NV\\d{3}$";
        if (empId == null || empId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (!empId.matches(EMPLOYEE_ID_PATTERN)) {
            throw new IllegalArgumentException("Invalid Employee ID format. Expected format: NV and more than 3 element (vd: NV001)");
        }
        return empId;
    }
    public static int validateAge(int age){
            if(age < 18 || age > 70){
                throw new IllegalArgumentException("Tuổi phải lớn hơn 18 hoặc nhỏ hơn 70");
            }
            return age;
    }
    public static double validateSalary(double salary){
        if(salary < 0.0){
            throw new IllegalArgumentException("Salary must be more than 0");
        }
        return salary;
    }

    public static void checkUniqueId(String empId, List<Employee> employees) {
        for (Employee employee: employees) {
            if (employee.getEmpId().equals(employees)) {
                throw new IllegalArgumentException("Id đã tồn tại");
            }
        }
    }
}
