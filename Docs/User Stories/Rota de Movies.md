## User Story: Gerenciamento de Filmes na API de Cinema

**Sendo um administrador de um cinema**  
Gostaria de poder gerenciar os filmes na API de Cinema  
Para poder criar, listar, atualizar e excluir filmes disponíveis

---

## Definition of Ready (DoR)

- Banco de dados e infraestrutura para desenvolvimento disponibilizados;  
- Ambiente de testes configurado e validado;  
- Ferramentas para execução e monitoramento de testes definidas (postman e k6);  
- Configuração de endpoints para monitoramento de métricas (tempo de resposta e taxa de sucesso).

---

## Definition of Done (DoD)

- CRUD de gerenciamento de filmes implementado (CRIAR, ATUALIZAR, LISTAR E DELETAR);  
- Análise de testes cobrindo todos os verbos (CRUD);  
- Automação de testes baseada na análise realizada;  
- Testes de performance (carga, pico, estresse, volume, durabilidade) executados;  
- Critérios de performance (tempo de resposta, taxa de sucesso, uso de recursos) atendidos;  
- Evidências coletadas (gráficos de desempenho, logs de execução, relatórios);  
- Gargalos de performance identificados e documentados;  
- Relatório consolidado com análise das métricas;  
- Ambiente de testes configurado e validado;  
- Matriz de rastreabilidade atualizada com resultados dos testes de performance.

---

Requisitos Funcionais:

1. Criando um Novo Filme:
- O usuário administrador da API envia uma solicitação POST para o endpoint /movies com os detalhes do filme.
- O sistema valida os campos obrigatórios e a unicidade do título.
- Se as validações passarem, o sistema cria o filme e atribui um ID único.
- O sistema retorna uma resposta de sucesso com o status 201 Created, incluindo o ID do filme.
2. Obtendo a Lista de Filmes:
- O usuário envia uma solicitação GET para o endpoint /movies.
- O sistema retorna uma lista de todos os filmes cadastrados com detalhes.
3. Obtendo Detalhes de um Filme por ID:
- O usuário envia uma solicitação GET para o endpoint /movies/{id}, onde {id} é o ID do filme desejado.
- O sistema verifica a existência do filme e retorna seus detalhes.
- Se o filme não existir, o sistema retorna uma resposta de erro com o status 404 Not Found.
4. Atualizando os Detalhes de um Filme por ID:
- O usuário administrador da API envia uma solicitação PUT para o endpoint /movies/{id}, onde {id} é o ID do filme a ser atualizado.
- O sistema verifica a existência do filme, permite a atualização de campos específicos e valida os dados.
- Se todas as validações passarem, o sistema atualiza os detalhes do filme.
- O sistema retorna uma resposta de sucesso com o status 200 OK e os detalhes atualizados do filme.
5. Excluindo um Filme por ID:
- O usuário administrador da API envia uma solicitação DELETE para o endpoint /movies/{id}, onde {id} é o ID do filme a ser excluído.
- O sistema verifica a existência do filme e o remove permanentemente do banco de dados.
- O sistema retorna uma resposta de sucesso com o status 204 No Content.

Requisitos Não Funcionais de Performance:

- A API deve ser capaz de processar pelo menos 100 solicitações de criação de filmes por segundo.
- O tempo médio de resposta para a criação de um novo filme não deve exceder 200 milissegundos.
- A API deve ser capaz de responder a solicitações GET de listagem de filmes em menos de 100 milissegundos.
- A lista de filmes deve ser paginada, com no máximo 20 filmes por página.
- A API deve ser capaz de responder a solicitações GET de detalhes de um filme em menos de 50 milissegundos.
- A API deve ser capaz de processar pelo menos 50 solicitações de atualização de filmes por segundo.
- O tempo médio de resposta para a atualização dos detalhes de um filme não deve exceder 300 milissegundos.
- A API deve ser capaz de processar pelo menos 30 solicitações de exclusão de filmes por segundo.
- O tempo médio de resposta para a exclusão de um filme não deve exceder 400 milissegundos.