# Plano de aprendizado — Controle de Gastos

> **Objetivo final:** um sistema para controlar minhas finanças (receitas, gastos fixos, gastos
> variáveis e investimentos), com API em Spring Boot rodando num Raspberry Pi 4 dentro de container,
> e front-end em Next/TypeScript na Vercel.

**Como usar este documento:** marque os `[ ]` conforme terminar e vá commitando. Cada fase entrega
algo que **funciona** — não passe para a próxima antes do "pronto quando" da atual.

---

## As regras do jogo

Quatro princípios que valem para todas as fases. Quando estiver perdido, volte aqui.

1. **Uma coisa nova por vez.** Cada fase adiciona no máximo uma tecnologia desconhecida. Se algo
   quebrar, você sabe onde procurar.
2. **Sempre ter algo funcionando.** Nunca fique uma semana com o projeto quebrado. Prefira dar um
   passo pequeno que roda a um passo grande que não.
3. **Fatia vertical, não camada.** Faça "cadastrar lançamento" inteiro (banco → API → tela) antes
   de começar "editar". Nunca faça todas as entidades, depois todos os services, depois todas as telas.
4. **Teste a API sozinha antes de fazer tela.** `curl`, Postman ou arquivo `.http` no IntelliJ. Isso
   evita você caçar no front um bug que está no Java.

**O que NÃO fazer agora:** gráfico bonito, tela de login completa, refatorar antes de funcionar,
"deixar preparado para o futuro". Tudo isso tem uma fase própria mais adiante.

---

## Fase 0 — Terminar o Java que falta

**Meta:** fechar os módulos do curso, mas construindo a versão console do próprio Controle de Gastos.
Assim cada tópico novo já nasce aplicado no domínio que você quer.

**Entrega:** um programa de terminal que registra lançamentos, salva num arquivo CSV e mostra o
resumo do mês. Sem Spring, sem banco, sem web.

### Módulos, na ordem, e o que cada um destrava

- [ ] **Herança e polimorfismo** — a base para entender a hierarquia de exceções da fase seguinte.
      *No projeto:* modele `Lancamento` com os campos `descricao`, `valor`, `data`, `tipo`.
- [ ] **Interfaces** — o mais importante de todos. `JpaRepository` **é** uma interface que você
      declara e o Spring implementa sozinho. Sem esse conceito, Spring vira mágica.
      *No projeto:* crie uma interface `RepositorioDeLancamentos` com `salvar`, `listar`, `remover`,
      e uma implementação que guarda numa lista em memória. Na Fase 1 o Spring vai fazer exatamente
      isso por você — e você vai entender o que ele está fazendo.
- [ ] **Tratamento de exceções** — é como a API vai devolver 404 e 400 em vez de estourar erro feio.
      *No projeto:* crie `ValorInvalidoException` e lance quando o valor for zero ou negativo.
- [ ] **Generics, Set e Map** — `List<Lancamento>`, `Optional<T>`, `Map<TipoLancamento, BigDecimal>`.
      *No projeto:* agrupe os lançamentos do mês por tipo num `Map`.
- [ ] **Programação funcional e lambdas** — a forma moderna de somar, filtrar e agrupar.
      *No projeto:* calcule o total de cada tipo com `stream().filter().map().reduce()`.
- [ ] **Trabalhando com arquivos** — o único tópico que não usa Spring, mas destrava algo que você
      vai querer muito: **importar o extrato CSV do banco** em vez de digitar tudo à mão.
      *No projeto:* salve e carregue os lançamentos de um `.csv`.

### Duas decisões técnicas para já acertar

- [ ] Usar **`BigDecimal`** para valores, nunca `double`. Com dinheiro, `0.1 + 0.2` dá
      `0.30000000000000004` e vira centavo errado no fechamento do mês.
- [ ] Usar **`enum TipoLancamento`** com `RECEITA`, `GASTO_FIXO`, `GASTO_VARIAVEL`, `INVESTIMENTO` —
      e **uma** classe `Lancamento`, não uma classe para cada tipo.

