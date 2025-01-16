using System;
using System.Globalization;
using ClassTriagulo;


namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Triangulo x, y;

            x = new Triangulo();
            y = new Triangulo();


            Console.WriteLine("Entre com as medidas do triângulo x: ");
            x.A = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            x.B = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            x.C = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
             
            Console.WriteLine("Entre com as medidas do triângulo y: ");

            y.A = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            y.B = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            y.C = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);


            double areax = x.Area();
            double areay = y.Area();

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