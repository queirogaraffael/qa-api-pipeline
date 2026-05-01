ID: BUG-001
Tipo de Bug: Segurança
Data do Relato: 2025-01-09
Ambiente de Teste: AWS EC2

Descrição:
Os endpoints da rota /movies não possuem validação de autenticação, permitindo acesso não autorizado a funcionalidades sensíveis. De acordo com a documentação da API, todos os endpoints desta rota deveriam estar protegidos por autenticação.

Passos para Reproduzir:

    Envie uma requisição GET para /movies sem fornecer nenhum token de autenticação.
    Envie uma requisição POST para /movies para criar um novo filme, também sem fornecer token de autenticação.
    Observe que ambas as requisições são processadas com sucesso, mesmo sem a autenticação.

Resultado Esperado:

    O sistema deve retornar um status 401 Unauthorized para qualquer requisição sem autenticação.
    A mensagem de resposta deve indicar que o usuário não está autenticado e que um token válido é necessário.
    Nenhuma ação deve ser realizada nos endpoints sem autenticação.

Resultado Atual:
O sistema processa as requisições e retorna 200 OK ou 201 Created, permitindo acesso e alterações nos dados sem necessidade de autenticação.

- Prioridade: P1 (Alto Risco de Segurança)
- Gravidade: S1 (Ameaça Crítica à Segurança)

Correção Sugerida:

    Implementar middleware de autenticação em todos os endpoints da rota /movies.
    Garantir que requisições não autenticadas sejam bloqueadas com o status apropriado (401 Unauthorized).
    Adicionar testes automatizados para validar o comportamento de autenticação em todos os endpoints.

---

ID: BUG-002
Tipo de Bug: Segurança
Data do Relato: 2025-01-09
Versão: v1.0
Ambiente de Teste: AWS EC2

Descrição:
Os endpoints da rota /tickets não possuem validação de autenticação, permitindo que usuários não autenticados acessem e manipulem dados relacionados a tickets. De acordo com a documentação da API, todos os endpoints desta rota deveriam estar protegidos por autenticação para garantir a segurança e integridade dos dados.

Passos para Reproduzir:

    Envie uma requisição GET para /tickets sem fornecer nenhum token de autenticação.
    Envie uma requisição POST para /tickets para criar um novo ticket, também sem fornecer token de autenticação.
    Observe que ambas as requisições são processadas com sucesso, mesmo sem autenticação.

Resultado Esperado:

    O sistema deve retornar um status 401 Unauthorized para qualquer requisição sem autenticação.
    A mensagem de resposta deve informar que o usuário não está autenticado e que um token válido é necessário.
    Nenhuma ação deve ser realizada nos endpoints sem autenticação.

Resultado Atual:
O sistema processa as requisições e retorna 200 OK ou 201 Created, permitindo o acesso e a manipulação de dados de tickets sem necessidade de autenticação.

- Prioridade: P1 (Alto Risco de Segurança)
- Gravidade: S1 (Ameaça Crítica à Segurança)

Correção Sugerida:

    Implementar middleware de autenticação em todos os endpoints da rota /tickets.
    Garantir que requisições não autenticadas sejam bloqueadas com o status apropriado (401 Unauthorized).
    Realizar testes automatizados para verificar se todos os endpoints requerem autenticação antes de processar as requisições.

---

**ID**: Bug-003: Endpoint /movies permite cadastro de filme com dados inválidos
**Tipo de Bug**: Funcional  
**Data do Relato**: 2025-01-09  
**Ambiente de Teste**: AWS EC2

## Descrição  
Ao tentar cadastrar um filme via a API com dados inválidos, o sistema aceita o cadastro mesmo quando o valor do campo `showtimes` está incorreto ou fora do formato esperado. Isso contraria o comportamento esperado conforme a documentação da API.  

## Passos para Reproduzir  
1. Autentique-se como um usuário administrador.  
2. Envie uma requisição `POST` para o endpoint `/movies` com os seguintes dados no corpo da requisição:  
   ```json
   {
     "title": "Filme de Exemplo",
     "description": "Descrição do filme",
     "launchdate": "2025-01-09T16:40:45.398Z",
     "showtimes": 0
   }
   ```
