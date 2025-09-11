
# Visão Geral do Projeto

Este projeto é centrado na API **nestjs-cinema**, que gerencia filmes e ingressos. O objetivo principal foi criar e executar uma ampla gama de testes, garantindo a confiabilidade e robustez da API.

O projeto inclui:

1. Desenvolvimento de **testes automatizados** para a API utilizando diversas ferramentas e frameworks.
2. Identificação de **bugs** e proposição de melhorias contínuas.
3. **Documentação** de processos de planejamento e execução de testes, garantindo uma abordagem transparente e reutilizável.
4. Criação de uma **pipeline CI/CD** para automatizar a execução dos testes e garantir a integração contínua do código com a API.
5. **Testes de performance** para medir a escalabilidade e o desempenho da API, com ênfase em resposta rápida e uso eficiente de recursos.
6. **Refatoração e otimização** da API durante os testes, visando melhorias no código e na estrutura para maior eficiência e manutenção a longo prazo.
7. **Monitoramento e análises** dos testes ao longo do desenvolvimento, com insights sobre a saúde geral da API e recomendações de práticas de melhoria.

---

Esse projeto visa não apenas garantir que a API funcione corretamente, mas também melhorar constantemente sua robustez e desempenho, implementando uma cultura de testes e integração contínua no ciclo de desenvolvimento.

## Tecnologias Utilizadas
- **Node.js** para o desenvolvimento da API.
- **Postman** e **Newman** para automação de testes de API.
- **k6** para testes de performance.
- **GitLab CI/CD** para automação da pipeline de integração contínua.

## Objetivos Futuros
- Expandir a cobertura de testes para incluir testes de usabilidade e testes de ponta a ponta.
- Dar continuidade ao desenvolvimento da API
- Implementar monitoramento em tempo real para detectar falhas ou problemas durante a execução da API em produção.
- Melhorar a documentação dos testes e processos, tornando-os mais acessíveis para futuros desenvolvedores.



##  Estrutura do projeto
### Diretório Raiz

- **`Docs/`**: Contém a documentação do projeto:
    - **`Evidências Bug e Melhorias/`**: Relatórios detalhados de bugs e sugestões de melhorias.
    - **`Mapas Mentais/`**: Mapas mentais usados no planejamento e análise do projeto.
    - **`Testes Funcionais/Gherkin/`**: Testes funcionais descritos em formato Gherkin.
    - **`User Stories/`**: Contém as User Stories para as funcionalidades da API.
    - **`Matriz de Rastreabilidade.md`**: Documento para rastrear a cobertura de funcionalidades.
    - **`Plano de Teste.md`**: Documento de planejamento de testes.
    - **`Testes Exploratorios.md`** : Relatórios de testes exploratórios realizados.
    - **`Testes Performance.md`**: Documentação sobre testes de performance.
    - **`Bugs.md`**: Bugs encontrados na API
    - **`Melhorias.md`**: Melhorias sugeridas pra API.


- **`Tests/`**: Diretório para arquivos relacionados a testes, dividido em:
    - **`k6/`**: Testes de performance usando K6.
        - **`cinema-performance/`**:
            - **`tests/`**: Contém os códigos de testes:
                - **`movies/`**:
                    - `PF-001 - Test Create Movies.js`: Teste de criação de filmes.
                    - `PF-002 - Test List Movies.js`: Teste de listagem de filmes.
                - **`tickets/`**:
                    - `PF-003 - Ticket Life Cycle Test.js`: Teste do ciclo de vida de ingressos.
                    - `PF-004 - Tickets Performance Test.js`: Teste de performance de ingressos.
            - **`relatorios/`**: Contém os relatórios gerados pelos testes de performance.
    - **`postman/`**: Testes de API usando Postman.
        - **`collection/`**: Contém as coleção de testes do Postman.
        - **`environment/`**: Contém io arquivo de variáveis de testes do Postman.
        - **`relatorios/`**: Relatórios gerados pelos testes no Postman.
- **`nestjs-cinema/`**: Contém o código da API. A API foi construída com **NestJS** e oferece suporte a operações CRUD para:
    - Filmes (Movies)
    - Ingressos (Tickets)
