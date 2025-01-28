using System;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            List<string> list = new List<string>();

            List<string> list2 = new List<string> { "Rodrigo", "Maria", "João", "Joyce" };

            Console.Write("Digite um nome: ");
            string nome = Console.ReadLine();
            Console.Write("Digite Outro nome: ");
            string nome2 = Console.ReadLine();
            list.Add(nome);
            list.Insert(0, nome2);

            Console.WriteLine(list2.Count);

            foreach (string item in list)
            {
                Console.WriteLine(item);
                Thread.Sleep(1000);
            }

        }
    }
}
