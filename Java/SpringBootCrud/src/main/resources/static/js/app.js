/*
 * Front-end do CRUD. Conversa com a API REST em /produtos usando fetch.
 * Como a pagina e servida pelo proprio Spring Boot, a origem e a mesma e nao precisa de CORS.
 */

const API = '/produtos';

// Quantidade a partir da qual o produto ainda esta confortavel em estoque.
const ESTOQUE_BAIXO = 5;

const moeda = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' });

const el = {
    formulario: document.getElementById('formulario'),
    tituloFormulario: document.getElementById('tituloFormulario'),
    produtoId: document.getElementById('produtoId'),
    nome: document.getElementById('nome'),
    descricao: document.getElementById('descricao'),
    preco: document.getElementById('preco'),
    quantidade: document.getElementById('quantidade'),
    botaoSalvar: document.getElementById('botaoSalvar'),
    botaoCancelar: document.getElementById('botaoCancelar'),
    botaoAtualizar: document.getElementById('botaoAtualizar'),
    botaoTema: document.getElementById('botaoTema'),
    busca: document.getElementById('busca'),
    corpoTabela: document.getElementById('corpoTabela'),
    vazio: document.getElementById('vazio'),
    aviso: document.getElementById('aviso'),
    statusApi: document.getElementById('statusApi'),
    resumoTotal: document.getElementById('resumoTotal'),
    resumoValor: document.getElementById('resumoValor'),
    resumoBaixo: document.getElementById('resumoBaixo')
};

// Id do produto que acabou de ser salvo, so para destacar a linha na tabela.
let idEmDestaque = null;

/* ============================ Chamadas na API ============================ */

/** Faz a requisicao e ja trata o corpo de erro que o back-end devolve. */
async function chamarApi(url, opcoes = {}) {
    const resposta = await fetch(url, {
        headers: { 'Content-Type': 'application/json' },
        ...opcoes
    });

    if (!resposta.ok) {
        let mensagem = `Erro ${resposta.status}`;
        try {
            const erro = await resposta.json();
            if (erro && erro.mensagem) {
                mensagem = erro.mensagem;
            }
        } catch (ignorado) {
            // resposta sem corpo JSON: mantem a mensagem generica
        }
        throw new Error(mensagem);
    }

    return resposta.status === 204 ? null : resposta.json();
}

async function carregarProdutos() {
    const termo = el.busca.value.trim();
    const url = termo ? `${API}?nome=${encodeURIComponent(termo)}` : API;

    try {
        const produtos = await chamarApi(url);
        desenharTabela(produtos);
        atualizarResumo(produtos);
        marcarStatus(true);
    } catch (erro) {
        marcarStatus(false);
        mostrarAviso(`Não foi possível carregar a lista: ${erro.message}`, 'erro');
    }
}

async function salvar(evento) {
    evento.preventDefault();

    const id = el.produtoId.value;
    const produto = {
        nome: el.nome.value.trim(),
        descricao: el.descricao.value.trim(),
        preco: Number(el.preco.value),
        quantidade: Number(el.quantidade.value)
    };

    try {
        const salvo = await chamarApi(id ? `${API}/${id}` : API, {
            method: id ? 'PUT' : 'POST',
            body: JSON.stringify(produto)
        });

        idEmDestaque = salvo.id;
        mostrarAviso(id ? `"${salvo.nome}" foi atualizado.` : `"${salvo.nome}" foi cadastrado.`, 'ok');
        limparFormulario();
        await carregarProdutos();
    } catch (erro) {
        mostrarAviso(`Não foi possível salvar: ${erro.message}`, 'erro');
    }
}

async function excluir(produto) {
    if (!confirm(`Excluir "${produto.nome}"? Essa ação não tem volta.`)) {
        return;
    }

    try {
        await chamarApi(`${API}/${produto.id}`, { method: 'DELETE' });
        mostrarAviso(`"${produto.nome}" foi excluído.`, 'ok');

        // Se o produto excluido estava aberto no formulario, volta para o modo de cadastro.
        if (el.produtoId.value === String(produto.id)) {
            limparFormulario();
        }
        await carregarProdutos();
    } catch (erro) {
        mostrarAviso(`Não foi possível excluir: ${erro.message}`, 'erro');
    }
}

/* ============================ Tela ============================ */

function situacao(quantidade) {
    if (quantidade === 0) {
        return { texto: 'Em falta', classe: 'critico' };
    }
    if (quantidade <= ESTOQUE_BAIXO) {
        return { texto: 'Estoque baixo', classe: 'atencao' };
    }
    return { texto: 'Em estoque', classe: 'ok' };
}

