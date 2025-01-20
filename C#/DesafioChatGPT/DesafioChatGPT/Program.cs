using System;
using DesafioChatGPT;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {

            ListaTarefas lista = new ListaTarefas();

            Console.WriteLine("=== Cadastro de Tarefas Simples ===");
            Console.WriteLine("1. Adicionar tarefa");
            Console.WriteLine("2. Exibir tarefa");
            Console.WriteLine("3. Concluir tarefa");
            Console.WriteLine("4. Sair\n");

            bool sair = false;

            while (!sair)
            {
                Console.Write("\n Escolha uma opção: ");
                int opcao = int.Parse(Console.ReadLine());

                switch (opcao)
                {
                    case 1:
                        Console.Write("Digite a descrição da tarefa: ");
                        string tarefa = Console.ReadLine();
                        lista.AdicionarTarefa(tarefa);
                        break;
                    case 2:
                        Console.WriteLine(lista); 
                        break;
                    case 3:
                        Console.WriteLine(lista.ConcluirTarefa());
                        break;
                    case 4:
                        Console.WriteLine("Saindo...");
                        sair = true;
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }

        }
    }
}


/*
 === Sistema de Gerenciamento de Tarefas ===  
1. Adicionar tarefa  
2. Listar tarefas  
3. Concluir tarefa  
4. Remover tarefa  
5. Sair  

Escolha uma opção: 1  
Digite a descrição da tarefa: Estudar C#  
Tarefa adicionada com sucesso!  

Escolha uma opção: 2  
ID: 1 | Descrição: Estudar C# | Status: Pendente  

Escolha uma opção: 3  
Digite o ID da tarefa concluída: 1  
Tarefa marcada como concluída!  

Escolha uma opção: 2  
ID: 1 | Descrição: Estudar C# | Status: Concluída  
*/