3. Observe a resposta da API.  

## Resultado Esperado  
- O sistema deve retornar o código de status **400 Bad Request** em menos de 300ms.  
- O corpo da resposta deve informar que o valor do campo `showtimes` é inválido.  
- O filme **não deve ser cadastrado** no banco de dados.  

## Resultado Atual  
- O sistema retorna o código de status **201 Created** e cadastra o filme com o valor inválido no campo `showtimes`.  

## Impacto  
- **Prioridade**: P2 (Violação de Regra de Negócio)  
- **Gravidade**: S2 (Impacto na Integridade dos Dados)  

## Correção Sugerida  
Implementar uma validação no endpoint `/movies` para garantir que o campo `showtimes` tenha valores válidos antes de permitir o cadastro do filme.  

## Teste Automático para Verificação do Bug  
```javascript
pm.test("Status code is 400", function () {
    pm.response.to.have.status(400);
});
```

---

- ID: BUG-004 : Endpoint da rota /movies não implementa busca paginada conforme especificado
- Tipo de Bug: Funcionalidade
- Data do Relato: 2025-01-09
- Ambiente de Teste: AWS EC2

Descrição:
A API da rota /movies está retornando todos os registros em uma única requisição, ao invés de implementar a busca paginada conforme especificado na user story e na documentação da API. Esse comportamento impacta negativamente o desempenho do sistema, especialmente para grandes volumes de dados.

Passos para Reproduzir:

    Envie uma requisição GET para /movies sem parâmetros adicionais.
    Observe que a API retorna todos os registros disponíveis, sem nenhum mecanismo de paginação.

Resultado Esperado:

    A API deve implementar busca paginada, permitindo a recuperação dos dados em partes menores.
    Deve aceitar parâmetros como page e limit para controlar a paginação.
    A resposta deve incluir informações como totalPages, currentPage e totalItems no corpo.

Resultado Atual:
A API retorna todos os registros em uma única requisição, ignorando a necessidade de paginação.

- Prioridade: P2 (Desempenho Afetado)
- Gravidade: S2 (Impacto Médio no Sistema)

Correção Sugerida:

    Implementar paginação nos endpoints da rota /movies.
    Adicionar parâmetros como page e limit para controle da paginação.
    Ajustar a resposta para incluir metadados da paginação (e.g., totalPages, currentPage, totalItems).
    Atualizar a documentação da API para incluir as informações relacionadas à paginação.

---

ID: B-005
Tipo de Bug: Funcionalidade
Data do Relato: 2025-01-09
Ambiente de Teste: AWS EC2

Descrição:
Ao criar um novo filme via o endpoint POST /movies, o corpo da resposta não inclui o ID do filme recém-criado. Esse comportamento contradiz o esperado, pois a ausência do ID dificulta o uso posterior do recurso (atualizações, exclusões ou criação de tickets associados).

Passos para Reproduzir:

    Envie uma requisição POST para /movies com os dados necessários para criar um novo filme (e.g., título, descrição, showtimes).
    Verifique o corpo da resposta.

Resultado Esperado:

    O corpo da resposta deve incluir o ID único do filme recém-criado (e.g., "id": "12345abc"), juntamente com outros detalhes relevantes.

Resultado Atual:
O corpo da resposta não contém o ID do filme criado, limitando o uso do recurso.

- Prioridade: P3 (Usabilidade Afetada)
- Gravidade: S2 (Impacto Moderado)

Correção Sugerida:

    Modificar o endpoint POST /movies para incluir o ID do filme recém-criado no corpo da resposta.
    Garantir que o ID seja retornado no formato adequado (string ou UUID).
    Atualizar os testes automatizados e a documentação da API para refletir essa mudança.

---

- ID: BUG-006: Endpoint de atualização de filme (PUT /movies/{id}) não permite enviar dados no corpo da requisição
- Tipo de Bug: Regras de Negócio
- Data do Relato: 2025-01-09
- Ambiente de Teste: AWS EC2

Descrição:
O endpoint POST /movies permite a criação de filmes com títulos duplicados, o que viola a regra de negócio definida. De acordo com os requisitos, cada filme deve ter um título único para evitar confusão e conflitos de dados.

