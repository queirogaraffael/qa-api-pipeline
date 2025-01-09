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

## 4. Funcionalidades ou Módulos a serem testados

| Módulo/Funcionalidade | Testes Incluídos |
|-----------------------|------------------|
| **Filmes**           | Listar, buscar por ID, criar, atualizar e deletar filmes. |
| **Tickets**          | Listar todos, buscar por ID, criar, atualizar e deletar tickets. |
| **Usuários e Login** | Cadastro, login, autenticação, e controle de permissões (administrador e usuario comum). |

---

## 5. Local dos Testes

- **Ambiente de Desenvolvimento Local**: Os testes serão realizados na mesma porta (`http://localhost:3000`), mas em uma **instância EC2 da AWS**, simulando um ambiente de produção.
  
- **Testes de Performance**: Serão realizados em um ambiente que emule condições reais de uso, para garantir que a API seja capaz de lidar com carga e tráfego conforme o esperado.

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


## 8. Riscos

Durante os testes com o Postman e k6 na AWS, alguns riscos podem surgir. Abaixo estão os riscos identificados e seus respectivos planos de mitigação:

| **Risco**                             | **Plano de Mitigação**                                                                 |
|---------------------------------------|----------------------------------------------------------------------------------------|
| **Falha no servidor local**           | Utilizar um ambiente em nuvem (AWS) como backup para garantir a continuidade dos testes. |
| **Dados de teste insuficientes**      | Gerar novos dados de teste conforme necessário para garantir a cobertura adequada.       |
| **Erros de autenticação (JWT, API)**  | Verificar se as chaves e tokens de autenticação estão corretos antes de iniciar os testes. |
| **Inconsistência na carga de tráfego**| Realizar testes com k6 utilizando múltiplos cenários de carga para identificar possíveis problemas de desempenho e escalabilidade. |
| **Problemas de rede na AWS**          | Monitorar a infraestrutura da AWS e garantir que os serviços (EC2, RDS, etc.) estejam configurados corretamente. |
| **Falhas no Postman durante os testes**| Validar as variáveis e parâmetros no Postman antes da execução dos testes para evitar falhas. |

---

## 9. Testes exploratórios
Os testes exploratórios são fundamentais porque permitem explorar o sistema de forma dinâmica, sem a limitação de roteiros pré-definidos. Essa abordagem estimula a criatividade e o pensamento crítico, ajudando a identificar defeitos inesperados que podem passar despercebidos em testes tradicionais baseados em scripts. Além disso, os testes exploratórios permitem identificar rapidamente problemas na documentação do Swagger, garantindo maior alinhamento entre a implementação e a descrição das APIs. Essa flexibilidade também facilita a adaptação a mudanças nos requisitos ou funcionalidades, otimizando o tempo e os recursos. Ao mesmo tempo, essa prática oferece um entendimento mais profundo do sistema, contribuindo para melhorar a qualidade geral do produto. **O arquivo de testes exploratórios estará disponível no repositório para consulta detalhada.**

---

## 10. Estrategias de testes 
A abordagem de testes incluirá cobertura completa de requisitos, abrangendo cenários positivos e negativos. Serão realizados testes baseados em riscos para áreas críticas, testes exploratórios sem roteiro fixo, e testes baseados em modelos representativos do sistema. Também serão executados testes de caixa preta para avaliar entradas e saídas, testes manuais para validação direta, e testes automatizados para maior eficiência. Além disso, os testes BDD serão utilizados para alinhar o comportamento esperado com os requisitos do negócio. Os casos e cenários de testes funcionais serão escritos em Gherkin, enquanto o planejamento dos cenários e cargas para testes não funcionais será documentado diretamente nesta seção. Essa abordagem garante uma validação abrangente e bem estruturada do sistema.

---

