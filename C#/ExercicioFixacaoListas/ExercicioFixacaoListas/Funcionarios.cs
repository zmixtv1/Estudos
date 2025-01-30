using System.Globalization;

namespace ExercicioFixacaoListas
{
    internal class Funcionarios
    {
        public class InfoFuncionarios
        {
            public int Id { get; set; }
            public string Name { get; set; }
            public double Salary { get; set; }
        }

        public List<InfoFuncionarios> funcionarios { get; set; } = new List<InfoFuncionarios>();

        public void AdicionarFuncionario(int id, string name, double salary)
        {
            funcionarios.Add(new InfoFuncionarios { Id = id, Name = name, Salary = salary });
        }

        public string getFuncionarios()
        {
            string resultado = null;
            foreach (var funcionario in funcionarios)
            {
                resultado += $"{funcionario.Id}, {funcionario.Name}, {funcionario.Salary}\n";
            }
            return resultado;
        }

        public void setSalaryFuncionario(int id, double percentage)
        {
            foreach (var funcionario in funcionarios)
            {
                if (funcionario.Id == id)
                {
                    funcionario.Salary += funcionario.Salary * (percentage / 100);
                }
            }
        }

    }
}
