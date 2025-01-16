using System;
using ExercicioTriangulo;
using System.Globalization;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Triangulo t = new Triangulo();

            Console.WriteLine("Entre com a largura e a altura do triangulo: ");
            Console.Write("Largura: ");
            t.Largura = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            Console.Write("altura: ");
            t.Altura = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine(t);
        }
    }
}