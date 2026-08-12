# SpringBootCrud

Projeto de exemplo com um **CRUD simples de Produto** usando Spring Boot, Spring Data JPA e banco H2 em memoria.

- Spring Boot 4.1.0 / Java 21
- Banco H2 em memoria (os dados sao recriados a cada execucao)
- API REST em `/produtos`
- Tela web em <http://localhost:8080> (HTML + CSS + JavaScript puro, sem framework)

## Como rodar

Na pasta do projeto. **Escolha a linha do seu terminal e cole so ela** (nao cole o bloco inteiro):

PowerShell (o `.\` na frente e obrigatorio — o PowerShell nao roda comando da pasta atual sem ele):

```powershell
.\mvnw.cmd spring-boot:run
```

Git Bash:

```bash
./mvnw spring-boot:run
```

Depois abra <http://localhost:8080> no navegador. Para parar, use `Ctrl+C` no terminal.

## A tela

A pagina e servida pelo proprio Spring Boot (de `src/main/resources/static`), entao ela roda na
mesma origem da API e nao precisa de configuracao de CORS. Da para fazer tudo por ela:

- cadastrar um produto pelo formulario da esquerda;
- clicar em **Editar** numa linha para carregar o produto no formulario e salvar as alteracoes;
- **Excluir** com confirmacao;
- buscar por nome (a lista filtra sozinha enquanto voce digita);
- acompanhar o resumo: quantos produtos existem, quanto vale o estoque e quantos precisam de
  reposicao.

A situacao de cada produto sai da quantidade: `0` vira **Em falta**, ate `5` vira **Estoque baixo**
e o resto **Em estoque**. Esse limite fica na constante `ESTOQUE_BAIXO`, no comeco do `app.js` —
e um bom primeiro lugar para mexer e ver a tela mudar.

O botao no canto superior direito alterna entre tema claro e escuro.

Rodar os testes:

```powershell
.\mvnw.cmd test
```

Tambem da para abrir a pasta direto no IntelliJ (ele reconhece o `pom.xml`) e rodar a classe
`CrudApplication`.

### Problemas comuns

**`PKIX path building failed` ao baixar dependencias**

A rede desta maquina usa um certificado que o Java nao conhece. Rode mandando o Java usar os
certificados do Windows:

```powershell
$env:MAVEN_OPTS = "-Djavax.net.ssl.trustStoreType=WINDOWS-ROOT"
.\mvnw.cmd spring-boot:run
```

No IntelliJ, a mesma opcao vai em *Settings > Build Tools > Maven > Runner > VM Options*.

**`Port 8080 was already in use`**

Ja tem alguma aplicacao usando a porta (provavelmente uma execucao anterior deste projeto que nao
foi encerrada). Para achar e encerrar, no PowerShell:

```powershell
Get-NetTCPConnection -LocalPort 8080 -State Listen | Select-Object OwningProcess
Stop-Process -Id <numero_do_processo> -Force
```

Ou, se preferir so subir em outra porta:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

## Endpoints

| Metodo | URL                   | O que faz                          | Status |
|--------|-----------------------|------------------------------------|--------|
| GET    | `/produtos`           | Lista todos                        | 200    |
| GET    | `/produtos?nome=mo`   | Filtra pelo nome (contem)          | 200    |
| GET    | `/produtos/{id}`      | Busca um produto                   | 200 / 404 |
| POST   | `/produtos`           | Cria um produto                    | 201    |
| PUT    | `/produtos/{id}`      | Atualiza um produto                | 200 / 404 |
| DELETE | `/produtos/{id}`      | Remove um produto                  | 204 / 404 |

### Exemplos com curl

```bash
curl http://localhost:8080/produtos

curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Monitor","descricao":"Monitor 27","preco":1800.0,"quantidade":5}'

curl -X PUT http://localhost:8080/produtos/4 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Monitor 4K","descricao":"Monitor 27 4K","preco":2100.0,"quantidade":3}'

curl -X DELETE http://localhost:8080/produtos/4
```

Quando o id nao existe, a resposta e um JSON de erro:

```json
{
  "timestamp": "2026-08-12T02:43:56.045042100Z",
  "status": 404,
  "erro": "Recurso nao encontrado",
  "mensagem": "Produto nao encontrado. Id: 999",
  "caminho": "/produtos/999"
}
```

## Banco de dados

O H2 roda em memoria. Para ver as tabelas pelo navegador, acesse
<http://localhost:8080/h2-console> e use:

- **JDBC URL:** `jdbc:h2:mem:estudosdb`
- **User:** `sa`
- **Password:** (vazio)

Os tres produtos iniciais vem do arquivo `src/main/resources/data.sql`.

## Estrutura

```
src/main/java/com/estudos/crud
├── CrudApplication.java              -> classe main, sobe a aplicacao
├── entities/Produto.java             -> entidade JPA (vira a tabela tb_produto)
├── repositories/ProdutoRepository.java -> acesso ao banco (JpaRepository)
├── services/ProdutoService.java      -> regras de negocio do CRUD
├── services/exceptions/              -> excecao de recurso nao encontrado
└── controllers/ProdutoController.java -> endpoints REST
    └── handlers/                     -> traduz a excecao em resposta 404

src/main/resources
├── application.properties            -> configuracao (banco, H2 console, JPA)
├── data.sql                          -> produtos iniciais
└── static                            -> a tela, servida em /
    ├── index.html
    ├── css/estilo.css
    └── js/app.js                     -> chama a API com fetch
```

O fluxo de uma requisicao e sempre o mesmo:
**Tela** (fetch) -> **Controller** (recebe o HTTP) -> **Service** (regra de negocio) ->
**Repository** (banco).
