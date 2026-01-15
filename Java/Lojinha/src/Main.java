import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        ArrayList<ContaBancaria> ListaContas = new ArrayList<>();

        int opcao = 0;

        while (opcao != 3) {
            System.out.println("=== MENU ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar ");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    int opc = 0;
                    System.out.println("Para adicionar conta Corrente digite 1\nPara dicionar Conata Poupanca digite 2:");
                    opc = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("___________Cadastrando___________");
                    if (opc == 1){
                        System.out.print("Digite um numero de conta: ");
                        int numeroConta = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome do titular: ");
                        String titular = scanner.nextLine();
                        ContaBancaria novaConta = new ContaBancaria(numeroConta, titular);
                        ListaContas.add(novaConta);
                        System.out.println("Conta corrente cadastrada com sucesso!");
                    } else if (opc == 2) {
                        System.out.print("Digite um numero de conta: ");
                        int numeroConta = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome do titular: ");
                        String titular = scanner.nextLine();
                        System.out.print("Digite a taxa de rendimento: ");
                        double taxa = scanner.nextDouble();
                        scanner.nextLine();
                        ContaPoupanca novaConta = new ContaPoupanca(numeroConta, titular, taxa);
                        ListaContas.add(novaConta);
                        System.out.println("Conta poupança cadastrada com sucesso!");
                    }
                    break;
                case 2:
                    System.out.println("Buscando");
                    System.out.print("Digite o numero da conta: ");
                    int numeroPesquisa = scanner.nextInt();
                    boolean encontrei = false;
                    for(ContaBancaria conta : ListaContas){
                        if(numeroPesquisa == conta.getNumeroConta()){
                            encontrei = true;
                            System.out.println( "\nTitular da Conta: " + conta.getTitular()+"\n");
                        }
                    }
                    if(!encontrei){
                        System.out.println("Conta não encontrada");
                    }
                    break;
                case 3:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    System.out.println("Tente Novamente!");
            }
        }
//
//        for(int c=0 ; c < 3 ; c++){
//            System.out.print("Digite um numero de conta: ");
//            int numeroConta = scanner.nextInt();
//            scanner.nextLine();
//            System.out.print("Digite o nome do titular: ");
//            String titular = scanner.nextLine();
//
//            ContaBancaria novaConta = new ContaBancaria(numeroConta, titular);
//            ListaContas.add(novaConta);
//        }
//
//        for (ContaBancaria c : ListaContas){
//            c.depositar(100.00);
//            System.out.println("Saldo do(a) " + c.getTitular() + " é de " + c.getSaldo());
//            c.sacar(50.00);
//            System.out.println("Saldo do(a) " + c.getTitular() + " é de " + c.getSaldo());
//        }


    }
}