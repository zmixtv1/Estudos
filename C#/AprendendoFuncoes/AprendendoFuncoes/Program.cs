using System;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Digite o primeiro número: ");
            int n1 = int.Parse(Console.ReadLine());
            Console.Write("Digite o segudo número: ");
            int n2 = int.Parse(Console.ReadLine());
            Console.Write("Digite o terceiro número: ");
            int n3 = int.Parse(Console.ReadLine());

            double resultado = Maior(n1, n2, n3);
            Console.WriteLine("O Maior é: " + resultado);

            Console.WriteLine("O Console digitou 4, 8 e 9");
            resultado = Maior(4, 8, 9);

            Console.WriteLine("O Maior é: " + resultado);
        }


        static int Maior(int a, int b, int c)
        {
            int valorFinal;

            if (a > b && a > c)
            {
                valorFinal = a;
            }
            else if (b > a && b > c)
            {
                valorFinal = b;
            }
            else
            {
                valorFinal = c;
            }

            return valorFinal;
        }
    }
}