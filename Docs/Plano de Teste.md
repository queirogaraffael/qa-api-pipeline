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

## 10. Cronograma -> (o cronograma do projeto, tanto a parte de desenvolvimento, planejamento de testes, automação de testes e depops vai ser de 30/12/2024 ate 10/01/2025. )

| Atividade                 | Data Inicial | Data Final   |
|---------------------------|--------------|--------------|
| **Planejamento**          | 05/01/2025   | 07/01/2025   |
| **Criação de Casos de Teste** | 08/01/2025   | 10/01/2025   |
| **Execução de Testes**    | 11/01/2025   | 15/01/2025   |
| **Consolidação de Resultados** | 16/01/2025   | 17/01/2025   |


cobertura

como o resultado dos testes serão executados

estrategias de testes 

candidatos a testes automatizados

testes exploratorios


matriz de risco

## Avaliação de segurança da API
 A API apresenta problemas de segurança, poois o acesso a todos os endpoints ate agora implementados (movies e tickets) podem ser acessados por qualquer um. Nesse caso no desenvolvimento da api e consequentemente em seus testes, a questão da segurança tem que ser levada em conta. 


## Avaliação da api atual e expetiativa do projeto 
A API atual apresenta inumeros problemas, dentre eles principalmente e questão da segunraça, pois todos podem acessar os endpoints, alguns endpoints estão mal construidos e com grandes riscos de falhas, como o GET de pegar todos os filmes e tickets. pois eles retornam todos os dados, e isso do ponto de vista de performance e desempenho não são bons, pois isso espera-se que tenha paginação por exemplo. o endpoint de de cadastrar um ticket ele cadastra mais de um ticket para um assento de filme. Ela tambem não tem a api de usuarios e login implementadas, nesse caso se relaciona com a questão da segurança ne. dentre outros problemas que a api tem. Com isso a expectativa é entrar em consenso com a equipe de desenvolvimento para com isso melhorar a qualidade da API.


testes de performance 

apresentação, objetivo, ambiente, ferramentas, massa de testes, vai ser feito apenas pros que estão implementados, o porque dos tipos de testes de cada rota e contexto de cada rota
, metricas, e cenarios. resultado por rota. fluxos
