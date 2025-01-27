using System;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Digite quanto produtos deseja cadastrar");
            int n = int.Parse(Console.ReadLine());

            List<string> produto = new List<string>();

            double[] valor = new double[n];

            for (int i = 0; i < n; i++)
            {
                
            }
        }
    }
}






/*
using System;
using System.Globalization;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());

            double[] vect = new double[n];

            for (int i = 0; i < n; i++)
            {
                vect[i] = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

                if (i == n - 1)
                {
                    Console.WriteLine($"No vetor {i - 1} está {vect[0]}");
                    Console.WriteLine($"No vetor {i} está {vect[1]}");
                    Console.WriteLine($"No vetor {i + 1} está {vect[2]}");
                }
            }


            double sum = 0.0;
            for (int i = 0; i < n; i++)
            {
                sum += vect[i];
            }


            double avg = sum / n;

            Console.WriteLine($"AVERAGE HEIGHT = {avg.ToString("F3",CultureInfo.InvariantCulture)}");

        }
    }
}
*/