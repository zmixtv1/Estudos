import requests
from bs4 import BeautifulSoup

url = "https://lista.mercadolivre.com.br/arduino-lilypad"
resposta = requests.get(url, headers={"User-Agent": "Mozilla/5.0"})
soup = BeautifulSoup(resposta.text, "html.parser")

produtos = soup.find_all("li", class_="ui-search-layout__item")

for i, produto in enumerate(produtos[:5], 1):
    try:
        
        titulo_tag = produto.find("h3", class_="poly-component__title-wrapper")
        titulo = titulo_tag.text.strip() if titulo_tag else "Sem título"

        
        link_tag = produto.find("a", class_="poly-component__title")
        link = link_tag["href"] if link_tag else "Sem link"

        
        img_tag = produto.find("img", class_="poly-component__picture")
        imagem = img_tag["title"] if img_tag else "Sem imagem"

        
        print(f"{i}. {titulo}")
        print(f"Link: {link}")
        print(f"Titulo da Imagem: {imagem}")
        print("-" * 50)

    except Exception as e:
        print(f"{i}. Erro ao extrair dados: {e}")
