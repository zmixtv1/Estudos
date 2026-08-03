package application; // Ou o pacote onde está a sua Main (ex: sem pacote se estiver na raiz)
import entities.Despesa; // Importando a classe profissional do outro pacote!

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Despesa> listaDespesas = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n==============================================");
            System.out.println("     🏢 MINI-ERP FINANCEIRO (7 OPÇÕES)       ");
            System.out.println("==============================================");
            System.out.println("[1] ➕ Cadastrar Nova Despesa");
            System.out.println("[2] 📋 Listar Todas as Despesas");
            System.out.println("[3] 💰 Pagar uma Despesa (por ID)");
            System.out.println("[4] 🚨 Radar de Contas Vencidas");
            System.out.println("[5] 📊 Relatório Financeiro Executivo");
            System.out.println("[6] 🗑️  Excluir uma Despesa (por ID)");
            System.out.println("[7] 🚪 Sair do Sistema");
            System.out.print("-> Escolha uma opção [1 a 7]: ");

            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("\n--- ➕ NOVA DESPESA ---");
                    System.out.print("Descrição da conta: ");
                    String desc = sc.nextLine();
                    System.out.print("Valor (R$): ");
                    double valor = sc.nextDouble();
                    System.out.print("Vence em quantos dias? (Ex: 5 ou -2 para vencida): ");
                    int dias = sc.nextInt();

                    LocalDate vencimento = LocalDate.now().plusDays(dias);

                    // Criando o objeto encapsulado:
                    listaDespesas.add(new Despesa(desc, valor, vencimento));

                    System.out.println("✅ Despesa cadastrada! Vencimento em: " + vencimento.format(fmt));
                    break;

                case 2:
                    System.out.println("\n--- 📋 TODAS AS DESPESAS ---");
                    if (listaDespesas.isEmpty()) {
                        System.out.println("Nenhuma despesa cadastrada.");
                    } else {
                        for (int i = 0; i < listaDespesas.size(); i++) {
                            Despesa d = listaDespesas.get(i);
                            // Usando isPago() e métodos GET:
                            String status = d.isPago() ? "[PAGO ✅]" : (d.isVencida() ? "[ATRASADA 🚨]" : "[PENDENTE ⏳]");
                            System.out.printf("ID #%d | %s | R$ %.2f | Vence: %s | %s%n",
                                    i, d.getDescricao(), d.getValor(), d.getDataVencimento().format(fmt), status);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- 💰 PAGAMENTO ---");
                    System.out.print("Digite o ID # da despesa para pagar: ");
                    int idPagar = sc.nextInt();

                    if (idPagar >= 0 && idPagar < listaDespesas.size()) {
                        Despesa d = listaDespesas.get(idPagar);
                        if (d.isPago()) { // Verificando com isPago()
                            System.out.println("⚠️ Esta conta já foi paga anteriormente!");
                        } else {
                            d.setPago(true); // Alterando com SETTER!
                            System.out.println("✅ Conta '" + d.getDescricao() + "' marcada como PAGA!");
                        }
                    } else {
                        System.out.println("❌ ID não encontrado!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- 🚨 RADAR DE CONTAS VENCIDAS ---");
                    boolean encontrouVencida = false;
                    for (int i = 0; i < listaDespesas.size(); i++) {
                        Despesa d = listaDespesas.get(i);
                        if (d.isVencida()) {
                            System.out.printf("🚨 ID #%d | %s | R$ %.2f | Venceu dia: %s%n",
                                    i, d.getDescricao(), d.getValor(), d.getDataVencimento().format(fmt));
                            encontrouVencida = true;
                        }
                    }
                    if (!encontrouVencida) {
                        System.out.println("🥳 Nenhuma conta vencida no momento!");
                    }
                    break;

                case 5:
                    System.out.println("\n--- 📊 RELATÓRIO EXECUTIVO ---");
                    double totalGeral = 0;
                    double totalPago = 0;
                    double totalPendente = 0;
                    Despesa maisCara = null;

                    for (Despesa d : listaDespesas) {
                        totalGeral += d.getValor();
                        if (d.isPago()) {
                            totalPago += d.getValor();
                        } else {
                            totalPendente += d.getValor();
                        }

                        // Comparando valores através do getValor()
                        if (maisCara == null || d.getValor() > maisCara.getValor()) {
                            maisCara = d;
                        }
                    }

                    System.out.printf("💵 Total Cadastrado: R$ %.2f%n", totalGeral);
                    System.out.printf("✅ Total Pago:       R$ %.2f%n", totalPago);
                    System.out.printf("⏳ Total Pendente:   R$ %.2f%n", totalPendente);
                    if (maisCara != null) {
                        System.out.printf("🔥 Conta mais cara:  %s (R$ %.2f)%n", maisCara.getDescricao(), maisCara.getValor());
                    }
                    break;

                case 6:
                    System.out.println("\n--- 🗑️ EXCLUSÃO ---");
                    System.out.print("Digite o ID # da despesa para excluir: ");
                    int idExcluir = sc.nextInt();

                    if (idExcluir >= 0 && idExcluir < listaDespesas.size()) {
                        Despesa removida = listaDespesas.remove(idExcluir);
                        System.out.println("🗑️ Despesa '" + removida.getDescricao() + "' excluída com sucesso!");
                    } else {
                        System.out.println("❌ ID não encontrado!");
                    }
                    break;

                case 7:
                    System.out.println("\n🚪 Encerrando o sistema financeiro. Bom trabalho!");
                    break;

                default:
                    System.out.println("❌ Opção inválida! Escolha um número entre 1 e 7.");
                    break;
            }
        }

        sc.close();
    }
}