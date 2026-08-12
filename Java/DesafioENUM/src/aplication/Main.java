package aplication;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Enter Department's name: ");
        String departmentName = sc.nextLine();
        System.out.println("Enter Worker data: ");
        System.out.print("Name: ");
        String Workername = sc.nextLine();
        System.out.print("Level: ");
        String Workerlevel = sc.nextLine().toUpperCase();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        Worker worker = new Worker(Workername, WorkerLevel.valueOf(Workerlevel),salary, new Department(departmentName));

        System.out.print("How many contractes to this worker? ");
        int n = sc.nextInt();

        for (int i=1; i<= n ; i++){
            System.out.println("Enter contract #" + i + " data:");
            System.out.print("Date (DD/MM/YYYY): ");
            LocalDate contractDate = LocalDate.parse(sc.next(), formatter);
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour,hours);
            worker.addContract(contract);

        }

        System.out.println();
        System.out.print("Enter Month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0,2));
        int year = Integer.parseInt(monthAndYear.substring(3 ));

        System.out.println("Name: "+ worker.getName());
        System.out.println("Departmente: "+ worker.getDepartament().getName());
        System.out.println("Income for "+ monthAndYear + ": " + String.format("%.2f",worker.income(year,month)));




        sc.close();
    }

}