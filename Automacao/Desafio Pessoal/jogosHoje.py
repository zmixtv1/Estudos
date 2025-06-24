from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import smtplib
from email.message import EmailMessage
import time

def coletar_jogos_filtro_ol():
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--window-size=1920,1080")
    driver = webdriver.Chrome(options=options)

    driver.get("https://ge.globo.com/agenda/#/futebol")
    time.sleep(5)

    # Clicar até carregar tudo
    while True:
        try:
            botao = driver.find_element(By.CSS_SELECTOR, "button[data-direction='right']")
            botao.click()
            time.sleep(1)
        except:
            break

    html = driver.page_source
    driver.quit()

    soup = BeautifulSoup(html, 'html.parser')

    # FILTRA SOMENTE O QUE ESTÁ DENTRO DA <ol>
    lista_jogos = soup.select_one("ol.sc-hIqOWS")
    if not lista_jogos:
        print("❌ Lista <ol> não encontrada.")
        return []

    jogos_html = lista_jogos.find_all("div", class_="sc-dhKdcB")
    jogos = []

    for jogo in jogos_html:
        try:
            campeonato = jogo.find("span", class_="sc-jXbUNg").text.strip()
            spans = jogo.find_all("span", class_="sc-jXbUNg")
            horario = spans[1].text.strip() + " " + spans[2].text.strip() if len(spans) > 2 else "-"
            rodada = spans[-2].text.strip() if len(spans) > 2 else "-"
            genero = spans[-1].text.strip() if len(spans) > 2 else "-"

            status_tag = jogo.find("span", attrs={"data-status-id": True})
            status = status_tag.text.strip() if status_tag else ""

            times = jogo.find_all("span", class_="sc-eeDRCY")
            time1 = times[0].text.strip() if len(times) > 0 else "-"
            time2 = times[1].text.strip() if len(times) > 1 else "-"

            placares = jogo.find_all("div", class_="sc-jsJBEP")
            score1 = placares[0].text.strip() if len(placares) > 0 else "-"
            score2 = placares[1].text.strip() if len(placares) > 1 else "-"

            jogos.append({
                "Campeonato": campeonato,
                "Horário": horario,
                "Status": status,
                "Time 1": time1,
                "Placar 1": score1,
                "Time 2": time2,
                "Placar 2": score2,
                "Rodada": rodada,
                "Gênero": genero
            })

        except Exception as e:
            print("Erro ao processar jogo:", e)

    return jogos

def gerar_html_inline(jogos):
    html = """
    <html>
    <body style="font-family: Arial, sans-serif; background-color: #b9b9b9; padding: 20px;">
        <h2 style="text-align: center;">📅 Jogos Selecionados</h2>
        <div style="display: flex;justify-content: center; flex-wrap: wrap;justify-content: center;">
    """
    for jogo in jogos:
        html += f"""
        <div style="background-color: white; border-radius: 10px; box-shadow: 2px 4px 5px rgba(0,0,0,0.7); width: 500px; margin: 10px; padding: 25px; text-align: center;">
            <h3 style="margin: 0 0 5px 0;">{jogo['Campeonato']}</h3>
            <br>
            <p style="margin: 4px 0;"><strong>Horário:</strong> {jogo['Horário']}</p>
            <p style="margin: 4px 0;"><strong>Status:</strong> {jogo['Status']}</p>
            <div style="margin: 10px 0;">
                <div style="font-size: 20px; color: rgb(190, 55, 55);"><strong>{jogo['Time 1']}</strong> <span style="margin: 0 8px;">{jogo['Placar 1']} x {jogo['Placar 2']}</span> <strong>{jogo['Time 2']}</strong></div>
            </div>
            <p style="margin: 4px 0;"><em>{jogo['Rodada']} - {jogo['Gênero']}</em></p>
        </div>
        """
    html += """
        </div>
    </body>
    </html>
    """
    return html
def enviar_email(destinatario, assunto, conteudo_html):
    remetente = "rodrigoalaor.lm@gmail.com"
    senha = "ixev ermx eegi fand"  # Para Gmail, use senha de app

    msg = EmailMessage()
    msg["Subject"] = assunto
    msg["From"] = remetente
    msg["To"] = destinatario
    msg.set_content("Seu cliente de e-mail não suporta HTML.")
    msg.add_alternative(conteudo_html, subtype='html')

    try:
        with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
            smtp.login(remetente, senha)
            smtp.send_message(msg)
            print("📧 E-mail enviado com sucesso!")
    except Exception as e:
        print("❌ Erro ao enviar e-mail:", e)

# Execução
if __name__ == "__main__":
    jogos = coletar_jogos_filtro_ol()
    html = gerar_html_inline(jogos)

    with open("preview_jogos_email.html", "w", encoding="utf-8") as f:
        f.write(html)
    print("✅ HTML salvo como preview_jogos_email.html")

    if jogos:
        enviar_email("rodrigoalaor.lm@gmail.com", "Agenda de Jogos ⚽", html)