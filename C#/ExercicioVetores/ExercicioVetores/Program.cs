using System;
using ExercicioVetores;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.Write("How many rooms will be rented? ");
            int n = int.Parse(Console.ReadLine());

            Quartos[] vect = new Quartos[10];

            for (int i = 1; i <= n; i++)
            {
                Console.WriteLine($"Rent #{i}");
                Console.Write("Name: ");
                string name = Console.ReadLine();
                Console.Write("Email: ");
                string email = Console.ReadLine();
                Console.Write("Room: ");
                int room = int.Parse(Console.ReadLine());
                if (vect[room] != null)
                {
                    Console.WriteLine("Room already occupied!");

                    Console.Write("Enter another Room: ");
                    int otherRoom = int.Parse(Console.ReadLine());
                    vect[otherRoom] = new Quartos(name, email);
                }
                else
                {
                    vect[room] = new Quartos(name, email);
                }
            }

            Console.WriteLine();
            Console.WriteLine("Quartos Ocupados: ");
            for (int i = 0; i < 10; i++)
            {
                if (vect[i] != null)
                {
                    Console.WriteLine($"{i}: {vect[i]}");
                }
            }
        }
    }
}
