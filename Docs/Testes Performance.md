
# 1. Testes de Performance

Os testes de performance avaliam a velocidade, estabilidade, capacidade e tempo de resposta de um sistema sob diferentes condições de uso, como carga esperada, picos de usuários e uso prolongado. Esses testes ajudam a identificar gargalos, prever limites e garantir uma experiência satisfatória para os usuários finais.

## 2. Objetivo

- Validar a performance da API de cinema ***de todas as rotas devidamente implementadas**, por meio de diferentes cenários e com base nos critérios de aceitação de cada user story.
- Identificar gargalos de performance e comportamentos inesperados ao testar sob cargas variadas.
- Avaliar a resiliência da API, analisando sua capacidade de lidar com picos de acesso e situações adversas.
- Garantir que os tempos de resposta permaneçam dentro das métricas estabelecidas, mesmo durante condições de alta demanda.

A API será executada em um ambiente na AWS, utilizando uma instância EC2, com os testes sendo realizados via npm.

## 3. Ambiente de Teste de Performance

| Parâmetro            | Configuração     |
|----------------------|------------------|
| Processador          | t2.micro        |
| Sistema Operacional  | Amazon Linux    |

## 4. Ferramentas a serem utilizadas

- **K6**: Ferramenta de execução dos testes de carga e performance.
- **HTML Report**: Para geração de relatórios consolidados.
- **AWS EC2**: Ambiente de execução da API.
- **GitLab**: Gestão e automação de pipelines dos testes.

## 5. Massa de Teste

Os testes serão realizados nas rotas relacionadas aos movies, utilizando dados dinâmicos e gerados automaticamente conforme os cenários definidos.

## 6. Cenários de Teste

## Rota /movies

### Criação de Filmes (PF-001 - Test Create Movies)

**Objetivo:**  
Validar que a API consegue processar solicitações de criação de filmes de forma eficiente e dentro dos critérios de aceitação estabelecidos.

**Critérios de Aceitação:**  

- A API deve ser capaz de processar pelo menos 100 solicitações de criação de filmes por segundo.
- O tempo médio de resposta para a criação de um novo filme não deve exceder 200 milissegundos.

**Configuração do Cenário:**  

| Parâmetro             | Configuração              |
|-----------------------|---------------------------|
| Executor              | constant-arrival-rate     |
| Taxa de Chegada       | 100 solicitações/segundo  |
| Duração               | 1 minuto                 |
| Usuários Alocados (VUs) | 50                       |
| Usuários Máximos (VUs) | 100                       |
| Thresholds            | http_req_duration: p(95)<200ms |

---

## Listagem de Filmes (PF-002 - Test List Movies)

**Objetivo:**  
Validar que a API consegue responder a solicitações de listagem de filmes com eficiência e dentro dos critérios de aceitação estabelecidos.

**Critérios de Aceitação:**  

- A API deve ser capaz de responder a solicitações GET de listagem de filmes em menos de 100 milissegundos.
- A lista de filmes deve ser paginada, com no máximo 20 filmes por página.
- A API deve responder a solicitações GET de detalhes de um filme em menos de 50 milissegundos.

**Configuração do Cenário:**  

| Parâmetro             | Configuração              |
|-----------------------|---------------------------|
| Executor              | constant-arrival-rate     |
| Taxa de Chegada       | 100 solicitações/segundo  |
| Duração               | 1 minuto                 |
| Usuários Alocados (VUs) | 50                       |
| Usuários Máximos (VUs) | 100                       |
| Thresholds            | http_req_duration: p(95)<100ms |

---

## Rota /tickets

## Teste do Ciclo de Vida de Tickets (PF-003 - Ticket Life Cycle Test)

## Objetivo
Validar o ciclo de vida completo de um ticket (criação, obtenção, atualização e exclusão) dentro dos critérios de aceitação estabelecidos.

## Critérios de Aceitação

- A API deve responder com sucesso (código 200 ou 201) para todas as operações.
- O tempo de resposta para todas as operações não deve exceder 300 milissegundos.
- Todos os tickets devem ser processados de acordo com as condições definidas nos cenários de teste.

## Configuração dos Cenários

| Tipo de Teste | Executor          | VUs | Iterações | Duração Máxima | Thresholds Avg | Thresholds p(95) |
|---------------|-------------------|-----|-----------|----------------|----------------|-------------------|
| Carga         | shared-iterations | 50  | 100       | 30s            | < 250 ms       | < 400 ms          |
| Estresse      | shared-iterations | 100 | 100       | 30s            | < 500 ms       | < 700 ms          |
| Pico          | shared-iterations | 200 | 200       | 20s            | < 800 ms       | < 1000 ms         |
| Resiliência   | shared-iterations | 50  | 50        | 40s            | < 400 ms       | < 600 ms          |
| Volume        | shared-iterations | 20  | 200       | 60s            | < 1000 ms      | < 1200 ms         |

## Operações Realizadas no Teste

| Operação   | Status Esperado | Thresholds de Tempo de Resposta |
|------------|-----------------|----------------------------------|
| Criação    | 201             | Tempo de resposta < 300 ms      |
| Obtenção   | 200             | Tempo de resposta < 300 ms      |
| Atualização| 200             | Tempo de resposta < 300 ms      |
| Exclusão   | 200             | Tempo de resposta < 300 ms      |

## Fluxo do Teste

1. Criar um ticket com dados gerados aleatoriamente.
2. Obter os detalhes do ticket criado utilizando o ID.
3. Atualizar o ticket alterando o número do assento.
4. Excluir o ticket criado.

## Teste de Performance de Tickets (PF-004 - Tickets Performance Test)

## Objetivo
Validar a performance da API na criação de tickets sob uma taxa constante de chegada, garantindo eficiência e conformidade com os critérios de aceitação.

## Critérios de Aceitação

- A API deve ser capaz de criar tickets com sucesso (status 201).
- O tempo médio de resposta para criação de tickets deve ser inferior a 300 milissegundos.
- O tempo de resposta para 95% das requisições deve ser inferior a 300 milissegundos.

## Configuração do Cenário

| Parâmetro             | Configuração           |
|-----------------------|-----------------------|
| Executor              | constant-arrival-rate |
| Taxa de Chegada       | 50 solicitações/segundo |
| Duração               | 1 minuto             |
| Usuários Pré-Alocados | 50                   |
| Usuários Máximos      | 100                  |

## Thresholds

| Métrica                         | Critério                        |
|---------------------------------|---------------------------------|
| http_req_duration               | Tempo médio < 300 ms           |
| http_req_duration{status:201}   | 95% das respostas < 300 ms     |

## Fluxo do Teste

1. Gerar um ticket utilizando dados aleatórios.
2. Enviar uma requisição POST para criar o ticket.
3. Validar:
   - Status da resposta deve ser 201.
   - Tempo de resposta deve ser inferior a 300 ms.
4. Aguardar 1 segundo antes de processar a próxima requisição.

---

## Analises 



## Conclusão

Os testes foram planejados para validar os principais critérios de aceitação relacionados à criação e listagem de filmes na API de cinema, com cenários que simulam condições de uso intensivo e critérios rigorosos de tempo de resposta.

Essa abordagem permitirá identificar possíveis gargalos e validar a resiliência da API sob condições de alta demanda, garantindo uma experiência consistente e satisfatória para os usuários finais.
