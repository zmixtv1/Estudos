from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

driver.get("https://www.mercadolivre.com.br/")

wait = WebDriverWait(driver, 10)

try:
    aceitar = wait.until(EC.visibility_of_element_located((By.XPATH, "//button[contains(text(),'Aceitar')]")))
    aceitar.click()
except:
    pass

try:
    buscar_produto = wait.until(EC.visibility_of_element_located((By.XPATH, '//*[@id="cb1-edit"]')))
    buscar_produto.send_keys("monitor gamer")
    buscar_produto.send_keys(Keys.ENTER)
except:
    pass

produtos = driver.find_elements(By.CLASS_NAME, "ui-search-layout__item")[:5]

for i, produto in enumerate(produtos[:5], start=1):
    try:
        titulo = produto.find_element(By.CLASS_NAME, "poly-component__title").text
        preco = produto.find_element(By.CLASS_NAME, "andes-money-amount__fraction").text + "," + \
                produto.find_element(By.CLASS_NAME, "andes-money-amount__cents").text
        imagem = produto.find_element(By.TAG_NAME, "img").get_attribute("src")
        nota = produto.find_element(By.CLASS_NAME, "poly-reviews__rating").text
        avaliacoes = produto.find_element(By.CLASS_NAME, "poly-reviews__total").text.strip("()")
        print("_"*20)
        print(f"Título: {titulo}")
        print(f"Preço: R${preco}")
        print(f"Imagem: {imagem}")
        print(f"Avaliação: {nota} estrelas")
        print(f"Avaliações: {avaliacoes} pessoas")
        print("_"*20)

    except:
        print(f"{i}. Erro ao extrair dados: ")

driver.quit()
