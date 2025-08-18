import application.Rectangle1;

import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        // É uma boa prática dar nomes mais descritivos às variáveis
        Rectangle1 rectangle = new Rectangle1();

        // Usar System.out.print() faz com que o usuário digite na mesma linha
        System.out.println("Enter rectangle width and height: ");
        rectangle.width = scanner.nextDouble();
        rectangle.height = scanner.nextDouble();

        System.out.println(); // Apenas para pular uma linha e organizar a saída

        // ✨ A mágica acontece aqui!
        // Ao passar o objeto para o println, o Java chama o método .toString() automaticamente.
        System.out.println(rectangle);

        scanner.close(); // É uma boa prática fechar o Scanner quando não for mais usar.
    }
}