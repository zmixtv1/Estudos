using System;
using System.Globalization;


namespace ExercicioProduto
{
    internal class Estoque
    {
        public string Nome;
        public double Preco;
        private int _quantidade;

        public int Quantidade
        {
            get
            {
                return Quantidade;
            }
            set
            {
                _quantidade = value;
            }
        }

        public Estoque()
        {

        }

        public Estoque(string nome, double preco, int quantidade)
        {
            Nome = nome;
            Preco = preco;
            _quantidade = quantidade;
        }

        public double ValorTotalEstoque()
        {
            double total = Preco * _quantidade;
            return total;
        }

        public void AdicionarProdutos(int quantidade)
        {
            _quantidade += quantidade;
        }

        public void RemoverProdutos(int quantidade)
        {
            _quantidade -= quantidade;
        }

        public override string ToString()
        {
            return $"{Nome}, ${Preco.ToString("F2", CultureInfo.InvariantCulture)} e {_quantidade} unidades.\nTotal: ${ValorTotalEstoque().ToString("F2", CultureInfo.InvariantCulture)}";
        }
    }
}
