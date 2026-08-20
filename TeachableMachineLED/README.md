# LED controlado por Teachable Machine

Um modelo treinado no [Teachable Machine](https://teachablemachine.withgoogle.com/) acende e apaga um LED virtual.
Duas versoes da mesma ideia:

| Arquivo | Formato do modelo | Onde roda |
|---|---|---|
| `LED_TeachableMachine_Colab.ipynb` | Keras `.h5` | Google Colab (Python) - **usar na apresentacao** |
| `index.html` | TensorFlow.js | Navegador (servidor local) |

---

## Notebook da apresentacao

<https://colab.research.google.com/drive/1F_z4W5o1wA8a3vgxpawteX4MQVAq9Sh7>

### Como levar o codigo para la

- **Mantendo esse link**: abra o notebook e cole as celulas deste `.ipynb` na ordem.
- **Mais rapido**: no Colab, `Arquivo -> Fazer upload de notebook` e envie o `.ipynb`.
  Atencao: isso cria um notebook novo, com **outro link**.

### Antes de enviar para o professor

1. **Compartilhar**: botao `Compartilhar` -> `Qualquer pessoa com o link` -> `Leitor`.
   Sem isso o professor recebe "voce precisa de acesso".
2. **Modelo acessivel por URL**: o notebook nao carrega o modelo junto. Na celula 2,
   aponte `URL_MODELO` para o `converted_keras.zip`. Funcionam os dois formatos:
   - GitHub: `https://raw.githubusercontent.com/zmixtv1/Estudos/main/TeachableMachineLED/converted_keras.zip`
   - Google Drive (compartilhado como "qualquer pessoa com o link"): cole o link normal,
     o notebook converte para download direto sozinho.
3. Rode o notebook do inicio ao fim uma vez, com a saida limpa, para conferir.

Se o download falhar, a celula 2 cai no upload manual - o notebook funciona nos dois casos.

---

## Passo a passo (voce)

1. No Teachable Machine: `Export Model` -> aba **Tensorflow** -> **Keras** -> `Download my model`.
2. Publique o `converted_keras.zip` (nesta pasta do repo, ou no Drive) e ajuste `URL_MODELO`.
3. Rode as celulas na ordem.
4. Ajuste na celula 4 qual classe liga o LED, o limiar e o modo.
5. Use a **demo por foto** (mais estavel) ou a **demo ao vivo** (webcam continua).

A celula de carregamento tenta 3 estrategias diferentes, porque o `.h5` do Teachable Machine
foi salvo com Keras 2 e o Colab hoje vem com Keras 3. O notebook avisa qual delas funcionou.

---

## Versao web (TensorFlow.js)

1. No Teachable Machine: `Export Model` -> aba **Tensorflow.js** -> `Upload my model` e copie a URL.
2. Suba um servidor local nesta pasta (camera/microfone exigem contexto seguro):

   ```
   python -m http.server 8000
   ```

3. Abra <http://localhost:8000>, cole a URL e clique em Iniciar.

Suporta modelos de imagem, pose e audio. Requer internet: as bibliotecas vem do CDN.

---

## Como o LED reage

**Pagina web (`index.html`) - modo comando.** Feito para modelos com gestos separados:

- gesto de **Ligar** -> acende
- gesto de **Desligar** -> apaga
- qualquer outra classe (ex.: "Nada") -> nao mexe no LED

A pagina tenta adivinhar sozinha quais classes sao Ligar/Desligar pelo nome, e os dois seletores
deixam trocar na mao. Um comando so vale depois de aparecer em varios quadros seguidos
(controle **Estabilidade**), para o LED nao piscar sozinho.

**Notebook do Colab.** Ainda usa os modos antigos, escolhidos na celula 4:

- **hold**: o LED fica aceso so enquanto a classe esta acima do limiar.
- **toggle**: cada nova deteccao inverte o estado (liga/desliga).
