import requests
from bs4 import BeautifulSoup
import pandas as pd
from concurrent.futures import ThreadPoolExecutor

def processar_pagina(pagina):
    if pagina == 1:
        url = "https://books.toscrape.com/"
    else:
        url = f"https://books.toscrape.com/catalogue/page-{pagina}.html"

    try:
        resposta = requests.get(url, headers={"User-Agent": "Mozilla/5.0"}, timeout=10)
        soup = BeautifulSoup(resposta.text, "html.parser")
        livros = soup.find_all("li", class_='col-xs-6 col-sm-4 col-md-3 col-lg-3')

        livros_5_estrelas = []

        for livro in livros:
            estrela = livro.find("p", class_="star-rating")
            if estrela and 'Five' in estrela.get('class', []):
                titulo = livro.find("h3").find("a")["title"]
                preco = livro.find("p", class_='price_color').text.strip()
                estoque = livro.find("p", class_="instock availability").text.strip()
                link_relativo = livro.find("a")["href"]
                if "catalogue/" not in link_relativo:
                    link = f"https://books.toscrape.com/catalogue/{link_relativo}"
                else:
                    link = f"https://books.toscrape.com/{link_relativo}"

                livros_5_estrelas.append({
                    "Titulo": titulo,
                    "Preco": preco,
                    "Disponibilidade": estoque,
                    "Link": link
                })

        return livros_5_estrelas

    except Exception as e:
        print(f"Erro na página {pagina}: {e}")
        return []

# Páginas de 1 a 50
paginas = list(range(1, 51))

# Executar em paralelo
dados_livros = []
with ThreadPoolExecutor(max_workers=10) as executor:
    resultados = executor.map(processar_pagina, paginas)
    for r in resultados:
        dados_livros.extend(r)

# Salvar CSV
df = pd.DataFrame(dados_livros)
df.to_csv("livros_5_estrelas.csv", index=False)

print(f"\n{len(dados_livros)} livros com 5 estrelas salvos em 'livros_5_estrelas.csv'")
