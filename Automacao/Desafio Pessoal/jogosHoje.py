from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

# Setup Selenium
options = Options()
options.add_argument("--headless")
options.add_argument("--no-sandbox")
options.add_argument("--disable-dev-shm-usage")

driver = webdriver.Chrome(options=options)
driver.get("https://ge.globo.com/agenda/#/futebol")

# Aguarda carregar o conteúdo JS
time.sleep(5)

# Localiza todos os blocos de jogos
jogos = driver.find_elements(By.CSS_SELECTOR, "div.sc-dhKdcB.gQMKOH")

print(f"Quantidade de jogos encontrados: {len(jogos)}\n")

for i, jogo in enumerate(jogos, 1):
    print(f"Jogo {i}:")

    # Extrai os nomes dos times dentro do bloco atual
    times = jogo.find_elements(By.CSS_SELECTOR, "span.sc-eeDRCY.kXIsjf")
    if len(times) >= 2:
        time1 = times[0].text.strip()
        time2 = times[1].text.strip()
        print(f"{time1} x {time2}")
    else:
        print("Times não encontrados")

    # Exibe o restante do conteúdo do jogo
    print(jogo.text)
    print("____________________")

driver.quit()
