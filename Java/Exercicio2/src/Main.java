import application.Employee;

import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Employee();

        System.out.print("Name: ");
        employee.name = scanner.nextLine();
        System.out.print("Gross salary: ");
        employee.grossSalary = scanner.nextDouble();
        System.out.print("Tax: ");
        employee.tax = scanner.nextDouble();

        System.out.print(employee);

        System.out.print("Which percentage to incrase salary: ");
        double percentage = scanner.nextDouble();
        employee.IncreaseSalary(percentage);

        System.out.print(employee);


    }
}