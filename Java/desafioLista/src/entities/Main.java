package entities;

import models.Funcionario;

import javax.swing.plaf.synth.SynthUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Funcionario> list = new ArrayList<>();
        System.out.print("How many employees will be registred? ");


        int employeers = sc.nextInt();

        for(int c=0;c<employeers;c++){
            sc.nextLine();
            System.out.printf("Emplyoee #%d: \n", c+1);
            System.out.print("Id: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            Funcionario emp = new Funcionario(id, name , salary);
            list.add(emp);
        }

            System.out.print("Enter the employee id that will have salary increase: ");
            int increase_id = sc.nextInt();
            System.out.print("Enter the percentage: ");
            double percentage = sc.nextDouble();

            for(Funcionario funcionario: list){
                if(funcionario.getId() == increase_id){
                    funcionario.aumento(percentage);
                }else{
                    System.out.println("HTis id does not exist! ");
                }
            }

            System.out.println("List of employees: ");
            for(Funcionario funcionario: list){
                System.out.println(funcionario);
            }

    }
}