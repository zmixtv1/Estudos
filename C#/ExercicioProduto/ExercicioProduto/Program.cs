using System;
using ExercicioProduto;
using System.Globalization;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Estoque p = new Estoque();

            Console.WriteLine("Entre os dados do produto: ");
            Console.Write("Produto: ");
            p.Nome = Console.ReadLine();
            Console.Write("Preço: ");
            p.Preco = double.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);
            Console.Write("Quantidade no estoque: ");
            p.Quantidade = int.Parse(Console.ReadLine());

            Console.WriteLine($"\nDados do Produto: {p}");
            Console.Write("Deseja adicionar ou remover produto? [Sim/nao]: ");
            string resposta = Console.ReadLine().ToLower();

            while (resposta == "sim")
            {
                Console.Write("\nPara Adicionar Digite [1] e Para Remover digite [2]: ");
                int resp = int.Parse(Console.ReadLine());
                if (resp == 1)
                {
                    Console.Write("\nDigite a quantidade que deseja adicionar: ");
                    int qte = int.Parse(Console.ReadLine());
                    p.AdicionarProdutos(qte);
                    Console.WriteLine($"\nDados do Produto: {p}");
                }
                else if (resp == 2)
                {
                    Console.Write("\nDigite a quantidade que deseja remover: ");
                    int qte = int.Parse(Console.ReadLine());
                    p.RemoverProdutos(qte);
                    Console.WriteLine($"\nDados do Produto: {p}");
                }
                else 
                {
                    Console.WriteLine("Digito não encontrado, tente novamente...\n");
                }
                Console.Write("Deseja adicionar ou remover produto? [Sim/nao]: ");
                resposta = Console.ReadLine().ToLower();
            }
        }
    }
}