Passos para Reproduzir:

    Envie uma requisição POST para /movies com o título "Filme Exemplo".

    {
        "title": "Filme Exemplo",
        "description": "Descrição do filme",
        "showtimes": ["2025-01-15T19:00:00"]
    }

    Envie outra requisição POST para /movies com o mesmo título "Filme Exemplo".
    Observe que ambas as requisições são processadas com sucesso e criam filmes diferentes com o mesmo título.

Resultado Esperado:

    O sistema deve retornar um status 400 Bad Request ao tentar criar um filme com um título já existente.
    A mensagem de erro deve indicar que o título do filme deve ser único.
    O segundo filme não deve ser criado no banco de dados.

Resultado Atual:
O sistema permite a criação de múltiplos filmes com o mesmo título, retornando um status 201 Created para ambas as requisições.

- Prioridade: P2 (Regra de Negócio Violada)
- Gravidade: S2 (Impacto na Consistência dos Dados)

Correção Sugerida:

    Implementar uma verificação no backend para garantir que o título do filme seja único antes de criar um novo registro.
    Retornar um erro apropriado (400 Bad Request) caso um título duplicado seja enviado.
    Adicionar testes automatizados para validar que títulos duplicados não podem ser criados.
    Atualizar a documentação da API para informar que o campo title é único.

---

- ID: BUG-007: Endpoint de atualização de filme (PUT /movies/{id}) não permite enviar dados no corpo da requisição
- Tipo de Bug: Regras de Negócio
- Data do Relato: 2025-01-09
- Ambiente de Teste: AWS EC2

Descrição:
O endpoint POST /movies permite a criação de filmes com títulos duplicados, o que viola a regra de negócio definida. De acordo com os requisitos, cada filme deve ter um título único para evitar confusão e conflitos de dados.

Passos para Reproduzir:

    Envie uma requisição POST para /movies com o título "Filme Exemplo".

    {
        "title": "Filme Exemplo",
        "description": "Descrição do filme",
        "showtimes": ["2025-01-15T19:00:00"]
    }

    Envie outra requisição POST para /movies com o mesmo título "Filme Exemplo".
    Observe que ambas as requisições são processadas com sucesso e criam filmes diferentes com o mesmo título.

Resultado Esperado:

    O sistema deve retornar um status 400 Bad Request ao tentar criar um filme com um título já existente.
    A mensagem de erro deve indicar que o título do filme deve ser único.
    O segundo filme não deve ser criado no banco de dados.

Resultado Atual:
O sistema permite a criação de múltiplos filmes com o mesmo título, retornando um status 201 Created para ambas as requisições.

- Prioridade: P2 (Regra de Negócio Violada)
- Gravidade: S2 (Impacto na Consistência dos Dados)

Correção Sugerida:

    Implementar uma verificação no backend para garantir que o título do filme seja único antes de criar um novo registro.
    Retornar um erro apropriado (400 Bad Request) caso um título duplicado seja enviado.
    Adicionar testes automatizados para validar que títulos duplicados não podem ser criados.
    Atualizar a documentação da API para informar que o campo title é único.

---

- ID: BUG-008 : É possível excluir filmes com tickets associados
- Tipo de Bug: Regras de Negócio
- Data do Relato: 2025-01-09
- Ambiente de Teste: AWS EC2

Descrição:
O endpoint DELETE /movies/{id} permite excluir um filme que possui tickets associados, o que viola a regra de negócio. De acordo com os requisitos, um filme não deve ser excluído enquanto houver tickets relacionados a ele, para evitar inconsistências nos dados e problemas no fluxo de uso do sistema.

Passos para Reproduzir:

    Crie um filme via POST /movies com os dados desejados.
    Crie um ou mais tickets associados ao filme via POST /tickets.
    Envie uma requisição DELETE para /movies/{id}, usando o ID do filme criado.
    Observe que a requisição é processada com sucesso e o filme é excluído, mesmo havendo tickets associados.

