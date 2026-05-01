Feature: API de Reservas de Cinema

  Como um cliente autenticado que deseja assistir a um filme no cinema
  Quero reservar ingressos pela API de Tickets
  Para garantir meu lugar na sessão desejada

  Background:
    Given que o cliente está autenticado

  # GP-011 - Cenários para criação de reservas de ingressos

  Scenario: CT-027 -  Criar reserva de ingresso com sucesso
    Given que o cliente está autenticado
    And os dados do ticket são "movieId": "12345", "userId": "67890", "seatNumber": 10, "price": 50, "showtime": "2025-01-06T19:30:00Z"
    When o cliente envia uma requisição POST para a rota "http://localhost:3000/tickets"
    Then o sistema deve retornar o status code 201
    And a mensagem de resposta deve ser "Reserva criada com sucesso."

  Scenario: CT-028 - Criar reserva com assento indisponível
    Given que o cliente está autenticado
    And o assento "seatNumber": 10 já está reservado
    When o cliente envia uma requisição POST para a rota "http://localhost:3000/tickets"
    Then o sistema deve retornar o status code 400
    And a mensagem de resposta deve ser "Assento indisponível."

  Scenario: CT-029 - Criar reserva com dados inválidos
    Given que o cliente está autenticado
    And os dados do ticket são "movieId": "", "userId": "", "seatNumber": -1, "price": -10, "showtime": ""
    When o cliente envia uma requisição POST para a rota "http://localhost:3000/tickets"
    Then o sistema deve retornar o status code 400
    And a mensagem de resposta deve ser "Requisição inválida."

  # GP-012 - Cenários para listagem de reservas de ingressos

  Scenario: CT-030 - Listar reservas de ingressos com sucesso
    Given que o cliente está autenticado
    When o cliente envia uma requisição GET para a rota "http://localhost:3000/tickets?page=1&limit=10"
    Then o sistema deve retornar o status code 200
    And a resposta deve incluir uma lista paginada de tickets com os atributos "movieId", "userId", "seatNumber", "price", "showtime" e "id"

  Scenario: CT-031 - Buscar reserva por ID com sucesso
    Given que o cliente está autenticado
    And o ticket com "id": "abc123" existe
    When o cliente envia uma requisição GET para a rota "http://localhost:3000/tickets/abc123"
    Then o sistema deve retornar o status code 200
    And a resposta deve incluir os detalhes do ticket com "id": "abc123"

  Scenario: CT-032 - Buscar reserva por ID inexistente
    Given que o cliente está autenticado
    And o ticket com "id": "inexistente123" não existe
    When o cliente envia uma requisição GET para a rota "http://localhost:3000/tickets/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Ticket não encontrado."

  # GP-013 - Cenários para atualização de reservas de ingressos

  Scenario: CT-033 - Atualizar reserva de ingresso com sucesso
    Given que o cliente está autenticado
    And o ticket com "id": "abc123" existe
    And os novos dados do ticket são "movieId": "54321", "userId": "09876", "seatNumber": 15, "price": 60, "showtime": "2025-01-07T21:00:00Z"
    When o cliente envia uma requisição PUT para a rota "http://localhost:3000/tickets/abc123"
    Then o sistema deve retornar o status code 200
    And a mensagem de resposta deve ser "Reserva atualizada com sucesso."

  Scenario: CT-034 - Atualizar reserva inexistente
    Given que o cliente está autenticado
    And o ticket com "id": "inexistente123" não existe
    And os novos dados do ticket são "movieId": "54321", "userId": "09876", "seatNumber": 15, "price": 60, "showtime": "2025-01-07T21:00:00Z"
    When o cliente envia uma requisição PUT para a rota "http://localhost:3000/tickets/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Ticket não encontrado."

  # GP-014 - Cenários para exclusão de reservas de ingressos

  Scenario: CT-035 - Excluir reserva de ingresso com sucesso
    Given que o cliente está autenticado
    And o ticket com "id": "abc123" existe
    When o cliente envia uma requisição DELETE para a rota "http://localhost:3000/tickets/abc123"
    Then o sistema deve retornar o status code 200
    And a mensagem de resposta deve ser "Reserva deletada com sucesso."

  Scenario: CT-036 - Excluir reserva inexistente
    Given que o cliente está autenticado
    And o ticket com "id": "inexistente123" não existe
    When o cliente envia uma requisição DELETE para a rota "http://localhost:3000/tickets/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Ticket não encontrado."


# GP-015 - Cenários para verificação de limites

Scenario: CT-040 - Verificar número do assento fora do intervalo
  Given que o usuário está autenticado como "admin"
  And os dados do filme incluem um número de assento "seatNumber": 100
  When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
  Then o sistema deve retornar o status code 400
  And a mensagem de resposta deve ser "Número do assento inválido, deve estar entre 0 e 99."

Scenario: CT-041 - Verificar número do assento dentro do intervalo
  Given que o usuário está autenticado como "admin"
  And os dados do filme incluem um número de assento "seatNumber": 45
  When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
  Then o sistema deve retornar o status code 201
  And a mensagem de resposta deve ser "Filme criado com sucesso."

Scenario: CT-042 - Verificar preço do ingresso fora do intervalo
  Given que o usuário está autenticado como "admin"
  And os dados do filme incluem um preço de ingresso "ticketPrice": 65
  When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
  Then o sistema deve retornar o status code 400
  And a mensagem de resposta deve ser "Preço do ingresso inválido, deve estar entre 0 e 60."

Scenario: CT-043 - Verificar preço do ingresso dentro do intervalo
  Given que o usuário está autenticado como "admin"
  And os dados do filme incluem um preço de ingresso "ticketPrice": 45
  When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
  Then o sistema deve retornar o status code 201
  And a mensagem de resposta deve ser "Filme criado com sucesso."
