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
import time


# Configuração do Chrome
options = Options()
options.add_argument("user-agent=Mozilla/5.0 ...")
options.add_argument("--disable-blink-features=AutomationControlled")
options.add_argument("--log-level=3") 

# Inicializa o driver
driver = webdriver.Chrome(options=options, service=Service(ChromeDriverManager().install()))
driver.get("https://quotes.toscrape.com/login")
driver.set_window_size(1600, 1200)

def logar():
    wait = WebDriverWait(driver, 15)
    login = wait.until(EC.presence_of_element_located((By.ID, "username")))
    login.send_keys("admin")
    Local_senha = wait.until(EC.presence_of_element_located((By.ID, "password")))
    senha = Local_senha.send_keys("admin")
    senha = Local_senha.send_keys(Keys.ENTER)
    wait.until(EC.presence_of_element_located((By.CLASS_NAME, "quote")))

def pegar_dados():
    
    html = driver.page_source
    soup = BeautifulSoup(html, 'html.parser')

    quantidade = soup.find_all("div", class_="quote")
    autores = []

    for c, frases in enumerate(quantidade):
        base = 'https://quotes.toscrape.com/'
        texto = frases.find("span", class_="text").text.strip()
        autor = frases.find("small", class_="author").text.strip()
        links_autores = base + frases.find("a").get('href')
        

        grupoDasTAGS = frases.find("div", class_="tags")
        tags = [tag.text.strip() for tag in grupoDasTAGS.find_all("a", class_="tag")]
        
        autores.append({
            "Nome": autor
        })
        
        print(f"{c}. {texto}\nAutor: {autor}\nTags: {', '.join(tags)}\nLink do autore: {links_autores}\n")
        
        
def pegar_dados_autores(autores):
    print("testes")
        
    
    
logar()
pegar_dados()