Resultado Esperado:

    O sistema deve retornar um status 400 Bad Request ou 409 Conflict ao tentar excluir um filme com tickets associados.
    A mensagem de erro deve indicar que o filme não pode ser excluído devido à existência de tickets relacionados.
    O filme não deve ser removido do banco de dados enquanto os tickets associados não forem excluídos ou desassociados.

Resultado Atual:
O sistema processa a requisição e exclui o filme com sucesso, retornando um status 200 OK ou 204 No Content, mesmo com tickets associados.

- Prioridade: P1 (Impacto Crítico na Regra de Negócio)
- Gravidade: S1 (Risco Alto de Inconsistência nos Dados)

Correção Sugerida:

    Implementar uma validação no endpoint DELETE /movies/{id} para verificar se o filme possui tickets associados antes de permitir a exclusão.
    Retornar um erro apropriado (400 Bad Request ou 409 Conflict) caso existam tickets relacionados.
    Adicionar testes automatizados para garantir que filmes com tickets associados não possam ser excluídos.
    Atualizar a documentação da API para incluir essa restrição.

---

    - ID: PF-BUG-001
    - Título: API não retorna respostas verificáveis no endpoint POST /movies
    - Tipo de Bug: Não Funcional - Teste de Performance
    - Data do Relato: 2025-01-10
    - Ambiente de Teste: AWS EC2

Descrição:
O endpoint POST /movies não retorna um body ou qualquer tipo de resposta que permita verificar com exatidão se as requisições foram bem-sucedidas durante os testes de performance. Esse comportamento dificulta a validação dos resultados do teste.

Passos para Reproduzir:

    Execute o teste de performance "Test Create Movies" no K6.
    Observe que as respostas das requisições POST /movies não contêm informações úteis (como IDs ou mensagens de sucesso).

Resultado Esperado:

    A API deve retornar um body com informações relevantes (ex.: ID do filme criado, status detalhado).
    A resposta deve permitir verificar o sucesso ou falha de cada requisição.

Resultado Atual:

    As respostas das requisições POST não contêm informações verificáveis, dificultando a análise dos resultados.

Prioridade: P2 (Impacto na análise dos testes)
Gravidade: S2 (Dificuldade média para análise)

Correção Sugerida:

    Atualizar a API para retornar respostas detalhadas no endpoint POST /movies.
    Garantir que as respostas incluam um body com informações úteis para validação.

---

    - ID: PF-BUG-002
    - Título: Endpoint GET /movies não suporta paginação, causando timeouts em testes de performance
    - Tipo de Bug: Não Funcional - Teste de Performance
    - Data do Relato: 2025-01-10
    - Ambiente de Teste: AWS EC2

Descrição:
O endpoint GET /movies apresenta gargalos em testes de performance devido à ausência de suporte para paginação. Isso resulta em timeouts quando grandes volumes de dados são solicitados.

Passos para Reproduzir:

    Execute o teste de performance "Test List Movies" no K6.
    Observe que as requisições para o endpoint GET /movies falham frequentemente devido a timeouts.

Resultado Esperado:

    O endpoint GET /movies deve suportar paginação para evitar sobrecarga e timeouts.
    A API deve retornar apenas o número especificado de registros por página.

Resultado Atual:

    As requisições falham devido à ausência de paginação e ao grande volume de dados retornados.

Prioridade: P1 (Impacto crítico em escalabilidade)
Gravidade: S1 (Alta severidade devido a timeouts)

Correção Sugerida:

    Implementar paginação no endpoint GET /movies.
    Adicionar parâmetros como page e limit para controle dos dados retornados.

---

    - ID: PF-BUG-003
    - Título: Problemas de conexão durante testes de ciclo de vida de tickets
    - Tipo de Bug: Não Funcional - Teste de Estresse
    - Data do Relato: 2025-01-10
    - Ambiente de Teste: AWS EC2

Descrição:
Durante o teste "Ticket Life Cycle Test", a API apresenta problemas de conexão após algumas requisições, possivelmente devido ao estresse causado pela execução paralela de múltiplos testes. Esse comportamento resulta em falhas na captura dos checks.

Passos para Reproduzir:

    Execute o teste "Ticket Life Cycle Test" no K6, com 5 tipos de testes de performance diferentes e em paralelo.
    Observe que a API começa a apresentar problemas de conexão após um curto período.

