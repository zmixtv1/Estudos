using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DesafioChatGPT.ListaTarefas;

namespace DesafioChatGPT
{
    internal class ListaTarefas
    {
        public class Tarefa
        {
            public string Descricao { get; set; }
            public string? Status { get; set; }
        }

        public List<Tarefa> Tarefas { get; private set; } = new List<Tarefa>();


        public void AdicionarTarefa(string descricao)
        {
            Tarefas.Add(new Tarefa { Descricao = descricao, Status = "Pendente" });
        }


        public string GetTarefa()
        {
            string resultado = null;

            foreach (var tarefa in Tarefas)
            {
                resultado += $"Descrição: {tarefa.Descricao}, Status: {tarefa.Status}\n";
            }

            return resultado;
        }

        public string SetStatus(int idTarefa)
        {
            if (idTarefa >= 0 && idTarefa < Tarefas.Count) // Valida se o ID está dentro do intervalo válido
            {
                Tarefas[idTarefa].Status = "Concluído"; // Altera o status para "Concluído"
                return $"Tarefa \"{Tarefas[idTarefa].Descricao}\" marcada como Concluída!";
            }
            else
            {
                return "ID da tarefa inválido!"; // Retorna uma mensagem de erro caso o ID seja inválido
            }
        }


    }
}


/*
 === Sistema de Gerenciamento de Tarefas ===  
1. Adicionar tarefa  
2. Listar tarefas  
3. Concluir tarefa  
4. Remover tarefa  
5. Sair  

Escolha uma opção: 1  
Digite a descrição da tarefa: Estudar C#  
Tarefa adicionada com sucesso!  

Escolha uma opção: 2  
ID: 1 | Descrição: Estudar C# | Status: Pendente  

Escolha uma opção: 3  
Digite o ID da tarefa concluída: 1  
Tarefa marcada como concluída!  

Escolha uma opção: 2  
ID: 1 | Descrição: Estudar C# | Status: Concluída  
*/