package application;

import java.lang.Math; // Math já faz parte de java.lang, então este import é opcional.

public class Rectangle1 {

    public double width;
    public double height;

    // MÉTODOS com letra minúscula, conforme a convenção do Java.
    // Não precisam mais guardar o resultado em uma variável da classe.
    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public double diagonal() {
        return Math.sqrt(width * width + height * height);
    }

    // A anotação @Override é uma boa prática.
    @Override
    public String toString() {
        // Chamamos String.format() e passamos os MÉTODOS diretamente.
        // Assim, garantimos que os valores estão sempre atualizados.
        return String.format(
                "Area: %.2f%nPerimetro: %.2f%nDiagonal: %.2f",
                area(),
                perimeter(),
                diagonal()
        );
    }
}