Resultado Esperado:

    A API deve suportar múltiplos testes de performance em paralelo sem apresentar problemas de conexão.
    Os checks devem ser gerados corretamente para todas as requisições.

Resultado Atual:

    A API apresenta problemas de conexão e não gera os checks de maneira confiável.

Prioridade: P1 (Impacto em estabilidade e análise)
Gravidade: S1 (Alta severidade devido à falha completa em certas condições)

Correção Sugerida:

    Revisar o código da API para melhorar o manuseio de cargas elevadas.
    Implementar medidas de escalabilidade, como balanceamento de carga.
    Monitorar e ajustar limites de conexões simultâneas.

---

    - ID: PF-BUG-004
    - Título: Problemas intermitentes no método POST /tickets durante testes de performance
    - Tipo de Bug: Não Funcional - Teste de Performance
    - Data do Relato: 2025-01-10
    - Ambiente de Teste: AWS EC2

Descrição:
O teste "Tickets Performance Test" indicou problemas intermitentes ao criar tickets via método POST /tickets. As falhas ocorrem de maneira irregular, sugerindo possíveis problemas temporários no ambiente ou na API.

Passos para Reproduzir:

    Execute o teste "Tickets Performance Test" no K6.
    Observe que algumas requisições POST /tickets falham de forma intermitente.
    Alterne a forma de envio das requisições e repita o teste para confirmar o comportamento.

Resultado Esperado:

    Todas as requisições POST /tickets devem ser processadas com sucesso, mesmo em condições de carga elevada.

Resultado Atual:

    Algumas requisições POST falham de maneira intermitente, sem padrão claro.

Prioridade: P2 (Impacto moderado na confiabilidade)
Gravidade: S2 (Severidade média devido à intermitência)

Correção Sugerida:

    Analisar logs e recursos da API para identificar gargalos ou problemas momentâneos.
    Implementar melhorias no gerenciamento de recursos para evitar falhas intermitentes.
    Adicionar monitoramento em tempo real para detectar e responder a problemas rapidamente.

---

ID: BUG-009
Tipo de Bug: Funcionalidade
Data do Relato: 2025-01-10
Ambiente de Teste: AWS EC2

Descrição:
Os endpoints da rota /ticket não permitem a recuperação de tickets pelo ID, mesmo quando um ID válido é fornecido. De acordo com a documentação da API, o endpoint /ticket/_{id} deveria retornar as informações do ticket correspondente ao ID fornecido.

Passos para Reproduzir:

    Envie uma requisição GET para /ticket/_{id}, substituindo {id} por um ID válido de ticket (ex.: /ticket/123).
    Observe que o sistema retorna um erro ou uma resposta inesperada, ao invés de devolver os dados do ticket solicitado.

Resultado Esperado:

    O sistema deve retornar um status 200 OK, acompanhado dos dados do ticket correspondente ao ID fornecido.
    A resposta deve incluir informações como ID do ticket, descrição, status e demais dados relevantes.

Resultado Atual:
O sistema retorna um status 404 Not Found ou 500 Internal Server Error, indicando que não é possível recuperar o ticket pelo ID fornecido.

Prioridade: P2 (Impacto Moderado)
Gravidade: S2 (Funcionalidade Essencial Não Operacional)

Correção Sugerida:

    Corrigir o endpoint /ticket/_{id} para aceitar e processar requisições GET corretamente.
    Verificar se a lógica de busca pelo ID está implementada corretamente no backend.
    Adicionar testes automatizados para garantir o comportamento esperado ao recuperar tickets pelo ID.

---

ID: BUG-010 
Tipo de Bug: Funcionalidade
Data do Relato: 2025-01-10
Ambiente de Teste: AWS EC2

Descrição:
Os endpoints da rota /ticket não permitem a atualização de tickets existentes. De acordo com a documentação da API, o endpoint /ticket/{id} deveria processar requisições PUT ou PATCH para atualizar os dados de um ticket, mas a funcionalidade não está operando como esperado.

