using System;
using System.Globalization;

namespace ExercicioVetores
{
    internal class Quartos
    {
        public string Name { get; set; }
        public string Email { get; set; }

        public Quartos(string nome, string email)
        {
            Name = nome;
            Email = email;
        }

        public override string? ToString()
        {
            return Name + "," + Email;
        }
    }
}
