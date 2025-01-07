Feature: Cadastro e Gerenciamento de Usuários

  Como administrador ou usuário comum de um cinema,
  Quero poder me cadastrar e gerenciar meu perfil na API de Cinema,
  Para acessar as funcionalidades de acordo com meu perfil (gerenciar filmes ou reservar tickets).

  # GP-004 - Cadastro de Usuários
  
  Scenario: CT-010 - Cadastro de usuário com dados válidos
    Given que forneço os campos "nome", "email", "password" e "administrador" corretamente
    When envio uma solicitação POST para o endpoint de cadastro
    Then o usuário deve ser cadastrado com sucesso
    And devo receber um status code 201 (Criado).

  Scenario: CT-011 - Cadastro de usuário com email já utilizado
    Given que já existe um usuário cadastrado com o email "exemplo@email.com"
    And forneço os campos "nome", "email", "password" e "administrador" corretamente
    When envio uma solicitação POST para o endpoint de cadastro
    Then não devo conseguir cadastrar o usuário
    And devo receber um status code 400 (Requisição Inválida)
    And uma mensagem indicando que o email já está em uso.

  Scenario: CT-012 - Cadastro de usuário com email inválido
    Given que forneço um email inválido ("exemplo@com")
    And forneço os campos "nome", "email", "password" e "administrador" corretamente
    When envio uma solicitação POST para o endpoint de cadastro
    Then não devo conseguir cadastrar o usuário
    And devo receber um status code 400 (Requisição Inválida)
    And uma mensagem indicando que o email não segue o padrão válido.

  Scenario: CT-013 - Cadastro de usuário com senha fora do padrão
    Given que forneço uma senha com menos de 5 caracteres ou mais de 10 caracteres
    And forneço os campos "nome", "email", "password" e "administrador" corretamente
    When envio uma solicitação POST para o endpoint de cadastro
    Then não devo conseguir cadastrar o usuário
    And devo receber um status code 400 (Requisição Inválida)
    And uma mensagem indicando que a senha deve ter entre 5 e 10 caracteres.

  # GP-005 - Listagem de Usuários

  Scenario: CT-014 - Listagem de usuários com paginação
    Given que sou um administrador autenticado com um token válido
    When envio uma solicitação GET para o endpoint de listagem com os parâmetros "page" e "limit"
    Then devo receber a lista de usuários cadastrados na página especificada
    And o status code 200 (OK).

  Scenario: CT-015 - Tentativa de listar usuários sem autenticação
    Given que não sou um administrador ou não estou autenticado
    When envio uma solicitação GET para o endpoint de listagem
    Then devo receber um status code 401 (Não autorizado)
    And uma mensagem indicando que a autenticação é necessária.

  # GP-006 - Atualização de Usuários

  Scenario: CT-016 - Atualização de usuário com ID inexistente
    Given que o ID fornecido não corresponde a nenhum usuário existente
    When envio uma solicitação PUT para o endpoint de atualização com os dados do novo usuário
    Then um novo usuário deve ser criado com sucesso
    And devo receber um status code 201 (Criado).

  Scenario: CT-017 - Tentativa de editar usuário com email já utilizado
    Given que já existe um usuário cadastrado com o email "exemplo@email.com"
    And envio uma solicitação PUT para o endpoint de edição com esse mesmo email
    When os dados são enviados
    Then não devo conseguir editar o usuário
    And devo receber um status code 400 (Requisição Inválida)
    And uma mensagem indicando que o email já está em uso.