**Pronto quando:** você consegue rodar no terminal, cadastrar cinco lançamentos, fechar o programa,
abrir de novo e os dados continuarem lá.

---

## Fase 1 — A API rodando no seu PC

**Novo nesta fase:** Spring Boot. Só isso.
**Referência:** `Java/SpringBootCrud` — o CRUD de produtos faz exatamente isso, com outros campos.
Leia o código dele sempre que travar.

- [ ] Fazer os módulos de Spring Boot do curso
- [ ] Criar o projeto em `Java/ControleGastos/api` (pelo [start.spring.io](https://start.spring.io)
      ou copiando a estrutura do `SpringBootCrud`)
- [ ] Dependências: Web, Spring Data JPA, H2, **Validation**
- [ ] Entidade `Lancamento` com `BigDecimal valor`, `LocalDate data` e o enum `TipoLancamento`
- [ ] `LancamentoRepository extends JpaRepository<Lancamento, Long>`
- [ ] Service com o CRUD e a exceção de "não encontrado"
- [ ] Controller com os cinco endpoints (`GET` lista, `GET` por id, `POST`, `PUT`, `DELETE`)
- [ ] `@RestControllerAdvice` traduzindo as exceções em 404 e 400
- [ ] Validação com `@NotBlank`, `@NotNull`, `@Positive` e `@Valid`
- [ ] Endpoint de resumo do mês: total por tipo *(é aqui que os lambdas da Fase 0 voltam)*
- [ ] Testar tudo com `.http` ou Postman, **sem tela**
- [ ] Uma tela HTML simples servida pelo próprio Spring, igual à do `SpringBootCrud`
- [ ] Escrever pelo menos um teste automatizado do service

**Pronto quando:** você registrou seus gastos reais **de uma semana** usando o sistema. Se for chato
de usar, conserte agora — antes de complicar a infraestrutura.

---

## Fase 2 — Postgres em Docker (ainda no PC)

**Novo nesta fase:** Docker e Postgres.
**Por que agora:** o H2 apaga tudo quando você reinicia. Chega um momento em que isso irrita — e é
esse o momento certo de trocar.

- [ ] Instalar o Docker Desktop e entender os três comandos básicos: `up`, `down`, `logs`
- [ ] `docker-compose.yml` só com o Postgres, com volume nomeado para os dados persistirem
- [ ] Trocar a dependência do H2 pelo driver do Postgres
- [ ] Configurar a conexão por **variável de ambiente** (`SPRING_DATASOURCE_URL` etc.), não fixa no
      `application.properties`
- [ ] Trocar `ddl-auto=create-drop` por **Flyway**: o schema vira arquivo de migration versionado
- [ ] Migrar seus lançamentos da Fase 1 (ou recadastrar, se forem poucos)

**Pronto quando:** você desliga o PC, liga no dia seguinte, sobe o projeto e seus dados estão lá.

---

## Fase 3 — Rodando no Raspberry Pi

**Novo nesta fase:** empacotar em container e fazer deploy.

- [ ] Preparar o Pi: sistema atualizado, Docker instalado, acesso por SSH
- [ ] Se possível, **bootar de um SSD USB** em vez do cartão SD — cartão SD morre, e agora é o seu
      histórico financeiro que está nele
- [ ] `Dockerfile` da API (`FROM eclipse-temurin:21-jre`, copiando o `.jar`)
- [ ] Gerar o `.jar` no PC (`mvnw package`) e buildar a imagem **no Pi** — o `.jar` é portátil, mas a
      imagem não: PC é `amd64`, o Pi é `arm64`
- [ ] Juntar API e Postgres no mesmo `docker-compose.yml`
- [ ] `healthcheck` no Postgres + `depends_on: condition: service_healthy` — sem isso o Spring sobe
      antes do banco aceitar conexão e morre
- [ ] `restart: unless-stopped` nos dois, para voltarem sozinhos depois de queda de luz
- [ ] Limitar memória: `mem_limit: 1g` no serviço + `-XX:MaxRAMPercentage=75` na JVM
- [ ] **Backup automático:** `pg_dump` diário copiando para fora do Pi

**Pronto quando:** você tira o Pi da tomada, liga de novo, e o sistema volta sozinho — com os dados
intactos e um backup recente guardado em outro lugar.

---

## Fase 4 — O front-end em Next na Vercel

**Novo nesta fase:** Next, TypeScript, Vercel e Cloudflare Tunnel. É a fase mais longa — quatro
coisas novas. Faça devagar e nesta ordem.

### 4a. Expor a API com segurança

- [ ] Instalar o `cloudflared` no Pi e criar o túnel (sem abrir porta no roteador — não funcionaria
      mesmo, provedor com CGNAT não te dá IP público)
- [ ] Colocar o `cloudflared` no mesmo compose e **não publicar a porta 8080**: ele alcança a API
      pela rede interna do Docker
- [ ] Proteger a API com HTTP Basic e um usuário só, com a senha vindo de variável de ambiente

### 4b. O front

- [ ] Aprender o básico de TypeScript: `interface`, tipos em função, `Omit<>`
- [ ] `npx create-next-app` com TypeScript, em `Java/ControleGastos/web`
      *(se der erro de certificado, é a interceptação de HTTPS da sua rede — mesmo problema do
      Maven, resolve com `npm config set cafile`)*
- [ ] Criar as `interface` TypeScript espelhando o JSON da API
- [ ] Tela de lista lendo por **Server Component** — a chamada sai do servidor do Next, então não
      existe CORS e a senha da API nunca chega ao navegador
- [ ] Formulário de cadastro com **Server Action** + `revalidatePath`
- [ ] Editar e excluir
- [ ] Filtro por mês e o resumo no topo
- [ ] Deploy na Vercel, com as variáveis de ambiente configuradas lá

**A regra de ouro desta fase:** o navegador **nunca** fala direto com o Pi. Sempre
`navegador → Next → túnel → API`.

**Pronto quando:** você registra um gasto pelo celular, na rua, e ele aparece no Pi lá em casa.

---

## Fase 5 — Melhorias (cada uma quando doer)

Nada aqui é pré-requisito. Faça quando o problema aparecer de verdade:

- [ ] **Paginação** — quando a lista passar de umas 100 linhas e ficar lenta
- [ ] **DTOs separados da entidade** — quando um campo aparecer no JSON e você não quiser aquilo lá
- [ ] **Gastos fixos recorrentes** — quando cansar de digitar o aluguel todo mês
- [ ] **Importar CSV do banco** — quando cansar de digitar tudo *(aqui volta o módulo de arquivos)*
- [ ] **Gráficos** — evolução mês a mês. É a recompensa, guarde para o final
- [ ] **Login de verdade** — quando mais alguém for usar
- [ ] **Metas de orçamento** — "quero gastar no máximo X com mercado este mês"

---

## Quando travar

Antes de pedir ajuda ou procurar no Google, responda nesta ordem:

1. **Qual camada?** Chame a API direto com `curl`. Se a API responde certo, o problema é no front.
   Se não responde, esqueça o front.
2. **O que diz o log?** No container: `docker compose logs -f api`. A causa real costuma estar na
   primeira linha do erro, não na última.
3. **Funcionava antes?** `git diff` mostra o que você mexeu desde a última vez que rodava.
4. **É ambiente ou é código?** Erro de certificado, porta ocupada e falta de memória parecem bug do
   projeto, mas não são.

---

## Resumo em uma linha

| Fase | O que entrega | Novo aqui |
|------|---------------|-----------|
| 0 | App de console com CSV | Java (fim do curso) |
| 1 | API + tela simples no PC | Spring Boot |
| 2 | Dados que não somem | Docker, Postgres |
| 3 | Rodando no Pi 24h | Container, deploy |
| 4 | Acesso pelo celular | Next, TypeScript, Vercel, túnel |
| 5 | Refinamentos | conforme a necessidade |

O ponto mais importante do plano: **a partir da Fase 1 você já tem um sistema útil**. O Pi e a
Vercel são melhorias de conforto, não pré-requisitos para começar a usar.
