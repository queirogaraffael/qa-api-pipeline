Feature: Gerenciamento de Filmes na API de Cinema

  Como um administrador de um cinema
  Quero gerenciar os filmes na API de Cinema
  Para poder criar, listar, atualizar e excluir filmes disponíveis

  # GP-007 - Cenários para a criação de filmes

  Scenario: CT-018 - Criar filme com sucesso
    Given que o usuário está autenticado como "admin"
    And os dados do filme são "title": "Inception", "director": "Christopher Nolan", "genre": "Sci-Fi", "releaseDate": "2010-07-16", "duration": 148
    When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
    Then o sistema deve retornar o status code 201
    And a mensagem de resposta deve ser "Filme criado com sucesso."

  Scenario: CT-019 - Criar filme com dados inválidos
    Given que o usuário está autenticado como "admin"
    And os dados do filme são "title": "", "director": "", "genre": "", "releaseDate": "", "duration": -1
    When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies"
    Then o sistema deve retornar o status code 400
    And a mensagem de resposta deve ser "Requisição inválida."

  # GP-008 - Cenários para listagem de filmes

  Scenario: CT-020 - Listar todos os filmes com sucesso
    Given que o usuário está autenticado como "admin" ou "user"
    When o administrador envia uma requisição GET para a rota "http://localhost:3000/movies"
    Then o sistema deve retornar o status code 200
    And a resposta deve incluir uma lista de filmes com os atributos "title", "director", "genre", "releaseDate", "duration" e "id"

  Scenario: CT-021 - Listar filme por ID com sucesso
    Given que o usuário está autenticado como "admin" ou "user"
    And o filme com "id": "abc123" existe
    When o administrador envia uma requisição GET para a rota "http://localhost:3000/movies/abc123"
    Then o sistema deve retornar o status code 200
    And a resposta deve incluir os detalhes do filme com "id": "abc123"

  Scenario: CT-022 - Listar filme por ID inexistente
    Given que o usuário está autenticado como "admin" ou "user"
    And o filme com "id": "inexistente123" não existe
    When o administrador envia uma requisição GET para a rota "http://localhost:3000/movies/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Filme não encontrado."

  # GP-009 - Cenários para atualização de filmes

  Scenario: CT-023 - Atualizar filme com sucesso
    Given que o usuário está autenticado como "admin"
    And o filme com "id": "abc123" existe
    And os novos dados do filme são "title": "Interstellar", "director": "Christopher Nolan", "genre": "Sci-Fi", "releaseDate": "2014-11-07", "duration": 169
    When o administrador envia uma requisição PUT para a rota "http://localhost:3000/movies/abc123"
    Then o sistema deve retornar o status code 200
    And a mensagem de resposta deve ser "Filme atualizado com sucesso."

  Scenario: CT-024 - Atualizar filme inexistente
    Given que o usuário está autenticado como "admin"
    And o filme com "id": "inexistente123" não existe
    And os novos dados do filme são "title": "Interstellar", "director": "Christopher Nolan", "genre": "Sci-Fi", "releaseDate": "2014-11-07", "duration": 169
    When o administrador envia uma requisição PUT para a rota "http://localhost:3000/movies/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Filme não encontrado."

  # GP-010 - Cenários para exclusão de filmes

  Scenario: CT-025 - Excluir filme com sucesso
    Given que o usuário está autenticado como "admin"
    And o filme com "id": "abc123" existe
    When o administrador envia uma requisição DELETE para a rota "http://localhost:3000/movies/abc123"
    Then o sistema deve retornar o status code 200
    And a mensagem de resposta deve ser "Filme deletado com sucesso."

  Scenario: CT-026 - Excluir filme inexistente
    Given que o usuário está autenticado como "admin"
    And o filme com "id": "inexistente123" não existe
    When o administrador envia uma requisição DELETE para a rota "http://localhost:3000/movies/inexistente123"
    Then o sistema deve retornar o status code 404
    And a mensagem de resposta deve ser "Filme não encontrado."

    Scenario: CT-039 - Tentar excluir filme com tickets associados
    Given que o usuário está autenticado como "admin"
    And o filme com "id": "xyz789" existe
    And o filme com "id": "xyz789" tem tickets associados
    When o administrador envia uma requisição DELETE para a rota "http://localhost:3000/movies/xyz789"
    Then o sistema deve retornar o status code 400
    And a mensagem de resposta deve ser "Não é possível excluir o filme, pois ele tem tickets associados."
