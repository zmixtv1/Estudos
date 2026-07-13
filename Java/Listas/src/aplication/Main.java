package aplication;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> lista = new ArrayList<>();

        lista.add("Teste");
        lista.add("Teste2");
        lista.add("Teste3");
        lista.add("Teste4");
        lista.add("Teste5");

        lista.remove("Teste2");
        lista.removeLast();

        lista.add(1,"Teste2atualizado");


        for(String nome: lista) {
            System.out.println(nome);
        }
    }
}