- **`Dockerfile`**: Arquivo usado para criar uma imagem Docker da aplicação. Ele usa um processo de construção em múltiplas etapas com duas fases: build e runtime. A fase build instala as dependências do npm, enquanto a fase runtime copia o aplicativo já construído e o executa na porta 3000.  
- **`.gitlab-ci.yml`**: Configuração de pipeline de CI/CD para automatizar os testes e fazer o deploy da aplicação na AWS.  
- **`sonar-project.properties`**: Arquivo que configura a análise de qualidade de código do projeto com o SonarCloud. Define o nome do projeto (`nestjs-cinema`), a versão (`1.0`) e a organização (`cinema-compass`). Também especifica os diretórios de código fonte (`src`), de testes (`test`), e os caminhos para relatórios de cobertura (`coverage/lcov.info`) e do ESLint (`eslint-report.json`).  
- **`docker-compose.yml`**: Arquivo Docker Compose para levantar a API e suas dependências.  

---


## Como Executar o Projeto

### Pré-requisitos
Certifique-se de ter os seguintes programas instalados em seu sistema:

- **Node.js**
    - [Guia de Download e Instalação](https://nodejs.org/en/download)

- **Newman**
    - [Documentação Oficial do Newman](https://www.npmjs.com/package/newman)

- **k6**
    - [Guia Oficial de Instalação do k6](https://grafana.com/docs/k6/latest/set-up/install-k6/)

### Passo Inicial

1. Clone o repositório:
   ```bash
   git clone git@gitlab.com:raffaelqueiroga/cinema-api-automation.git
   cd seu-diretorio-do-repositorio-aqui
   ```

2. Instale as dependências e inicie o servidor da API:
   ```bash
   cd nestjs-cinema
   npm install
   npm run start
   ```

## Executando os Testes

Após iniciar a API, navegue até a pasta de testes específica para executar os testes. Cada pasta de teste contém um `README.md` dedicado com instruções detalhadas:

- [Testes de API com Postman](./Tests/postman/README.md)
- [Testes de Performance com k6](./Tests/k6/README.md)


## 🤝 Agradecimentos Especiais

<a href="https://gitlab.com/gizelegabriele"><img src="https://secure.gravatar.com/avatar/b94ba7dac285fb08332cd7f4001b8537ca4edd542e6995959b3f43092a0b832e?s=1600&d=identicon" width="100"></a>
<a href="https://gitlab.com/ednosilva"><img src="https://gitlab.com/uploads/-/system/user/avatar/23011785/avatar.png?width=192" width="100"></a>
<a href="https://gitlab.com/RafaBricia"><img src="https://gitlab.com/uploads/-/system/user/avatar/23014310/avatar.png?width=800" width="100"></a>
<a href="https://gitlab.com/KaioPinto"><img src="https://gitlab.com/uploads/-/system/user/avatar/23013976/avatar.png?width=800" width="100"></a>
 

 ## 👨‍💻 Autor
<div style="text-align: center;">
  <img alt="Raffael Queiroga" height="150" width="150" src="https://avatars.githubusercontent.com/u/117753291?v=4" style="margin-right: 20px"/>
</div>

<p>Olá! Sou <strong>Raffael Queiroga</strong>, estudante de <strong>Sistemas de Informação</strong> na <strong>Unifacisa</strong>, atualmente no segundo período. Antes disso, estudei <strong>Engenharia Elétrica</strong> na <strong>Universidade Federal de Campina Grande</strong> por 2,5 anos, onde desenvolvi uma base sólida em linguagens de programação como <strong>C</strong>, <strong>C++</strong> e <strong>Python</strong>.</p>

<p>Meu principal interesse está no desenvolvimento em <strong>Java</strong>, linguagem que estudo há mais de dois anos, com foco em <strong>back-end</strong> e <strong>engenharia de software</strong>. Atualmente, faço estágio na <strong>Compass.UOL</strong>, onde trabalho em projetos voltados para <strong>AWS</strong> e <strong>Performance for Software Quality Engineering</strong>.</p>

<p>Sou de uma cidade do interior, perto de <strong>Campina Grande</strong>, e sou apaixonado por andar a cavalo e pela leitura. Além disso, possuo um bom nível de <strong>inglês</strong> e <strong>alemão</strong>, o que me permite explorar conhecimentos em diferentes idiomas.</p>

---

## 💻 Stack Principal

<div>
    <img align="center" alt="Java" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg">
    <img align="center" alt="Spring" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg">
    <img align="center" alt="Docker" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg">
    <img align="center" alt="MySQL" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original.svg">
    <img align="center" alt="NoSQL" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mongodb/mongodb-original.svg">
    
</div>

---

## ☎️ Contatos

<div>
  <a href="https://www.linkedin.com/in/raffaelqueiroga/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
  <a href="https://github.com/queirogaraffael" target="_blank"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" target="_blank"></a>
  <a href="mailto:raffael.queiroga@maisunifacisa.com.br" target="_blank"><img src="https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
</div>
