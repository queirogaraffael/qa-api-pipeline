# Testes Exploratórios - Cinema API

## Objetivo
Investigar o comportamento da API, validar cenários documentados e identificar falhas, inconsistências ou melhorias para a aplicação.

## Modelo
- **Explorar** uma funcionalidade/componente/módulo  
- **Com** alguma estratégia/recurso/condição  
- **Para** descobrir alguma determinada informação  

---

## Endpoints de Filmes

### Endpoint: Listar Filmes (`GET /movies`)

#### Resposta Vazia ou Incompleta
- **Com**: A ausência de filmes cadastrados ou dados incompletos (como título ou descrição ausentes).  
- **Para**: Verificar como o sistema lida com respostas vazias ou incompletas e se retorna uma mensagem adequada.

#### Filtros Não Documentados
- **Com**: Tentativas de utilizar filtros como `genre`, `releaseDate`, ou `rating` na URL.  
- **Para**: Descobrir funcionalidades não documentadas ou comportamentos inesperados relacionados à listagem de filmes.  

#### Ordenação e Paginação
- **Com**: Grande volume de filmes cadastrados, com e sem paginação ou ordenação (por exemplo, ordenando por data de lançamento ou classificação).  
- **Para**: Identificar problemas de desempenho ou a ausência de ordenação/paginação no retorno.

#### Resposta Esperada
- **Código**: `200`  
- **Descrição**: Filmes listados com sucesso.

---

### Endpoint: Criar Filme (`POST /movies`)

#### Validação de Dados Obrigatórios
- **Com**: Omissão de campos obrigatórios como `title`, `description`, `launchdate` ou `showtimes`.  
- **Para**: Verificar como o sistema lida com a falta de informações obrigatórias.

#### Dados Inválidos no Corpo da Requisição
- **Com**: Envio de dados inválidos (por exemplo, `title` vazio, `launchdate` com formato incorreto ou `showtimes` com valores inválidos).  
- **Para**: Avaliar como a API valida os dados e lida com entradas incorretas.

#### Resposta Esperada
- **Código**: `201`  
- **Descrição**: Filme criado com sucesso.  
- **Código**: `400`  
- **Descrição**: Requisição inválida.

---

### Endpoint: Listar Filme por ID (`GET /movies/{id}`)

#### Consulta com ID Inválido
- **Com**: IDs que não existem ou estão em formato incorreto.  
- **Para**: Verificar o tratamento de erros e se a mensagem de erro é clara.

#### Filme Removido
- **Com**: Consulta de um filme que foi deletado.  
- **Para**: Avaliar como o sistema responde a essa situação.

#### Resposta Esperada
- **Código**: `200`  
- **Descrição**: Filme listado com sucesso.  
- **Código**: `400`  
- **Descrição**: Requisição inválida.  
- **Código**: `404`  
- **Descrição**: Filme não encontrado.

---

### Endpoint: Atualizar Filme (`PUT /movies/{id}`)

#### Atualização de Campos Específicos
- **Com**: Alteração de um único campo, como `title`, `description`, `launchdate` ou `showtimes`.  
- **Para**: Validar a atualização parcial de informações e se a API retorna o status adequado.

#### IDs Inválidos ou Inexistentes
- **Com**: IDs inexistentes ou em formato incorreto.  
- **Para**: Identificar como o sistema trata essas situações.

#### Dados Inválidos na Atualização
- **Com**: Envio de dados inválidos no corpo da requisição, como `title` vazio ou `showtimes` com formato incorreto.  
- **Para**: Testar a consistência das validações de entrada.

#### Resposta Esperada
- **Código**: `201`  
- **Descrição**: Filme atualizado com sucesso.  
- **Código**: `400`  
- **Descrição**: Requisição inválida.  
- **Código**: `404`  
- **Descrição**: Filme não encontrado.

---

### Endpoint: Deletar Filme (`DELETE /movies/{id}`)

#### IDs Inexistentes ou Inválidos
- **Com**: IDs que não existem ou estão em formato incorreto.  
- **Para**: Verificar se a API retorna mensagens claras e apropriadas.

