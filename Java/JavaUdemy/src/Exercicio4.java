import java.util.Locale;
import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o Numero do funcionario: ");
        int employeed = scanner.nextInt();

        System.out.print("Digite as horas trabalhadas: ");
        int hours = scanner.nextInt();

        System.out.print("Digite o valor que recebe por hora: ");
        double cash = scanner.nextDouble();

        double resposta = salary_employeed(hours, cash);
        System.out.printf("Number = %d%nSalary = U$%.2f%n",employeed, resposta);
    }
    public static double salary_employeed(int hours,double cash){
        double salary = hours * cash;
        return salary;
    }

}
