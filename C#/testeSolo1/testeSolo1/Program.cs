using System;

namespace MyApp
{
    internal class Program
    {
        static string ObterNome()
        {
            Console.Write("Digite seu nome: ");
            return Console.ReadLine();
        }
        static string ObterGenero()
        {
            Console.Write("Digite seu Genero: ");
            return Console.ReadLine();
        }
        static uint ObterIdade()
        {
            Console.Write("Digite sua idade: ");
            return uint.Parse(Console.ReadLine());
        }

        static void Responder(string nome)
        {
            double saldoMaria = 10.35784;
            double saldoRodrigo = 12.4141;

            if (nome == "Maria")
            {
                Console.WriteLine($"\nOlá {nome}, Seu saldo é de {saldoMaria}");
            }
            else if (nome == "Rodrigo")
            {
                Console.WriteLine($"\nOlá {nome}, Seu saldo é de R$ {saldoRodrigo}");
            }

        }

        static void Main(string[] args)
        {
            string nome = ObterNome();
            string genero = ObterGenero();
            uint idade = ObterIdade();

            Responder(nome);


        }

    }
}

