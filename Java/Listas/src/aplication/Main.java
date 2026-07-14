package aplication;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> lista = new ArrayList<>();

        lista.add("Rodrigo");
        lista.add("Cecilia");
        lista.add("monica");
        lista.add("fernanda");
        lista.add("Teste5");

        lista.remove("Teste5");
        lista.removeLast();

        lista.removeIf(x -> x.charAt(0) == 'm');





        lista.add(1,"Teste2 atualizado");

        for(String nome: lista) {
            System.out.println(nome);
        }
    }
}