package ra.entity;

import ra.helper.InputHelper;
import ra.helper.ValidateEmployeeInput;

import java.util.Scanner;

public class Employee {
    private String empId;
    private String empName;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(String empId, String empName, int age, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputDate(Scanner scanner) {
        System.out.println("Nhập mã nhân viên (vd: NV001): ");
        this.empId = ValidateEmployeeInput.validateId(scanner.nextLine());
        System.out.println("Nhập tên nhân viên: ");
        this.empName = scanner.nextLine();
        System.out.println("Nhập tuổi nhân viên: ");
        this.age = ValidateEmployeeInput.validateAge(InputHelper.inputInteger(scanner));
        System.out.println("Nhaapj tieefn luwong của nhân viên: ");
        this.salary = ValidateEmployeeInput.validateSalary(InputHelper.inputDouble(scanner));
    }
    public void displayData() {
        System.out.printf("| %-12s | %-12s | %3d | %10.5f |%n",
                this.empId,
                this.empName,
                this.age,
                this.salary);
    }
}


