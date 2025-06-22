import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import pandas as pd

# Configuração do Chrome
options = Options()
options.add_argument("user-agent=Mozilla/5.0 ...")
options.add_argument("--disable-blink-features=AutomationControlled")

# Inicializa o driver
driver = webdriver.Chrome(options=options, service=Service(ChromeDriverManager().install()))
driver.get("https://webscraper.io/test-sites/e-commerce/allinone/computers/tablets")

# Aceita cookies se aparecer
wait = WebDriverWait(driver, 15)
try:
    aceitar =  wait.until(EC.presence_of_element_located((By.CLASS_NAME, "acceptCookies")))
    aceitar.click()
except:
    pass

html = driver.page_source

# Usamos BeautifulSoup para raspar os dados
soup = BeautifulSoup(html, "html.parser")

produtos = soup.find_all("div", class_="thumbnail")

"""
- Nome do produto
- Preço
- Descrição
- Avaliação (quantidade de estrelas)
- Link para a página do produto
"""

salvar_produto = []

for c, produto in enumerate(produtos):
    base = 'https://webscraper.io'
    
    nome_ = produto.find('a',class_="title")
    nome = nome_['title']
    
    local_preco = produto.find('h4', class_='price float-end card-title pull-right')
    preco = local_preco.find('span', attrs={"itemprop": True})
    
    descricao = produto.find("p", class_="description card-text")

    rating_tag = produto.find('p', attrs={'data-rating': True})
    rating = rating_tag['data-rating']
    
    pegar_link = produto.find("a", class_='title')
    link_resumido = pegar_link.get('href')
    link = base + link_resumido
    
    print("___"*20)
    print(f"Nome = {nome}")
    print(f"Preço = {preco.text}")
    print(f"Descrição = {descricao.text}")
    print(f"Rating = {rating}")
    print(f"link = {link}")
    
    salvar_produto.append({
        "Produto": nome,
        "Preço": preco.text,
        "Descrição": descricao.text,
        "Avaliação": rating,
        "Link": link,
    })

df = pd.DataFrame(salvar_produto)
df.to_csv('tables_wsio.csv', index=False)

# Fecha o navegador
driver.quit()
