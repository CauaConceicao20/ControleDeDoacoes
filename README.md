<h1>Sobre o Desafio</h1>

<H2>Sistema de Suporte a Desabrigado em Enchentes</H2>

<p>Com o que esta pronto do projeto é possivel fazer doações e enviar pedidos para distribuidoras de acordo com sua necessidade, o crud de todos entidades está pronto tambem.
O programa faz a adição dos items/ distribuidoras atravess de um arquivo csv logo quando iniciado criando sua base de dados completa com a relação das entidades já prontas.
<h1>Relatório de Desenvolvimento do Projeto</h1></p>
    
    <h2>Relatorio sobre o codigo</h2>
    <p>Durante o desenvolvimento deste projeto, enfrentei desafios relacionados à administração do tempo e alguns imprevistos. Como resultado, códigos desnecessários foram deixados na aplicação, e houve a necessidade de refatorar algumas partes. A seguir, apresento um relatório detalhado sobre as falhas encontradas</p>
    
    <h2>Problemas Identificados</h2>
    <ul>
        <li>
            <h3>Funcionalidade de Adicionar Distribuidora:</h3>
            <ul>
                <li><strong>Erro:</strong> Faltou a chamada do método para adicionar a distribuidora.</li>
                <li><strong>Causa:</strong> Provavelmente apagado inadvertidamente durante o desenvolvimento.</li>
            </ul>
        </li>
        <li>
            <h3>Funcionalidade de Remover Distribuidora:</h3>
            <ul>
                <li><strong>Erro:</strong> Faltou a chamada do método para realizar a remoção.</li>
                <li><strong>Causa:</strong> Semelhante ao problema anterior, a chamada do método foi omitida.</li>
            </ul>
        </li>
        <li>
            <h3>Switch Cases Incompletos:</h3>
            <ul>
                <li><strong>Erro:</strong> Ausência de <code>break</code> em algumas instruções <code>switch case</code>.</li>
                <li><strong>Linhas Atingidas:</strong> 276, 602 e 419 na <code>main</code>.</li>
                <li><strong>Causa:</strong> Provavelmente esquecidos durante a codificação.</li>
            </ul>
        </li>
        <li>
            <h3>Mensagens de Teste:</h3>
            <ul>
                <li><strong>Erro:</strong> Mensagens adicionais usadas para testes não foram removidas.</li>
                <li><strong>Impacto:</strong> Exibição de mensagens duplicadas ao ocorrer uma exceção.</li>
            </ul>
        </li>
        <li>
            <h3>Validações Faltantes:</h3>
            <ul>
                <li><strong>Erro:</strong> Ausência de verificações importantes, como:</li>
                <ul>
                    <li>Verificação na remoção de um pedido para confirmar se foi removido ou não.</li>
                    <li>Verificação da existência de um pedido com o ID inserido na alteração do pedido.</li>
                    <li>Validação da escolha da descrição na doação de itens via terminal.</li>
                </ul>
                <li><strong>Causa:</strong> Falta de atenção aos detalhes durante a implementação das validações.</li>
            </ul>
        </li>
        <li>
            <h3>Erro na Condição da Linha 170:</h3>
            <ul>
                <li><strong>Erro:</strong> Chamada da variável errada na condição, usando <code>escolhaUnidadeDeMedida</code> em vez de <code>escolhaDescricao</code>.</li>
                <li><strong>Impacto:</strong> Problema na condição lógica do código.</li>
            </ul>
        </li>
    </ul>
    
    <h2>Códigos de Testes Esquecidos</h2>
    <ul>
        <li>
            <h3>Funcionalidade de Aceitar Pedidos:</h3>
            <ul>
                <li><strong>Erro:</strong> Código de teste deixado na <code>main</code> por volta da linha 364.</li>
                <li><strong>Impacto:</strong> Interferência no funcionamento correto da funcionalidade.</li>
            </ul>
        </li>
        <li>
            <h3>Limitação de 200 Itens por Abrigo:</h3>
            <ul>
                <li><strong>Erro:</strong> Testes não concluídos deixaram o código em estado inconsistente.</li>
                <li><strong>Impacto:</strong> Problema na ação de negar um pedido quando a distribuidora aceita um pedido e depois tenta negar outro.</li>
            </ul>
        </li>
        <li>
            <h3>Validação da Quantidade de Itens por Distribuidora:</h3>
            <ul>
                <li><strong>Erro:</strong> Operador <code>&lt;</code> usado em vez de <code>&lt;=</code> na condição do método <code>adiciona</code> no <code>ItemService</code>.</li>
                <li><strong>Impacto:</strong> Mal funcionamento na validação da quantidade de itens.</li>
            </ul>
        </li>
        <li>
            <h3>Else Extra:</h3>
            <ul>
                <li><strong>Erro:</strong> Um <code>else</code> adicional foi deixado no código para testes.</li>
                <li><strong>Impacto:</strong> Interferência no fluxo lógico do código.</li>
            </ul>
        </li>
    </ul>
    
    <h2>Conclusão</h2>
    <p>Esses foram os principais problemas identificados durante a revisão do código. É crucial ressaltar que o código necessita de uma refatoração, que não foi possível realizar devido à limitação de tempo. A correção dessas falhas e a refatoração do código são essenciais para garantir o bom funcionamento e a manutenção do projeto.</p>

<p><strong>Apontei algumas linhas, mas podem não estar exatas porque localmente adicionei alguns comentários para a apresentação. Isso pode causar algumas divergências nas linhas.</strong></p>

<h2>Nota pra instrutores</h2>
ficou faltando algumas features das pedidas entre elas foi a transferencia entre distribuidoras e a adição de um limite 200 para cada item nos abrigos
O codigo acabou ficando bem bagunçado na main e pecando em varias coisas principalmente na organização e validações de entradas acabei não gerenciando meu tempo bem e no final tive que da uma rushada
fora isso gostei muito do desafio sendo ele meu primeiro projeto que uso o conceito de relação entre entidades tão fortemente, agradeço pela oportunidade de crescimento. 
