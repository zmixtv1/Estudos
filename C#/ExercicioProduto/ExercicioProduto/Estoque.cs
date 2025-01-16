using System;
using System.Globalization;


namespace ExercicioProduto
{
    internal class Estoque
    {
        public string Nome;
        public double Preco;
        public int Quantidade;
        

        public double ValorTotalEstoque() 
        {
            double total = Preco * Quantidade;
            return total;
        }

        public void AdicionarProdutos(int quantidade)
        {
            Quantidade += quantidade;
        }

        public void RemoverProdutos(int quantidade)
        {
            Quantidade -= quantidade;
        }

        public override string ToString()
        {
            return $"{Nome}, ${Preco.ToString("F2", CultureInfo.InvariantCulture)} e {Quantidade} unidades.\nTotal: ${ValorTotalEstoque().ToString("F2", CultureInfo.InvariantCulture)}";
        }
    }
}
