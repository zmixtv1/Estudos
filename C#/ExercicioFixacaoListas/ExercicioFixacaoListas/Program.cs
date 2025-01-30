using System;
using System.Globalization;
using ExercicioFixacaoListas;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Funcionarios listaFuncionario = new Funcionarios();

            Console.Write("Quantos funcionarios deseja cadastrar: ");
            int cadastro = int.Parse(Console.ReadLine());

            for (int i = 0; i < cadastro; i++)
            {
                Console.WriteLine($"\nEmpregado : #{i + 1}");

                Console.Write($"Id: ");
                int Id = int.Parse(Console.ReadLine());
                Console.Write($"Nome: ");
                string nome = Console.ReadLine();
                Console.Write($"Salario: ");
                double salario = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

                listaFuncionario.AdicionarFuncionario(Id, nome, salario);
            }

            Console.Write("\nEntre com o ID que deseja almentar o salario: ");
            int IdAlmento = int.Parse(Console.ReadLine());
            Console.Write("\nEntre com a porcentagem: ");
            double porcentagem = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            listaFuncionario.setSalaryFuncionario(IdAlmento, porcentagem);

            Console.WriteLine("\nLista de funcionarios Atulaizada:");
            Console.WriteLine(listaFuncionario.getFuncionarios());


        }
    }
}
