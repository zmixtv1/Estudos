using System;
using ClassFuncionario;
using ClassPessoa;
using System.Globalization;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Pessoa p1 = new Pessoa();
            Pessoa p2 = new Pessoa();

            Console.WriteLine("Dados da primeira Pessoa: ");
            Console.Write("Digite o nome: ");
            p1.Nome = Console.ReadLine();
            Console.Write("Digite a idade: ");
            p1.Idade = int.Parse(Console.ReadLine());

            Console.WriteLine("Dados da Segunda Pessoa: ");
            Console.Write("Digite o nome: ");
            p2.Nome = Console.ReadLine();
            Console.Write("Digite a idade: ");
            p2.Idade = int.Parse(Console.ReadLine());

            if (p1.Idade > p2.Idade)
            {
                Console.WriteLine($"{p1.Nome} é mais velho que {p2.Nome}!");
            }
            else if (p1.Idade < p2.Idade)
            {
                Console.WriteLine($"{p2.Nome} é mais velho que {p1.Nome}!");
            }
            else
            {
                Console.WriteLine("Ambos tem a mesma idade!");
            }


            Console.WriteLine(new string('_', 100));

            Funcionario f1 = new Funcionario();
            Funcionario f2 = new Funcionario();

            Console.WriteLine("Dados do primeiro funcionario: ");
            Console.Write("Digite o nome: ");
            f1.Nome = Console.ReadLine();
            Console.Write("Digite a salario: ");
            f1.Salario = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine("Dados do segudo funcionario: ");
            Console.Write("Digite o nome: ");
            f2.Nome = Console.ReadLine();
            Console.Write("Digite a salario: ");
            f2.Salario = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            double media = 0.0;

            media = (f1.Salario + f2.Salario) / 2;

            Console.WriteLine($"O salário médio é de {media.ToString("F2",CultureInfo.InvariantCulture)}");

























        }
    }
}