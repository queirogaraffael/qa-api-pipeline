# User Story: API de Reservas de Cinema

Sendo um cliente que deseja assistir a um filme no cinema,  
Gostaria de poder reservar ingressos pela API de Tickets  
Para garantir meu lugar na sessão desejada.

---

## Definição de Pronto (DoR)
- Banco de dados e infraestrutura para desenvolvimento disponibilizados.
- API de cadastro de usuários e Login implementadas.
- Ambiente de testes disponibilizado.
- Ferramentas para execução e monitoramento de testes definidas (postman e k6).
- Configuração de endpoints para monitoramento de métricas (tempo de resposta, taxa de sucesso, etc).

## Definição de Feito (DoD)
- Autenticação com token Bearer implementada para todos os endpoints.
- Análise de testes cobrindo a rota de tickets.
- Automação de testes baseada na análise realizada.
- Testes de performance executados.
- Critérios de performance (tempo de resposta, taxa de sucesso, uso de recursos) atendidos.
- Evidências coletadas (gráficos de desempenho, logs de execução, relatórios, etc).
- Gargalos de performance identificados e documentados.
- Relatório consolidado com análise das métricas.
- Ambiente de testes configurado e validado.
- Matriz de rastreabilidade atualizada com resultados dos testes.

---

## Criterios de aceitação - Teste Funcionais

- Deve ser possível criar reservas de ingressos com os campos: movieId, userId, seatNumber (0-99), price (0-60), showtime.
- O assento "seatNumber (0-99)" deve estar disponível para a reserva.
- Deve ser possível listar tickets com paginação opcional (page e limit).
- Deve ser possível buscar um ticket específico por ID.
- Deve ser possível atualizar tickets existentes.
- Deve ser possível excluir tickets existentes.
- Chamadas com dados inválidos devem ser rejeitadas com mensagens apropriadas.
- Tickets devem conter um ID único para identificação.

---

## Criterios de aceitação - Testes Não Funcionais

- A API deve ser capaz de processar 100 solicitações de reserva por segundo em condições de carga normal.
- O tempo médio de resposta para o processamento de reservas deve ser inferior a 300ms durante a maioria das requisições.
- A API deve garantir uma disponibilidade superior a 99% durante o período de testes.
- O consumo de memória e CPU deve ser monitorado e não deve ultrapassar 80% de uso durante os testes de carga.
- A API deve suportar até 500 usuários simultâneos sem degradação significativa na performance.
- Logs básicos devem ser gerados para monitorar erros e o desempenho da API durante os testes.
