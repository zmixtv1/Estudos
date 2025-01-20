using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesafioChatGPT
{
    internal class ListaTarefas
    {
        public int IdTarefa { get; private set; }
        public string Descricao { get; private set; }
        public string Status { get; private set; }


        public void AdicionarTarefa(string descricao, string status)
        {

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