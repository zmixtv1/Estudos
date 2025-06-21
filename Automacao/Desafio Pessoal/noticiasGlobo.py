import requests
from bs4 import BeautifulSoup

url = 'https://g1.globo.com/'
resposta = requests.get(url, headers={"User-Agent": "Mozilla/5.0"})
soup = BeautifulSoup(resposta.text, "html.parser")

noticias = soup.find_all("h2")

for c, noticia in enumerate(noticias):
    try: 
        titulo_tag = noticia.find('p')
        titulo = titulo_tag.text.strip() if titulo_tag else "sem Título"
        
        print(f"{c}. {titulo}")
        
    except Exception as e:
        print(f"{c}. Erro ao extrair dados: {e}")