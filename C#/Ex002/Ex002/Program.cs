using System;

using System.Globalization;
using static System.Net.Mime.MediaTypeNames;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                try
                {
                    Console.Write("Digite um número aleatório: ");
                    int n1 = int.Parse(Console.ReadLine());
                    Console.WriteLine(n1);
                    Console.WriteLine("Digite seu nome, gênero, idade e altura. utilizando espaço entre os dados: ");
                    string[] vetor = Console.ReadLine().Split(' ');

                    string nome = vetor[0];
                    string sexoo = vetor[1];
                    int idade = int.Parse(vetor[2]);
                    double altura = double.Parse(vetor[3], CultureInfo.InvariantCulture);

                    string sexo = sexoo.ToUpper();

                    if (sexo == "M")
                    {
                        sexo = "Masculino";
                        Console.WriteLine($"Olá sou {nome}, Tenho {idade} anos, meu Gênero é {sexo}. Tenho {altura} de altura!");
                    }
                    else if (sexo == "F")
                    {
                        sexo = "Feminino";
                        Console.WriteLine($"Olá sou {nome}, Tenho {idade} anos, meu Gênero é {sexo}. Tenho {altura} de altura!");
                    }
                    else
                    {
                        Console.WriteLine($"Olá sou {nome}, Tenho {idade} anos, meu Gênero é {sexo}. Tenho {altura} de altura!");
                    }
                    break;
                }
                catch
                {
                    Console.WriteLine("erro");
                    break;
                }
            }
        }
    }
}