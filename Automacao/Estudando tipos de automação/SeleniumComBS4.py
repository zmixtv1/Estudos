from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from selenium.common.exceptions import NoSuchElementException
from bs4 import BeautifulSoup
import time

options = Options()
options.add_argument("user-agent=Mozilla/5.0 ...")  # simula navegador real
options.add_argument("--disable-blink-features=AutomationControlled")

# Inicializa o navegador com Selenium
driver = webdriver.Chrome(options=options,service=Service(ChromeDriverManager().install()))
driver.get("https://www.google.com")

# Tenta detectar o iframe do reCAPTCHA
try:
    iframe = driver.find_element(By.XPATH, "//iframe[contains(@src, 'recaptcha')]")
    print("🔒 reCAPTCHA v2 detectado!")
    input("➡️ Resolva o CAPTCHA manualmente e pressione Enter para continuar...")
except NoSuchElementException:
    print("✅ Nenhum reCAPTCHA encontrado, prosseguindo...")

# Aceita cookies se aparecer
try:
    aceitar = driver.find_element(By.XPATH, "//button[contains(text(),'Aceitar')]")
    aceitar.click()
except:
    pass

# Busca por um termo
busca = driver.find_element(By.NAME, "q")
busca.send_keys("FURIA Esports")
busca.send_keys(Keys.RETURN)

# Espera carregar os resultados
time.sleep(2)

# Agora pegamos o conteúdo da página com Selenium
html = driver.page_source

# Usamos BeautifulSoup para raspar os dados
soup = BeautifulSoup(html, "html.parser")

# Busca os títulos dos resultados
resultados = soup.select("h3")

# Imprime os primeiros 5 títulos
for i, resultado in enumerate(resultados[:5], 1):
    print(f"{i}. {resultado.text.strip()}")

# Fecha o navegador
driver.quit()
