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
from datetime import datetime
import pandas as pd
import time

from concurrent.futures import ThreadPoolExecutor


# Configuração padrão do Chrome
options = Options()
options.add_argument("--headless")
options.add_argument("--disable-blink-features=AutomationControlled")
options.add_argument("--log-level=3")

# Inicializa a janela Chrome
driver = webdriver.Chrome(options=options, service=Service(ChromeDriverManager().install()))
driver.get("https://quotes.toscrape.com/login")
driver.set_window_size(1600, 1200)

def logar():
    wait = WebDriverWait(driver, 15)
    login = wait.until(EC.presence_of_element_located((By.ID, "username")))
    login.send_keys("admin")
    Local_senha = wait.until(EC.presence_of_element_located((By.ID, "password")))
    Local_senha.send_keys("admin")
    Local_senha.send_keys(Keys.ENTER)
    wait.until(EC.presence_of_element_located((By.CLASS_NAME, "quote")))

def pegar_dados():
    
    html = driver.page_source
    soup = BeautifulSoup(html, 'html.parser')

    quantidade = soup.find_all("div", class_="quote")
    autores = []

    for frases in enumerate(quantidade):
        base = 'https://quotes.toscrape.com'
        texto = frases.find("span", class_="text").text.strip()
        autor = frases.find("small", class_="author").text.strip()
        links_autores = base + frases.find("a").get('href')
        

        grupoDasTAGS = frases.find("div", class_="tags")
        tags = [tag.text.strip() for tag in grupoDasTAGS.find_all("a", class_="tag")]
        
        autores.append({
            "Texto": texto,
            "Nome": autor,
            "Link_autor": links_autores,
            "Tags": tags
        })
        
    return autores

        
        
def coletar_info_autor(autor_dict):
    try:

        driver = webdriver.Chrome(options=options, service=Service(ChromeDriverManager().install()))
        driver.get(autor_dict['Link_autor'])

        wait = WebDriverWait(driver, 15)
        data_nasc = wait.until(EC.presence_of_element_located((By.CLASS_NAME, "author-born-date"))).text
        local_nasc = wait.until(EC.presence_of_element_located((By.CLASS_NAME, "author-born-location"))).text
        descricao = wait.until(EC.presence_of_element_located((By.CLASS_NAME, "author-description"))).text
        data_formatada = datetime.strptime(data_nasc, "%B %d, %Y").strftime("%d/%m/%Y")

        driver.quit()

        return {
            "Nome": autor_dict["Nome"],
            "DataNascimento": data_formatada,
            "LocalNascimento": local_nasc,
            "Descricao": descricao
        }

    except Exception as erro:
        return {"Nome": autor_dict["Nome"], "Erro": str(erro)}

# EXECUÇÃO
logar()
paginas = 10
autores = []

for pagina in range(1, paginas + 1):
    driver.get(f"https://quotes.toscrape.com/page/{pagina}/")
    time.sleep(1)
    autores += pegar_dados()

driver.quit()

# Rodar tudo ao mesmo tempo com ThreadPoolExecutor
with ThreadPoolExecutor(max_workers=10) as executor:
    resultados = list(executor.map(coletar_info_autor, autores))


dados_csv = []
for frase in autores:
    nome_autor = frase['Nome']
    autor_info = next((a for a in resultados if a['Nome'] == nome_autor), {})

    dados_csv.append({
        "Citação": frase["Texto"],
        "Autor": nome_autor,
        "Tags": ", ".join(frase["Tags"]),
        "DataNascimento": autor_info.get("DataNascimento", "Erro"),
        "LocalNascimento": autor_info.get("LocalNascimento", "Erro"),
        "Descricao": autor_info.get("Descricao", "Erro")
    })
    
df = pd.DataFrame(dados_csv)
df.to_csv("quotes_full.csv", index=False, encoding="utf-8-sig")

print("\n✅ CSV salvo como 'quotes_full.csv'")




