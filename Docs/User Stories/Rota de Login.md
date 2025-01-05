# User Story: Autenticação de Usuários na API

**Sendo um usuário ou administrador de um cinema com cadastro já realizado**
Gostaria de poder me autenticar na API de Cinema
Para acessar as funcionalidades correspondentes ao meu perfil (reservar tickets ou gerenciar filmes).
---

## Definition of Ready (DoR)

- Banco de dados e infraestrutura para desenvolvimento disponibilizados;  
- API de cadastro de usuários implementada;  
- Ambiente de testes configurado e validado;  
- Ferramentas para execução e monitoramento de testes definidas (k6 e postman);  
- Configuração de endpoints para monitoramento de métricas (tempo de resposta, taxa de sucesso, etc).

---

## Definition of Done (DoD)

- Autenticação com geração de token Bearer implementada;  
- Análise de testes cobrindo a rota de login;  
- Automação de testes baseada na análise realizada;  
- Testes de performance executados;  
- Critérios de performance (tempo de resposta, taxa de sucesso, uso de recursos) atendidos;  
- Evidências coletadas (gráficos de desempenho, logs de execução, relatórios);  
- Gargalos de performance identificados e documentados;  
- Relatório consolidado com análise das métricas;  
- Ambiente de testes configurado e validado;  
- Matriz de rastreabilidade atualizada com resultados dos testes de performance.

---

## Criterios de aceitação - Teste Funcionais

- Usuários não cadastrados não deverão conseguir autenticar.
- Usuários com senha inválida não deverão conseguir autenticar.
- No caso de não autenticação, deverá ser retornado um status code 401 (Unauthorized).
- Usuários existentes e com a senha correta deverão ser autenticados.
- A autenticação deverá gerar um token Bearer.
- O token deverá permitir:
    - Para administradores: acesso às rotas protegidas da API de filmes (cadastrar, editar, atualizar, excluir).
    - Para usuários comuns: acesso às rotas protegidas da API de tickets (criar, listar, buscar, atualizar, excluir reservas).

## Criterios de aceitação - Testes Não Funcionais

- A API deverá apresentar tempos de resposta abaixo de 2 segundos para 95% das requisições em cenários de carga normal.  
- A API deverá ser capaz de suportar até 1000 requisições simultâneas sem degradação significativa no desempenho.  
- O uso de recursos (CPU, memória) não deverá exceder 80% durante a execução de testes de carga, pico e estresse.  
- A API deverá manter uma taxa de sucesso de 99% em cenários de alto volume de requisições.  
- Os testes de estresse deverão identificar o ponto de falha da API, e a API deverá retornar um erro controlado (503 - Service Unavailable) quando o limite for atingido.  
- Os testes de durabilidade deverão garantir que a API mantenha a performance estável durante períodos de execução contínua de 24 horas ou mais.  
- As métricas de desempenho (tempo de resposta, taxa de sucesso, uso de recursos) deverão ser monitoradas e relatadas em gráficos claros e legíveis.  
- A API deverá ser testada em diferentes cenários de rede (latência alta, baixa banda) para garantir robustez e resiliência.  
- O ambiente de testes deverá ser configurado e validado para garantir que as métricas de desempenho sejam consistentes com os resultados esperados.
