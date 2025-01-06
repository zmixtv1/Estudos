using System;
/*
namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Digite Seu Nome:");

            // Lendo a entrada do usuário e salvando em uma variável
            string nome = Console.ReadLine();

            // Exibindo o valor digitado

            Console.WriteLine($"Olá {nome}, Tudo Bem?");

            string resposta = Console.ReadLine()?.ToLower();

            if (resposta == "sim")
            {
                Console.WriteLine("Ótimo, fico feliz que esteja bem!");
            }
            else
            {
                Console.WriteLine("Espero que melhore!");
            }
        }
    }
}

*/
 using System;

namespace MyApp
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string nome = ObterNome();
            CumprimentarUsuario(nome);

            string resposta = ObterRespostaUsuario("Você está bem?");

            ExibirMensagemComBaseNaResposta(resposta);
        }

        static string ObterNome()
        {
            Console.Write("Digite seu nome: ");
            return Console.ReadLine();
        }

        static void CumprimentarUsuario(string nome)
        {
            Console.WriteLine($"Olá, {nome}, tudo bem?");
        }

        static string ObterRespostaUsuario(string pergunta)
        {
            Console.Write(pergunta + $" \n");
            return Console.ReadLine()?.ToLower(); // Convertendo para minúsculas diretamente
        }

        static void ExibirMensagemComBaseNaResposta(string resposta)
        {
            if (resposta == "sim")
            {
                Console.WriteLine("Ótimo, fico feliz que esteja bem!");
            }
            else
            {
                Console.WriteLine("Espero que melhore!");
            }
        }
    }
}
