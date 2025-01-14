using System;
using System.Globalization;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            double x1, x2, x3, y1, y2, y3;

            Console.WriteLine("Entre com as medidas do triângulo x: ");
            x1 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            x2 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            x3 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

            Console.WriteLine("Entre com as medidas do triângulo y: ");

            y1 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            y2 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            y3 = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);


            double px = (x1 + x2 + x3) / 2;
            double areax = Math.Sqrt(px * (px - x1) * (px - x2) * (px - x3));

            double py = (y1 + y2 + y3) / 2;
            double areay = Math.Sqrt(py * (py - y1) * (py - y2) * (py - y3));

            Console.WriteLine($"Área de x: {areax.ToString("F4", CultureInfo.InvariantCulture)}");
            Console.WriteLine($"Área de y: {areay.ToString("F4", CultureInfo.InvariantCulture)}");

            if (areax > areay)
            {
                Console.WriteLine("Maior área: X");
            }
            else
            {
                Console.WriteLine("Maior área: Y");
            }
        }
    }
}