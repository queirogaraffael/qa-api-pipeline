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

## Criterios de aceitação - Teste Funcionais

- O administrador deverá ser capaz de criar um filme com os campos obrigatórios e título único;  
- Não deverá ser possível criar um filme com título já existente;  
- O sistema deverá atribuir um **ID único** para cada filme criado;  
- Ao listar filmes, o sistema deve retornar uma lista paginada com até 20 filmes por página;  
- O sistema deve retornar detalhes completos do filme ao consultar um filme pelo ID;  
- Se o filme não existir, deverá ser retornado **status 404 Not Found**;  
- O administrador poderá atualizar o filme com os dados fornecidos e o sistema deverá validar os dados enviados;  
- O sistema deverá retornar **status 200 OK** com os dados atualizados após a atualização do filme;  
- O administrador poderá excluir filmes, e o sistema deverá verificar se o filme existe antes de realizar a exclusão;  
- Após a exclusão, o sistema deverá retornar **status 204 No Content**;  
- Os testes executados deverão conter evidências.

---

## Criterios de aceitação - Testes Não Funcionais

- A API deverá processar pelo menos **100 solicitações por segundo** para criação de filmes, com tempo médio de resposta até **200ms**;  
- A API deverá retornar uma listagem de filmes com tempo médio de resposta até **100ms** para até **20 filmes por página**;  
- A API deverá retornar detalhes de um filme com tempo médio de resposta até **50ms**;  
- Para atualizações, a API deverá processar pelo menos **50 solicitações por segundo**, com tempo médio de resposta até **300ms**;  
- Para exclusões, a API deverá processar pelo menos **30 solicitações por segundo**, com tempo médio de resposta até **400ms**;  
- A API deverá manter uma taxa de sucesso de 99% em cenários de alto volume de requisições;  
- O uso de recursos (CPU, memória) não deverá exceder 80% durante a execução de testes de carga, pico e estresse;  
- Durante testes de estresse, a API deverá ser capaz de lidar com picos de requisições sem falhas críticas;  
- O desempenho da API deverá ser monitorado em diferentes cenários de rede (latência alta, baixa banda) e garantir resiliência;  
- Os testes de durabilidade deverão garantir que a API mantenha a performance estável durante períodos de execução contínua de 24 horas ou mais;  
- As métricas de desempenho (tempo de resposta, taxa de sucesso, uso de recursos) deverão ser monitoradas e relatadas em gráficos claros e legíveis;  
- O ambiente de testes deverá ser configurado e validado para garantir que as métricas de desempenho sejam consistentes com os resultados esperados.
