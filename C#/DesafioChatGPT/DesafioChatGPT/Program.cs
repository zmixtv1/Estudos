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
                        Console.WriteLine(lista.GetTarefa()); 
                        break;
                    case 3:
                    
                        Console.WriteLine("=== Tarefas ===");
                        for (int i = 0; i < lista.Tarefas.Count; i++)
                        {
                            Console.WriteLine($"ID: {i}, Descrição: {lista.Tarefas[i].Descricao}, Status: {lista.Tarefas[i].Status}");

                        }

                        Console.Write("\nDigite o ID da tarefa que deseja concluir: ");
                        int id = int.Parse(Console.ReadLine());

                        string resultado = lista.SetStatus(id);
                        Console.WriteLine(resultado);
                        break;
                    case 4:
                        Thread.Sleep(2000);
                        Console.WriteLine("\n\nSaindo...");
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

 




## OUTRO CODIGO A PARTIR DAQUI!!!!




using System;

class Pessoa
{
    public string Nome { get; set; }
    public int Idade { get; set; }
    public string Email { get; set; }
}

class Program
{
    static void Main()
    {
        List<Pessoa> pessoas = new List<Pessoa>();

        // Adicionando pessoas à lista
        pessoas.Add(new Pessoa { Nome = "Rodrigo", Idade = 30, Email = "rodrigo@email.com" });
        pessoas.Add(new Pessoa { Nome = "Ana", Idade = 25, Email = "ana@email.com" });

        // Acessando dados individualmente
        foreach (var pessoa in pessoas)
        {
            Console.WriteLine($"Nome: {pessoa.Nome}, Idade: {pessoa.Idade}, Email: {pessoa.Email}");
        }

        // Filtrando pelo nome
        var pessoaSelecionada = pessoas.Find(p => p.Email == "rodrigo@email.com");
        if (pessoaSelecionada != null)
        {
            Console.WriteLine($"Nome: {pessoaSelecionada.Nome}, Idade: {pessoaSelecionada.Idade}, Email: {pessoaSelecionada.Email}");
        }
    }
}


 
 
 
 
 
 
 
 
 */