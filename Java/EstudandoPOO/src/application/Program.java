package application;

import entities.Product;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        Product product = new Product();
        System.out.printf("Enter Product data: %n");
        System.out.printf("Name: ");
        product.name = scanner.nextLine();

        System.out.printf("Price: ");
        product.price = scanner.nextDouble();

        System.out.printf("Quantity in stock: ");
        product.quantity = scanner.nextInt();

        System.out.printf("Product data: %s, $ %.2f,%d units , Total: $ $%.2f",product.name, product.price, product.quantity ,product.TotalValueINStock());

        System.out.printf("%n Enter the number of products to be added in stock: ");
        int moreQuantity = scanner.nextInt();
        product.AddProducts(moreQuantity);

        System.out.printf("update data: %s, $ %.2f,%d units , Total: $ $%.2f",product.name, product.price, product.quantity ,product.TotalValueINStock());

        System.out.printf("%nEnter the number of products to be removed from stock: ");
        int deleteQuantity = scanner.nextInt();
        product.RemoveProducts(deleteQuantity);
        System.out.printf("update data: %s, $ %.2f,%d units , Total: $ $%.2f%n",product.name, product.price, product.quantity ,product.TotalValueINStock());


    }
}
