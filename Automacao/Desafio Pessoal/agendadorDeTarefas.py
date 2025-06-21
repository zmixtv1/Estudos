import schedule
import time


def minha_tarefa():
    print("Executando tarefa...")
    
# Agendar para rodar a cada 10 segundos
schedule.every(1).seconds.do(minha_tarefa)

# Loop para manter o script rodando
while True:
    schedule.run_pending()