Passos para Reproduzir:

    Envie uma requisição PUT ou PATCH para /ticket/{id}, substituindo {id} por um ID válido de ticket, e forneça os dados atualizados no corpo da requisição.
    Observe que o sistema retorna um erro ou não realiza nenhuma alteração no ticket.

Resultado Esperado:

    O sistema deve retornar um status 200 OK, acompanhado dos dados atualizados do ticket.
    Os dados enviados no corpo da requisição devem ser corretamente aplicados ao ticket correspondente ao ID fornecido.

Resultado Atual:
O sistema retorna um status 400 Bad Request, 404 Not Found, ou 500 Internal Server Error, sem realizar nenhuma alteração nos dados do ticket.

Prioridade: P2 (Impacto Moderado)
Gravidade: S2 (Funcionalidade Essencial Não Operacional)

Correção Sugerida:

    Corrigir a lógica do endpoint /ticket/{id} para processar requisições PUT ou PATCH corretamente.
    Garantir que os dados enviados no corpo da requisição sejam validados e atualizados no ticket correspondente.
    Adicionar testes automatizados para validar o comportamento esperado ao atualizar tickets.

---

ID: BUG-011
Tipo de Bug: Validação de Dados
Data do Relato: 2025-01-10
Ambiente de Teste: AWS EC2

Descrição:
É possível cadastrar um ticket com um preço negativo na rota /ticket. De acordo com a lógica de negócios, o preço de um ticket deve ser sempre positivo. Contudo, o sistema não realiza a validação correta do campo de preço e permite que valores negativos sejam cadastrados sem qualquer erro.

Passos para Reproduzir:

    Envie uma requisição POST para /ticket com um corpo de requisição que inclua um valor negativo no campo "preço" (ex.: {"preco": -10.00, "descricao": "Ticket de Evento", "data": "2025-01-15"}).
    Observe que o sistema permite a criação do ticket com preço negativo, retornando um status 201 Created ou similar.

Resultado Esperado:

    O sistema deve retornar um status 400 Bad Request, indicando que o preço do ticket não pode ser negativo.
    A mensagem de erro deve informar que o preço deve ser um valor positivo.

Resultado Atual:
O sistema cria o ticket com preço negativo, sem retornar qualquer erro de validação.

Prioridade: P1 (Alto Risco de Dados Inválidos)
Gravidade: S1 (Ameaça Crítica à Integridade dos Dados)

Correção Sugerida:

    Implementar uma validação de preço na criação de tickets para garantir que o valor seja sempre positivo.
    Retornar um erro de validação (status 400) caso o preço seja negativo.
    Adicionar testes automatizados para validar que tickets com preço negativo não podem ser cadastrados.

---

ID: BUG-012
Tipo de Bug: Estabilidade
Data do Relato: 2025-01-10
Ambiente de Teste: AWS EC2

Descrição:
Quando tento excluir um ticket na rota /ticket/{id}, a API encerra inesperadamente. Isso ocorre ao enviar uma requisição DELETE para o endpoint de exclusão, fazendo com que o servidor da API falhe e se desconecte. Isso pode ser indicativo de um problema crítico de estabilidade ou tratamento inadequado de erros na lógica de exclusão.

Passos para Reproduzir:

    Envie uma requisição DELETE para /ticket/{id}, substituindo {id} por um ID válido de ticket.
    Observe que a API encerra ou retorna um erro fatal, desconectando a requisição sem processar a exclusão do ticket.

Resultado Esperado:

    O sistema deve retornar um status 200 OK ou 204 No Content, confirmando que o ticket foi excluído com sucesso.
    A API não deve falhar ou encerrar o processo durante a execução da requisição DELETE.

Resultado Atual:
A API encerra ou falha inesperadamente quando a requisição DELETE é feita, sem realizar a exclusão do ticket.

Prioridade: P1 (Alto Risco de Instabilidade)
Gravidade: S1 (Ameaça Crítica à Estabilidade do Sistema)

Correção Sugerida:

    Investigar o motivo do encerramento da API ao processar a exclusão de tickets.
    Corrigir a lógica de exclusão para garantir que a operação seja concluída sem causar falhas no servidor.
    Adicionar testes de estabilidade para garantir que a exclusão de tickets não cause queda da API.

---