#### Requisições Duplicadas
- **Com**: Repetição da requisição para deletar o mesmo filme.  
- **Para**: Verificar se a API gerencia corretamente a exclusão e retorna mensagens consistentes.

#### Resposta Esperada
- **Código**: `201`  
- **Descrição**: Filme excluído com sucesso.  
- **Código**: `400`  
- **Descrição**: Requisição inválida.  
- **Código**: `404`  
- **Descrição**: Filme não encontrado.

---
## Endpoints de Tickets

### Endpoint: Criação de Tickets (`POST /tickets`)

#### Validações de Campos Obrigatórios
- **Com**: Omissão de campos obrigatórios como `movieId`, `userId`, ou `seatNumber`.  
- **Para**: Identificar como o sistema responde à falta de informações essenciais.  

#### Dados Inválidos no Corpo da Requisição
- **Com**: Valores fora do esperado (`seatNumber` negativo, `price` como string, ou `showtime` com formato incorreto).  
- **Para**: Avaliar as validações de entrada e a consistência das mensagens de erro.  

#### Criar Tickets Duplicados
- **Com**: Requisições idênticas para criar tickets com mesmo `movieId`, `userId` e `seatNumber`.  
- **Para**: Verificar se a API evita duplicações e retorna mensagens apropriadas.  

---

### Endpoint: Listar Todos os Tickets (`GET /tickets`)

#### Paginação e Volume de Dados
- **Com**: Grande volume de tickets cadastrados, com e sem paginação.  
- **Para**: Identificar possíveis problemas de desempenho ou a ausência de paginação no retorno.  

#### Filtros Não Documentados
- **Com**: Tentativas de utilizar filtros como `movieId` ou `userId` na URL.  
- **Para**: Descobrir funcionalidades não documentadas ou comportamentos inesperados.  

---

### Endpoint: Listar Ticket por ID (`GET /tickets/{id}`)

#### Consulta com IDs Inválidos
- **Com**: IDs que não existem, IDs em formatos inválidos ou vazios.  
- **Para**: Verificar as mensagens de erro e o tratamento de entradas inválidas.  

#### Ticket Removido
- **Com**: Consulta de um ticket já deletado.  
- **Para**: Avaliar se o sistema retorna erro consistente e apropriado.  

---

### Endpoint: Atualizar Ticket (`PUT /tickets/{id}`)

#### Atualização de Campos Específicos
- **Com**: Alteração de um único campo, como `seatNumber` ou `price`.  
- **Para**: Validar a atualização parcial e os retornos da API.  

#### IDs Inválidos ou Inexistentes
- **Com**: IDs inexistentes ou no formato incorreto.  
- **Para**: Identificar como o sistema trata essas situações.  

#### Valores Inválidos em Atualização
- **Com**: Dados inválidos no corpo da requisição, como valores negativos para `price` ou `seatNumber`.  
- **Para**: Testar a consistência das validações de entrada.  

---

### Endpoint: Deletar Ticket (`DELETE /tickets/{id}`)

#### IDs Inexistentes ou Inválidos
- **Com**: IDs que não existem ou que não seguem o formato esperado.  
- **Para**: Avaliar se a API retorna mensagens claras e apropriadas.  

#### Requisições Duplicadas
- **Com**: Repetição da requisição para deletar o mesmo ticket.  
- **Para**: Verificar se a API gerencia corretamente a exclusão e retorna mensagens consistentes.  

---

## O que será investigado?

### Resiliência e Robustez da API
- Verificar como a API lida com entradas inválidas ou malformadas.  
- Avaliar consistência e clareza nas mensagens de erro.  

### Funcionalidades Não Documentadas
- Explorar possíveis filtros ou comportamentos adicionais nos endpoints.  

### Oportunidades de Melhoria
- Implementação de paginação nos retornos, se não existente.  
- Melhorias no manuseio de erros para entradas inválidas.  

### Segurança
- Verificar se informações sensíveis são expostas nos retornos.  
- Testar comportamentos com entradas maliciosas, como SQL Injection ou XSS.  

---

## Resultados Esperados
Durante os testes exploratórios, espera-se identificar bugs, comportamentos não documentados e possíveis melhorias para o funcionamento e a segurança da API.
