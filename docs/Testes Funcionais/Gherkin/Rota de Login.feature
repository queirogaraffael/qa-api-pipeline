Feature: Autenticação de Usuários na API

  Como um usuário ou administrador com cadastro já realizado
  Quero me autenticar na API de Cinema
  Para acessar as funcionalidades correspondentes ao meu perfil

  Background: Configuração inicial de Login
  Given que o endpoint de login está disponível

  # GP-001 - Cenários de autenticação bem-sucedida

  Scenario: CT-001 - Autenticar usuário com sucesso
    Given que o usuário está cadastrado com "email": "usuario@cinema.com" e "senha": "SenhaSegura123"
    When o usuário envia uma requisição POST para a rota "http://localhost:3000/auth/login" com os dados "email": "usuario@cinema.com" e "senha": "SenhaSegura123"
    Then o sistema deve retornar o status code 200
    And a resposta deve conter um token Bearer válido

  Scenario: CT-002 - Autenticar administrador com sucesso
    Given que o administrador está cadastrado com "email": "admin@cinema.com" e "senha": "Admin123"
    When o administrador envia uma requisição POST para a rota "http://localhost:3000/auth/login" com os dados "email": "admin@cinema.com" e "senha": "Admin123"
    Then o sistema deve retornar o status code 200
    And a resposta deve conter um token Bearer válido

  # GP-002 - Cenários de autenticação falha

  Scenario: CT-003 - Autenticar usuário com email não cadastrado
    Given que o email "naoexiste@cinema.com" não está cadastrado
    When o usuário envia uma requisição POST para a rota "http://localhost:3000/auth/login" com os dados "email": "naoexiste@cinema.com" e "senha": "Senha123"
    Then o sistema deve retornar o status code 401
    And a mensagem de resposta deve ser "Usuário ou senha inválidos."

  Scenario: CT-004 - Autenticar usuário com senha incorreta
    Given que o usuário está cadastrado com "email": "usuario@cinema.com" e "senha": "SenhaSegura123"
    When o usuário envia uma requisição POST para a rota "http://localhost:3000/auth/login" com os dados "email": "usuario@cinema.com" e "senha": "SenhaErrada"
    Then o sistema deve retornar o status code 401
    And a mensagem de resposta deve ser "Usuário ou senha inválidos."

  Scenario: CT-005 - Autenticar usuário com dados ausentes
    Given que o usuário está cadastrado com "email": "usuario@cinema.com" e "senha": "SenhaSegura123"
    When o usuário envia uma requisição POST para a rota "http://localhost:3000/auth/login" sem os dados obrigatórios
    Then o sistema deve retornar o status code 400
    And a mensagem de resposta deve ser "Requisição inválida."

  # GP-003 - Cenários de autorização com token

  Scenario: CT-006 - Usuário comum acessando rota protegida de tickets
    Given que o usuário está autenticado com um token Bearer válido
    When o usuário envia uma requisição GET para a rota "http://localhost:3000/tickets"
    Then o sistema deve retornar o status code 200

  Scenario: CT-007 - Administrador acessando rota protegida de filmes
    Given que o administrador está autenticado com um token Bearer válido
    When o administrador envia uma requisição POST para a rota "http://localhost:3000/movies" com os dados do filme
    Then o sistema deve retornar o status code 201

  Scenario: CT-008 - Acesso a rota protegida sem token
    When um usuário envia uma requisição GET para a rota "http://localhost:3000/tickets" sem fornecer um token Bearer
    Then o sistema deve retornar o status code 401
    And a mensagem de resposta deve ser "Token não fornecido."

  Scenario: CT-009 - Acesso a rota protegida com token inválido
    Given que o usuário está autenticado com um token Bearer inválido
    When o usuário envia uma requisição GET para a rota "http://localhost:3000/tickets"
    Then o sistema deve retornar o status code 401
    And a mensagem de resposta deve ser "Token inválido."
