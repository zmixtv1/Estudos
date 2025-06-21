import requests
from bs4 import BeautifulSoup
cont = 0
while True:
    if cont == 0:
        url = "https://books.toscrape.com/"
    else:
        url = f'https://books.toscrape.com/catalogue/page-{cont+1}.html'
    resposta = requests.get(url, headers={"User-Agent": "Mozilla/5.0"})
    soup = BeautifulSoup(resposta.text, "html.parser")

    livros = soup.find_all("li", class_='col-xs-6 col-sm-4 col-md-3 col-lg-3')
    """
    - Titulo
    - Preco
    - Disponibilidade
    - Link completo da pagina do livro
    """
    print("_"*50)
    print(f"Pagina {cont+1}")
    print("_"*50)
    for c, livro in enumerate(livros, 1):
        estrela = livro.find("p", class_="star-rating")
        if estrela and 'Five' in estrela.get('class', []):
            titulo = livro.find("h3")
            preco = livro.find("p", class_='price_color')
            estoque = livro.find("p", class_="instock availability")
            link = livro.find("a")
            
            print(f"Titulo = {titulo.text.strip()}")
            print(f"preço = {preco.text.strip()}")
            print(f"estoque = {estoque.text.strip()}")
            print(f"Link = https://books.toscrape.com/{link.get("href")}\n\n")
    cont+=1

    if cont == 50:
        break
    
    