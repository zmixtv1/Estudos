using System;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Enter the order value: ");
            int order = int.Parse(Console.ReadLine());
            int negative = 0;

            int[,] mat = new int[order, order];

            for (int i = 0; i < order; i++)
            {

                string[] values = Console.ReadLine().Split(' ');

                for (int j = 0; j < order; j++)
                {
                    mat[i, j] = int.Parse(values[j]);

                    if (mat[i, j] < 0)
                    {
                        negative++;
                    }
                }
            }

            Console.WriteLine("Main diagonal: ");

            for (int i = 0; i < order; i++)
            {
                Console.Write($"{mat[i, i]} ");
            }

            Console.Write($"\nNegative numbers = {negative}");
        }
    }
}
