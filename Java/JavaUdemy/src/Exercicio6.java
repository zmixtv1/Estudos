import java.util.Locale;
import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite  três pontos Flutuantes: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double triangulo = areaTriangulo(a,b,c);
        double circulo = areaCirculo(c);
        double trapezio = areaTrapezio(a,b,c);
        double quadrado = areaQuadrado(b);
        double retangulo = areaRetangulo(a,b);

        System.out.printf("Triangulo: %.3f%n" +
                "Circulo: %.3f%n" +
                "Trapézio: %.3f%n" +
                "Quadrado: %.3f%n" +
                "Retângulo: %.3f%n", triangulo, circulo, trapezio,quadrado,retangulo);

    }
    public static double areaTriangulo(double a, double b, double c){
        return (a * c) / 2 ;
    }

    public static  double areaCirculo(double raio){
        double pi = 3.14159;
        return pi * raio * raio;
    }

    public static double areaTrapezio(double baseA, double baseB, double altura){
        return ((baseA + baseB) * altura) / 2;
    }

    public  static double areaQuadrado(double b){
        return b * b;
    }

    public static  double areaRetangulo(double a, double b){
        return a * b;
    }
}
