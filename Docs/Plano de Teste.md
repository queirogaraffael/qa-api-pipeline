# Planejamento de Testes da API de Cinema

## 1. Nome do projeto
**Gerenciamento e Testes da API de Cinema**

---

## 2. Resumo

### Por que faremos o teste?
Assegurar que a API de Cinema funcione conforme o esperado, com foco em suas funcionalidades principais, como gerenciamento de filmes, tickets e controle de acesso por permissões de usuários.

### Hipótese
Acredita-se que a API de Cinema atenda às expectativas de performance, segurança e funcionalidade descritas na documentação.

### Resultado do teste
Verificar se a API realiza operações básicas (CRUD) e outras funcionalidades específicas com eficiência e precisão.

---

## 3. Pessoas envolvidas
- **Raffael Queiroga** - QA Tester, Desenvolvedor, DevOps

---

## 5. Local dos testes
- **Ambiente de desenvolvimento local** utilizando a URL `http://localhost:3000`.(na verdade vai ser testado nessa mesma porta, porem numa ec2 da aws)
- **Testes de performance** em um ambiente que emule condições reais de uso.

---

## 6. Recursos necessários

- **Software**: Postman e k6 para testes funcionais automatizados e para testes de performance performance.
- **Infraestrutura**: Conexão à internet estável, conexão com a ec2 da aws. 
- **Dados**: Scripts para geração de dados de teste (usuários, filmes e tickets) no k6. 
- a api disponivel para testes ?

---

## 7. Critérios usados

- **Avaliação dos testes**: Validação manual e automatizada dos endpoints; relatórios de cobertura.
- **Divisão entre equipe**:
  - QA: Testes funcionais.
  - Devs: Correções e melhorias com base nos defeitos encontrados.
- **Tarefas para usuários**: Testes de permissões e acessos diferenciados para administradores e usuários regulares.

---

## Avaliação de segurança da API
 A API apresenta problemas de segurança, poois o acesso a todos os endpoints ate agora implementados (movies e tickets) podem ser acessados por qualquer um. Nesse caso no desenvolvimento da api e consequentemente em seus testes, a questão da segurança tem que ser levada em conta. 


## Avaliação da api atual e expetiativa do projeto 
A API atual apresenta inumeros problemas, dentre eles principalmente e questão da segunraça, pois todos podem acessar os endpoints, alguns endpoints estão mal construidos e com grandes riscos de falhas, como o GET de pegar todos os filmes e tickets. pois eles retornam todos os dados, e isso do ponto de vista de performance e desempenho não são bons, pois isso espera-se que tenha paginação por exemplo. o endpoint de de cadastrar um ticket ele cadastra mais de um ticket para um assento de filme. Ela tambem não tem a api de usuarios e login implementadas, nesse caso se relaciona com a questão da segurança ne. dentre outros problemas que a api tem. Com isso a expectativa é entrar em consenso com a equipe de desenvolvimento para com isso melhorar a qualidade da API.

# User stories
Temos 4 user stories uma pra cada rota. User Story :  Cadastro e Gerenciamento de Usuários na API
User Story: API de Reservas de Cinema, User Story: Gerenciamento de Filmes na API de Cinema, User Story: Autenticação de Usuários na API
elas estão no diretorio de User Stories. pois assim facilida asua busca e visualização.

## Mapas mentais
Os mapas mentais foram desenvolvidos pelo miro. 
Tem esse mapa mental Mapa Mental - API NestJS Cinema (Estado Atual) com link:

e outra Mapa Mental - API NestJS Cinema (Estado Desejado) com link:

Nesse caso tem dois mapas mentais, pois um é da API atual e o outro e de onde quer ser chegado no final do projeto e por onde vai ser baseado os testes ideiais. 

## 4. Funcionalidades ou Módulos a serem testados

| Módulo/Funcionalidade | Testes Incluídos |
|-----------------------|------------------|
| **Filmes**           | Listar, buscar por ID, criar, atualizar e deletar filmes. |
| **Tickets**          | Listar todos, buscar por ID, criar, atualizar e deletar tickets. |
| **Usuários e Login** | Cadastro, login, autenticação, e controle de permissões (administrador e usuario comum). |

---

## 8. Riscos

| Risco                  | Plano de Mitigação                        |
|------------------------|-------------------------------------------|
| **Falha no servidor local** | Utilizar um ambiente em nuvem como backup. |
| **Dados de teste insuficientes** | Gerar novos dados ou usar mocks.           |
| **Erros de autenticação** | Verificar chaves e tokens antes do teste.      |

---

## 9. Como os resultados do teste serão divulgados

- **Relatórios gerados**:
  - Relatório de resultados gerais (PDF).
  - Relatório de defeitos (CSV).
- **Métricas usadas**:
  - Taxa de sucesso/falha por endpoint.
  - Tempo médio de resposta.
  - Cobertura de testes.

---

# Cobertura de testes

## como os testes serão executados
os testes funcionais vão ser executados em uma coleção postman, e os testes nao funcionais vão ser testados no k6. 

## Como os resultados dos testes serão apresentados ?
vai ser criado uma pasta no diretorio Docs em que vai ter o resultado da evolução da api. 

## Estrategias de testes 

Cobertura de requisitos com cenários positivos e negativos, testes baseados em riscos para áreas críticas, testes exploratórios sem roteiro fixo, testes baseados em modelos representativos do sistema, testes de caixa preta avaliando entradas e saídas, testes manuais para validação direta, testes automatizados para eficiência, testes BDD alinhando comportamento esperado com os requisitos do negócio. Os casos e cenarios de testes funcionais serão escritos em gherkin. Ja os testes nao funcionais o planejamento dos cenarios e cargas serão colocados aqui mesmo na documentação. 

## Candidatos a testes automatizados





## matriz de risco
codigo, risco, probabilidade, impacto nivel de risco e mitigação


# Issues
A issues, tanto melhorias quanto bugs vão ser colocado em uma pasta no diretorio de Docs, pois assim ficara melhor de buscar e visualizar.


## Matriz de rastreabilidade
A matriz de rastreabilidade vai ser colocado em um arquivo separado para assim melhorar a pesquisa e visulização dela.



## testes de performance 

apresentação, objetivo, ambiente, ferramentas, massa de testes, vai ser feito apenas pros que estão implementados, o porque dos tipos de testes de cada rota e contexto de cada rota
, metricas, e cenarios. resultado por rota. fluxos


## 10. Cronograma
(o cronograma do projeto, tanto a parte de desenvolvimento, planejamento de testes, automação de testes e depops vai ser de 30/12/2024 ate 10/01/2025. )

| Atividade                 | Data Inicial | Data Final   |
|---------------------------|--------------|--------------|
| **Planejamento**          | 05/01/2025   | 07/01/2025   |
| **Criação de Casos de Teste** | 08/01/2025   | 10/01/2025   |
| **Execução de Testes**    | 11/01/2025   | 15/01/2025   |
| **Consolidação de Resultados** | 16/01/2025   | 17/01/2025   |

