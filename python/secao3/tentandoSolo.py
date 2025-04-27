
"""
Faça um jogo para o usuário adivinhar qual
a palavra secreta.
- Você vai propor uma palavra secreta
qualquer e vai dar a possibilidade para
o usuário digitar apenas uma letra.
- Quando o usuário digitar uma letra, você 
vai conferir se a letra digitada está
na palavra secreta.
    - Se a letra digitada estiver na
    palavra secreta; exiba a letra;
    - Se a letra digitada não estiver
    na palavra secreta; exiba *.
Faça a contagem de tentativas do seu
usuário.
"""

palavra_secreta = 'rodrigo' 
palavra_secreta = palavra_secreta.lower()

letraAcertada = ''
tentativa = 0
while True:
    letra = input("Digite uma letra: ")
    
    if len(letra) > 1:
        print("Digite apenas uma letra!")
    
    if letra in palavra_secreta:
        letraAcertada += letra
    else: 
        print("Não possui essa letra Letra!")
        
    palavra_formatada = ''
    for letra_secreta in palavra_secreta:
        if letra_secreta in letraAcertada:
            palavra_formatada += letra_secreta
        else:
            palavra_formatada += '*'
            
    print('Palavra formada:', palavra_formatada)
    
    tentativa += 1
    if palavra_formatada == palavra_secreta:
        print(f"Você acertou em {tentativa} tentativas!")
        break 