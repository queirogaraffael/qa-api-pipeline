## User Story

**Sendo um administrador de um cinema**  
Gostaria de poder me cadastrar na API de Cinema  
Para poder gerenciar os filmes e realizar vendas de ingressos

---

## Definition of Ready (DoR)

- Banco de dados e infraestrutura para desenvolvimento disponibilizados;  
- Ambiente de testes disponibilizado;  
- Ferramentas para execução e monitoramento de testes de performance definidas (postman e k6);  
- Configuração de endpoints para monitoramento de métricas (tempo de resposta, taxa de sucesso, etc).

---

## Definition of Done (DoD)

- CRUD de cadastro de usuários implementado (CRIAR, ATUALIZAR, LISTAR E DELETAR);  
- Análise de testes cobrindo todos os verbos (CRUD);  
- Automação de testes baseada na análise realizada;  
- Testes de performance (carga) executados;  
- Critérios de performance (tempo de resposta, taxa de sucesso, uso de recursos) atendidos;  
- Evidências coletadas (gráficos de desempenho, logs de execução, relatórios);  
- Gargalos de performance identificados e documentados;  
- Relatório consolidado com análise das métricas;  
- Ambiente de testes configurado e validado;  
- Matriz de rastreabilidade atualizada com resultados dos testes de performance.

---

## Criterios de aceitação - Teste Funcionais

- Os usuários deverão possuir os campos NOME, E-MAIL, PASSWORD e ADMINISTRADOR;  
- Não deverá ser possível fazer ações e chamadas para usuários inexistentes;  
- Não deve ser possível criar um usuário com e-mail já utilizado;  
- Caso não seja encontrado usuário com o ID informado no PUT, um novo usuário deverá ser criado;  
- Não deve ser possível cadastrar usuário com e-mail já utilizado utilizando PUT;  
- Os testes executados deverão conter evidências;  
- Os e-mails devem seguir um padrão válido de e-mail para o cadastro;  
- As senhas devem possuír no mínimo 5 caracteres e no máximo 10 caracteres;  

---

## Criterios de aceitação - Testes Não Funcionais

- A API deverá apresentar tempos de resposta abaixo de 2 segundos para 95% das requisições em cenários de carga normal.  
- A API deverá ser capaz de suportar até 1000 requisições simultâneas sem degradação significativa no desempenho.  
- O uso de recursos (CPU, memória) não deverá exceder 80% durante a execução de testes de carga e pico.  
- A API deverá manter uma taxa de sucesso de 99% em cenários de alto volume de requisições.  
- Durante testes de estresse, a API deverá ser capaz de lidar com picos de requisições sem falhas críticas.  
- O desempenho da API deverá ser monitorado em diferentes cenários de rede (latência alta e baixa banda) e garantir resiliência.  
- Os testes de durabilidade deverão garantir que a API mantenha a performance estável durante períodos de execução contínua de 24 horas ou mais.  
- As métricas de desempenho (tempo de resposta, taxa de sucesso, uso de recursos) deverão ser monitoradas e relatadas em gráficos claros e legíveis.  
- O ambiente de testes deverá ser configurado e validado para garantir que as métricas de desempenho sejam consistentes com os resultados esperados.