function desenharTabela(produtos) {
    el.corpoTabela.replaceChildren();
    el.vazio.hidden = produtos.length > 0;

    produtos.forEach(produto => {
        const estado = situacao(produto.quantidade);
        const linha = document.createElement('tr');

        if (produto.id === idEmDestaque) {
            linha.className = 'linha-nova';
        }

        const id = document.createElement('td');
        id.className = `celula-id celula-id--${estado.classe}`;
        id.textContent = produto.id;

        const nome = document.createElement('td');
        const tituloProduto = document.createElement('div');
        tituloProduto.className = 'produto__nome';
        tituloProduto.textContent = produto.nome;
        nome.appendChild(tituloProduto);
        if (produto.descricao) {
            const descricao = document.createElement('div');
            descricao.className = 'produto__descricao';
            descricao.textContent = produto.descricao;
            nome.appendChild(descricao);
        }

        const preco = document.createElement('td');
        preco.className = 'num';
        preco.textContent = moeda.format(produto.preco);

        const quantidade = document.createElement('td');
        quantidade.className = 'num';
        quantidade.textContent = produto.quantidade;

        const situacaoCelula = document.createElement('td');
        const pilula = document.createElement('span');
        pilula.className = `pilula pilula--${estado.classe}`;
        pilula.textContent = estado.texto;
        situacaoCelula.appendChild(pilula);

        const total = document.createElement('td');
        total.className = 'num';
        total.textContent = moeda.format(produto.valorTotalEmEstoque);

        const acoes = document.createElement('td');
        const caixaAcoes = document.createElement('div');
        caixaAcoes.className = 'acoes';

        const botaoEditar = document.createElement('button');
        botaoEditar.type = 'button';
        botaoEditar.className = 'botao botao--tabela';
        botaoEditar.textContent = 'Editar';
        botaoEditar.addEventListener('click', () => abrirParaEdicao(produto));

        const botaoExcluir = document.createElement('button');
        botaoExcluir.type = 'button';
        botaoExcluir.className = 'botao botao--tabela botao--perigo';
        botaoExcluir.textContent = 'Excluir';
        botaoExcluir.addEventListener('click', () => excluir(produto));

        caixaAcoes.append(botaoEditar, botaoExcluir);
        acoes.appendChild(caixaAcoes);

        linha.append(id, nome, preco, quantidade, situacaoCelula, total, acoes);
        el.corpoTabela.appendChild(linha);
    });

    idEmDestaque = null;
}

function atualizarResumo(produtos) {
    const valorTotal = produtos.reduce((soma, p) => soma + p.valorTotalEmEstoque, 0);
    const precisamRepor = produtos.filter(p => p.quantidade <= ESTOQUE_BAIXO).length;

    el.resumoTotal.textContent = produtos.length;
    el.resumoValor.textContent = moeda.format(valorTotal);
    el.resumoBaixo.textContent = precisamRepor;
}

function abrirParaEdicao(produto) {
    el.produtoId.value = produto.id;
    el.nome.value = produto.nome ?? '';
    el.descricao.value = produto.descricao ?? '';
    el.preco.value = produto.preco ?? '';
    el.quantidade.value = produto.quantidade ?? '';

    el.tituloFormulario.textContent = `Editando #${produto.id}`;
    el.botaoSalvar.textContent = 'Salvar alterações';
    el.botaoCancelar.hidden = false;
    el.nome.focus();
}

function limparFormulario() {
    el.formulario.reset();
    el.produtoId.value = '';
    el.tituloFormulario.textContent = 'Novo produto';
    el.botaoSalvar.textContent = 'Cadastrar';
    el.botaoCancelar.hidden = true;
}

let temporizadorAviso;

function mostrarAviso(mensagem, tipo) {
    el.aviso.textContent = mensagem;
    el.aviso.className = `aviso aviso--${tipo}`;
    el.aviso.hidden = false;

    clearTimeout(temporizadorAviso);
    temporizadorAviso = setTimeout(() => {
        el.aviso.hidden = true;
    }, 4000);
}

function marcarStatus(conectado) {
    el.statusApi.textContent = conectado ? 'API conectada' : 'API fora do ar';
    el.statusApi.className = `pilula ${conectado ? 'pilula--ok' : 'pilula--critico'}`;
}

/* ============================ Tema ============================ */

function aplicarTema(tema) {
    document.documentElement.setAttribute('data-theme', tema);
    localStorage.setItem('tema', tema);
}

function alternarTema() {
    const atual = document.documentElement.getAttribute('data-theme');
    const preferidoEscuro = window.matchMedia('(prefers-color-scheme: dark)').matches;
    const efetivo = atual ?? (preferidoEscuro ? 'dark' : 'light');
    aplicarTema(efetivo === 'dark' ? 'light' : 'dark');
}

const temaSalvo = localStorage.getItem('tema');
if (temaSalvo) {
    aplicarTema(temaSalvo);
}

/* ============================ Eventos ============================ */

el.formulario.addEventListener('submit', salvar);
el.botaoCancelar.addEventListener('click', limparFormulario);
el.botaoAtualizar.addEventListener('click', carregarProdutos);
el.botaoTema.addEventListener('click', alternarTema);

// Busca enquanto digita, com uma pausa para nao disparar uma requisicao por tecla.
let temporizadorBusca;
el.busca.addEventListener('input', () => {
    clearTimeout(temporizadorBusca);
    temporizadorBusca = setTimeout(carregarProdutos, 300);
});

carregarProdutos();