## 11. Candidatos a testes automatizados
| User Story                        | Categoria     | Teste                                                                                       |
|-----------------------------------|---------------|--------------------------------------------------------------------------------------------|
| Cadastro e Gerenciamento de Usuários | Funcional     | Cadastro de usuário com campos obrigatórios válidos e email único.                         |
|                                   |               | Rejeição de cadastro com email inválido ou senhas fora do padrão.                          |
|                                   |               | Listagem de usuários com paginação (page e limit).                                         |
|                                   |               | Atualização de usuários existentes com dados válidos.                                      |
|                                   |               | Rejeição de atualização/deleção para usuários inexistentes.                                |
|                                   | Não Funcional | Teste de performance: tempo de resposta inferior a 2 segundos para 95% das requisições.   |
|                                   |               | Suporte a 1000 requisições simultâneas.                                                   |
|                                   |               | Uso de recursos abaixo de 80% em carga normal.                                             |
| API de Reservas de Cinema         | Funcional     | Autenticação com token Bearer válido.                                                     |
|                                   |               | Rejeição de reservas para assentos indisponíveis.                                          |
|                                   |               | Listagem de tickets com paginação (page e limit).                                          |
|                                   |               | Retornar detalhes do ticket por ID válido.                                                |
|                                   |               | Rejeição de atualização/deleção para tickets inexistentes.                                 |
|                                   | Não Funcional | Processar 100 solicitações de reserva por segundo com tempo médio de resposta < 300ms.    |
|                                   |               | Suportar até 500 usuários simultâneos.                                                    |
| Gerenciamento de Filmes           | Funcional     | Criação de filmes com título único.                                                       |
|                                   |               | Rejeição de criação com título duplicado.                                                 |
|                                   |               | Listagem de filmes com paginação e detalhes por ID.                                       |
|                                   |               | Atualização e exclusão de filmes existentes.                                              |
|                                   | Não Funcional | Processar 100 solicitações de criação por segundo (tempo médio < 200ms).                  |
|                                   |               | Processar 30 exclusões por segundo (tempo médio < 400ms).                                  |
| Autenticação de Usuários          | Funcional     | Login bem-sucedido para usuários válidos.                                                 |
|                                   |               | Rejeição de login para usuários inexistentes ou senha inválida.                            |
|                                   |               | Validação de token Bearer para diferentes níveis de usuário (admin/usuário comum).         |
|                                   | Não Funcional | Tempo de resposta para login < 2 segundos.                                                |
|                                   |               | Suporte a cenários de pico sem falhas críticas.                                            |

---

## 12. Matriz de risco
| Código | Risco                                                                                     | Probabilidade | Impacto | Nível de Risco | Mitigação                                                                                                         |
|--------|-------------------------------------------------------------------------------------------|---------------|---------|----------------|------------------------------------------------------------------------------------------------------------------|
| R1     | Erros no CRUD de usuários, como falha em validar e-mails únicos ou senhas fora do padrão. | Alta          | Alta    | Crítico        | Implementar validações robustas no backend, revisar cenários de testes automatizados e realizar testes de carga. |
| R2     | Tokens Bearer inválidos ou expirados permitindo acesso indevido às funcionalidades protegidas. | Média         | Alta    | Alto           | Garantir validações no middleware de autenticação e adicionar testes para tokens expirados e inválidos.         |
| R3     | Falha na reserva de tickets devido a assentos já ocupados ou conflitos de sincronização.   | Alta          | Alta    | Crítico        | Implementar controle de concorrência no backend e realizar testes de carga em cenários simultâneos.             |
| R4     | Demora no tempo de resposta da API em testes de performance, degradando a experiência do usuário. | Alta      | Alta    | Crítico        | Otimizar consultas ao banco de dados, usar cache onde aplicável e monitorar uso de recursos em cenários de estresse. |
| R5     | Usuários não conseguirem acessar funcionalidades devido a falhas na autenticação.         | Média         | Alta    | Alto           | Revisar lógica de autenticação e adicionar cobertura de testes para diferentes cenários de credenciais inválidas. |
| R6     | API não suportar o volume de requisições esperado nos testes de performance e carga.      | Alta          | Alta    | Crítico        | Revisar a arquitetura, usar técnicas de balanceamento de carga e realizar testes de estresse frequentes.         |
| R7     | Criação de filmes duplicados devido à falta de validação de título único.                 | Média         | Média   | Moderado       | Implementar validação única no banco de dados e adicionar testes de criação com dados duplicados.                |
| R8     | Falhas críticas durante testes de durabilidade (24 horas ou mais) causarem inconsistência de dados. | Baixa      | Alta    | Moderado       | Monitorar continuamente o uso de recursos e revisar logs de execução para identificar gargalos potenciais.       |
| R9     | Falta de logs apropriados dificultando a identificação de erros em produção.              | Média         | Alta    | Alto           | Implementar logging estruturado com níveis adequados (info, warning, error) e incluir métricas detalhadas nos relatórios. |

---

## 13. Issues
As issues, sejam relacionadas a melhorias ou correções de bugs, serão gerenciadas diretamente no GitLab. Essa abordagem facilita a organização, a busca e a visualização das tarefas, garantindo um acompanhamento mais eficiente.

---

## 14. Matriz de rastreabilidade
A matriz de rastreabilidade vai ser colocado em um arquivo separado para assim melhorar a pesquisa e visulização dela.

---

## 15. Testes de performance 
 O teste de performance vai ser colocado em um arquivo separado para uma melhor visualização.

---

## 16. Avaliação da api atual e expetiativa do projeto 
A API atual apresenta inumeros problemas, dentre eles principalmente e questão da segunraça, pois todos podem acessar os endpoints, alguns endpoints estão mal construidos e com grandes riscos de falhas, como o GET de pegar todos os filmes e tickets. pois eles retornam todos os dados, e isso do ponto de vista de performance e desempenho não são bons, pois isso espera-se que tenha paginação por exemplo. o endpoint de de cadastrar um ticket ele cadastra mais de um ticket para um assento de filme. Ela tambem não tem a api de usuarios e login implementadas, nesse caso se relaciona com a questão da segurança ne. dentre outros problemas que a api tem. Com isso a expectativa é entrar em consenso com a equipe de desenvolvimento para com isso melhorar a qualidade da API.

