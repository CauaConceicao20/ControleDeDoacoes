<h1>Sobre o Desafio</h1>

<h2>Sistema de Suporte a Desabrigados em Enchentes</h2>

<p>Com o que está pronto do projeto, é possível fazer doações e enviar pedidos para distribuidoras de acordo com sua necessidade. O CRUD de todas as entidades está pronto também. O programa faz a adição dos itens/distribuidoras através de um arquivo CSV logo quando iniciado, criando sua base de dados completa com a relação das entidades já prontas.</p>

<h1>Relatório de Desenvolvimento do Projeto</h1>

<h2>Problemas Identificados</h2>
<ul>
    <li>
        <h3>Funcionalidade de Adicionar Distribuidora:</h3>
        <p><strong>Erro:</strong> Faltou a chamada do método para adicionar a distribuidora.</p>
        <p><strong>Causa:</strong> Provavelmente apagado inadvertidamente durante o desenvolvimento.</p>
    </li>
    <li>
        <h3>Funcionalidade de Remover Distribuidora:</h3>
        <p><strong>Erro:</strong> Faltou a chamada do método para realizar a remoção.</p>
        <p><strong>Causa:</strong> Semelhante ao problema anterior, a chamada do método foi omitida.</p>
    </li>
    <li>
        <h3>Switch Cases Incompletos:</h3>
        <p><strong>Erro:</strong> Ausência de <code>break</code> em algumas instruções <code>switch case</code>.</p>
        <p><strong>Linhas Atingidas:</strong> 276, 602 e 419 na <code>main</code>.</p>
        <p><strong>Causa:</strong> Provavelmente esquecidos durante a codificação.</p>
    </li>
    <li>
        <h3>Mensagens de Teste:</h3>
        <p><strong>Erro:</strong> Mensagens adicionais usadas para testes não foram removidas.</p>
        <p><strong>Impacto:</strong> Exibição de mensagens duplicadas ao ocorrer uma exceção.</p>
    </li>
    <li>
        <h3>Validações Faltantes:</h3>
        <p><strong>Erro:</strong> Ausência de verificações importantes, como:</p>
        <ul>
            <li>Verificação na remoção de um pedido para confirmar se foi removido ou não.</li>
            <li>Verificação da existência de um pedido com o ID inserido na alteração do pedido.</li>
            <li>Validação da escolha da descrição na doação de itens via terminal.</li>
        </ul>
        <p><strong>Causa:</strong> Falta de atenção aos detalhes durante a implementação das validações.</p>
    </li>
    <li>
        <h3>Erro na Condição da Linha 170:</h3>
        <p><strong>Erro:</strong> Chamada da variável errada na condição, usando <code>escolhaUnidadeDeMedida</code> em vez de <code>escolhaDescricao</code>.</p>
        <p><strong>Impacto:</strong> Problema na condição lógica do código.</p>
    </li>
</ul>

<h2>Códigos de Testes Esquecidos</h2>
<ul>
    <li>
        <h3>Funcionalidade de Aceitar Pedidos:</h3>
        <p><strong>Erro:</strong> Código de teste deixado na <code>main</code> por volta da linha 364.</p>
        <p><strong>Impacto:</strong> Interferência no funcionamento correto da funcionalidade.</p>
    </li>
    <li>
        <h3>Limitação de 200 Itens por Abrigo:</h3>
        <p><strong>Erro:</strong> Testes não concluídos deixaram o código em estado inconsistente.</p>
        <p><strong>Impacto:</strong> Problema na ação de negar um pedido quando a distribuidora aceita um pedido e depois tenta negar outro.</p>
    </li>
    <li>
        <h3>Validação da Quantidade de Itens por Distribuidora:</h3>
        <p><strong>Erro:</strong> Operador <code>&lt;</code> usado em vez de <code>&lt;=</code> na condição do método <code>adiciona</code> no <code>ItemService</code>.</p>
        <p><strong>Impacto:</strong> Mal funcionamento na validação da quantidade de itens.</p>
    </li>
    <li>
        <h3>Else Extra:</h3>
        <p><strong>Erro:</strong> Um <code>else</code> adicional foi deixado no código para testes.</p>
        <p><strong>Impacto:</strong> Interferência no fluxo lógico do código.</p>
    </li>
</ul>

<p><strong>Apontei algumas linhas, mas podem não estar exatas porque localmente adicionei alguns comentários para a apresentação. Isso pode causar algumas divergências nas linhas.</strong></p>

<h2>Nota para Instrutores</h2>
<p>Ficaram faltando algumas features das pedidas, entre elas a transferência entre distribuidoras e a adição de um limite de 200 para cada item nos abrigos. O código acabou ficando bem bagunçado na <code>main</code> e pecando em várias coisas, principalmente na organização e validações de entradas. Acabei não gerenciando meu tempo bem e, no final, tive que dar uma apressada. Fora isso, gostei muito do desafio, sendo este meu primeiro projeto que usa o conceito de relação entre entidades tão fortemente. Agradeço pela oportunidade de crescimento.</p>
