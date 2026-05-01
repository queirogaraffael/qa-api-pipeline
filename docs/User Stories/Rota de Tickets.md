# User Story: API de Reservas de Cinema

Sendo um cliente autenticado que deseja assistir a um filme no cinema,
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

Requisitos Funcionais:

- O usuário envia uma solicitação POST para o endpoint /tickets com os seguintes detalhes do ingresso:
- ID do Filme (movieId) - Identifica o filme para o qual o ingresso está sendo reservado.
- ID do Usuário (userId) - Identifica o usuário que está fazendo a reserva.
- Número do Assento (seatNumber) - O número do assento que o usuário deseja reservar.
- Preço do Ingresso (price) - O preço do ingresso para o filme.
- Data de Apresentação (showtime) - A data e hora da apresentação do filme.
- O sistema valida se todos os campos obrigatórios estão preenchidos corretamente.
- O sistema verifica se o número do assento está dentro do intervalo de 0 a 99.
- O sistema verifica se o preço do ingresso está dentro do intervalo de 0 a 60.
- Se todas as validações passarem, o sistema cria uma reserva de ingresso com os detalhes fornecidos.
- O sistema atribui um ID único à reserva de ingresso.
- O sistema retorna uma resposta de sucesso com o status 201 Created, incluindo o ID da reserva de ingresso.

Requisitos Não Funcionais de Performance:

- A API deve ser capaz de processar pelo menos 50 solicitações de reserva de ingressos por segundo.
- O tempo médio de resposta para a reserva de um ingresso não deve exceder 300 milissegundos.