---

## 17. Avaliação de Segurança da API  
 A API apresenta problemas de segurança, poois o acesso a todos os endpoints ate agora implementados (movies e tickets) podem ser acessados por qualquer um. Nesse caso no desenvolvimento da api e consequentemente em seus testes, a questão da segurança tem que ser levada em conta. 


---

## 18. User Stories

O projeto inclui quatro **user stories**, cada uma correspondendo a uma rota específica da API. As user stories serão documentadas na **Wiki** para facilitar a busca e a visualização.

### 1. **Cadastro e Gerenciamento de Usuários na API**
Essa user story cobre o processo de cadastro e gerenciamento de usuários na API, permitindo criar, atualizar, e excluir usuários, além de consultar informações sobre os mesmos.

### 2. **API de Reservas de Cinema**
Aqui, abordamos a funcionalidade de reservas de cinema, que inclui a criação de reservas, consulta de reservas existentes, atualização e cancelamento de reservas.

### 3. **Gerenciamento de Filmes na API de Cinema**
Esta user story se refere ao gerenciamento de filmes dentro da API, permitindo adicionar, editar, excluir e consultar filmes disponíveis para exibição.

### 4. **Autenticação de Usuários na API**
Esta user story descreve o processo de autenticação de usuários, incluindo o login, registro de sessão e gerenciamento de permissões de acesso.

As user stories estarão organizadas na **Wiki**, garantindo fácil acesso e atualização para os membros da equipe.

---

## 19.Mapas Mentais

Os mapas mentais foram desenvolvidos utilizando a ferramenta **Miro**. Eles ajudam a visualizar o estado atual e desejado da API NestJS Cinema, servindo como base para os testes ideais do projeto.

### 1. Mapa Mental - API NestJS Cinema (Estado Atual)
Este mapa mental representa o estado atual da API NestJS Cinema. Ele serve como referência para entender a estrutura e os desafios do sistema até o momento.

Acesse o mapa mental [aqui](https://miro.com/welcomeonboard/Nkg1UmVWeE0vckdtQml3QnFGQWZXemFScTc1Yzl0aFhzRU1TZ2tWcU5YUUpTekNWd3Y1SndQZHliM0h0dWhGQlNDN2tzRnZWc3NHam54cno5Q1c4NzdnQnZ2RkZYVmVzLzFuSXk3amR2c2Q1SGwvNUhrRi9pS0t3S0g2bkYwVy8hZQ==?share_link_id=783629897800).

### 2. Mapa Mental - API NestJS Cinema (Estado Desejado)
Este mapa mental descreve o estado desejado da API NestJS Cinema, ou seja, o objetivo final do projeto. Ele serve como base para orientar os testes ideais e as melhorias a serem implementadas.

Acesse o mapa mental [aqui](https://miro.com/welcomeonboard/RWpySVcyMksvRTgvaFJTWGwwWnk1NEFYbkYzNEhFbUJDN29KbThnYi9SUTJtL2pPOFNyN0tzd01XV21LU2grendwZmMvMFRPVjhKbjg2NytJeU1JL0xnQnZ2RkZYVmVzLzFuSXk3amR2c2Q1SGwvNUhrRi9pS0t3S0g2bkYwVy8hZQ==?share_link_id=269424498189).

### Explicação dos Mapas Mentais
- **Mapa Atual**: Reflete a situação atual da API e fornece informações essenciais para identificar pontos de melhoria.
- **Mapa Desejado**: Representa o objetivo do projeto, detalhando onde se espera chegar com a API, servindo como base para os testes e implementações futuras.

---

## 20. Como os testes serão executados
Os testes funcionais serão executados utilizando uma coleção no Postman, garantindo a validação dos endpoints e fluxos principais da aplicação. Já os testes não funcionais, como desempenho e carga, serão realizados com o k6, permitindo análises detalhadas sobre a estabilidade e capacidade do sistema.

---

## 21. Como os resultados dos testes serão apresentados ?
Será criada uma pasta no diretório Docs para armazenar os registros da evolução da API, incluindo resultados, alterações e melhorias implementadas ao longo do desenvolvimento. Além disso, a Wiki será utilizada para documentar os contextos, análises e detalhes dos relatórios, facilitando o acesso e a compreensão das informações.

---

## 22. Cronograma

O cronograma do projeto, tanto a parte de desenvolvimento, planejamento de testes, automação de testes e DevOps, Abaixo estão as etapas detalhadas:

| Atividade                      | Data Inicial | Data Final   |
|--------------------------------|--------------|--------------|
| **Planejamento**               | 30/12/2024   | 31/12/2024   |
| **Configuração de Ambiente**   | 01/01/2025   | 02/01/2025   |
| **Criação de Casos de Teste**  | 03/01/2025   | 05/01/2025   |
| **Automação de Testes**        | 06/01/2025   | 08/01/2025   |
| **Execução de Testes**         | 09/01/2025   | 10/01/2025   |