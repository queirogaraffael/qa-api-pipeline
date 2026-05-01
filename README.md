# QA API Automation Pipeline

Este repositório contém a suíte de testes e automações desenvolvida como desafio final do meu estágio na **Compass.UOL** como *Software Quality Engineer Intern* (Setembro 2024 - Fevereiro 2025).

> **Aviso Importante:** 
> - **Projeto Original:** O pipeline e código originais com todo o histórico de execuções de CI/CD encontram-se no GitLab: [api-automation](https://gitlab.com/raffaelqueiroga/cinema-api-automation).
> - **API Externa:** Este repositório foca **exclusivamente na automação de QA, CI/CD e testes**. A API testada (alocada na pasta `backend/` para facilitar a execução local dos testes) é externa e desenvolvida por terceiros. O código original da API pode ser encontrado em: [nestjs-api](https://github.com/juniorschmitz/nestjs-cinema/tree/main/src).

## Visão Geral do Projeto

O objetivo principal deste projeto foi implementar uma cultura de testes robusta e uma esteira de integração contínua (CI/CD) para uma API de gerenciamento de cinemas (filmes e ingressos). O foco esteve na garantia de qualidade, confiabilidade, análise estática e escalabilidade.

Os pilares da entrega foram:
- **Testes Automatizados:** Desenvolvimento de automação de testes funcionais e de contrato.
- **Testes de Performance:** Avaliação de escalabilidade, resposta rápida e uso eficiente de recursos.
- **Pipeline CI/CD:** Automação da execução de testes, análise de qualidade e deploy contínuo na nuvem (AWS EC2).
- **Qualidade de Código:** Identificação de débitos técnicos e inspeção contínua através de análise estática.
- **Documentação de QA:** Criação de matrizes de rastreabilidade, planos de teste, evidências de bugs e propostas de melhorias contínuas.

## Ferramentas e Tecnologias

A stack de testes e infraestrutura foi cuidadosamente selecionada para simular um ambiente corporativo de alta performance:

- **Postman & Newman:** Automação de testes de API funcionais.
- **k6 (Grafana):** Automação e execução de testes de carga e performance.
- **GitLab CI/CD:** Orquestração de toda a pipeline (build, análise estática, build Docker, deploy na EC2 e execução de testes automatizados).
- **Docker & Docker Compose:** Padronização e conteinerização do ambiente de QA e da API.
- **SonarCloud / SonarQube:** Análise estática de código (SAST) para cobertura de testes e identificação de <i>code smells</i> e bugs de segurança.
- **Node.js:** Ambiente de execução base para a API e scripts de teste.

## Estrutura do Projeto

- **`docs/`**: Contém a documentação de QA:
    - Evidências de bugs e sugestões de melhorias.
    - Mapas mentais de planejamento.
    - Testes Funcionais em BDD/Gherkin.
    - User Stories, Plano de Teste e Matriz de Rastreabilidade.
    - Relatórios de Testes Exploratórios e Performance.
- **`tests/`**: Diretório principal da suíte de automação:
    - **`k6/`**: Scripts em JavaScript e relatórios para os testes de performance.
    - **`postman/`**: Coleções, variáveis de ambiente e relatórios gerados via Newman.
- **`backend/`**: Código-fonte da API (fornecida para execução local dos testes).
- **`Dockerfile` e `docker-compose.yml`**: Infraestrutura como código para levantamento da API.
- **`.gitlab-ci.yml`**: Configuração da pipeline de CI/CD.
- **`sonar-project.properties`**: Configuração da análise de qualidade no SonarCloud.

## Como Executar Localmente

### Pré-requisitos
- [Node.js](https://nodejs.org/en/download)
- [Newman](https://www.npmjs.com/package/newman)
- [k6](https://grafana.com/docs/k6/latest/set-up/install-k6/)

### 1. Inicializando a API

Para rodar os testes, você precisa primeiro iniciar a API:

```bash
# Clone o repositório
git clone https://github.com/queirogaraffael/qa-api-pipeline.git
cd qa-api-pipeline

# Inicie o servidor da API
cd backend
npm install
npm run start
```

### 2. Executando os Testes

Com a API rodando (na porta 3000), abra um novo terminal. Cada pasta de teste possui um `README.md` dedicado com instruções detalhadas:

- **[Testes de API com Postman/Newman](./tests/postman/README.md)**
- **[Testes de Performance com k6](./tests/k6/README.md)**

---

## Configuração da Infraestrutura (Pipeline CI/CD)

Este projeto utiliza o GitLab CI/CD para automatizar a análise estática, o build e os testes automatizados. O pipeline depende de variáveis configuradas externamente e prepara dinamicamente uma instância AWS EC2.

**Qual é o intuito final do pipeline?**
A arquitetura do CI/CD foi desenhada para realizar o deploy da API (o ambiente de testes) na instância EC2 e, logo em seguida, executar os testes de integração (Postman/Newman) e de performance (k6) a partir dos próprios containers do GitLab Runner. Esses testes são configurados dinamicamente para apontar para o IP público da máquina EC2 onde a aplicação acabou de ser levantada.

### 1. Requisitos dos GitLab Runners

O pipeline usa duas `tags` para rotear os jobs:
- **Runner `docker`**: Requer o **Docker executor** configurado em modo privilegiado (necessário para o job `docker:24-dind`). É ele quem roda os testes locais, o scanner do SonarCloud, o Postman/Newman e o k6.
- **Runner `shell`**: Requer o **Shell executor**. A máquina hospedeira deste runner precisa ter `docker-compose`, `ssh` e `scp` instalados, pois eles orquestram a verificação dos arquivos YAML e o deploy via acesso SSH remoto.

### 2. Variáveis de Ambiente (CI/CD Variables)

Para que o pipeline funcione, configure as seguintes variáveis nas configurações de CI/CD do repositório no GitLab:

| Variável | Descrição |
| :--- | :--- |
| `SONAR_TOKEN` | Token de autenticação gerado no SonarCloud para análise estática. |
| `PRIVATE_KEY` | Conteúdo da chave privada `.pem` usada para conectar na AWS EC2. |
| `TEST_EC2_SSH_TARGET` | Endereço IP público ou DNS da instância EC2 (ex: `12.34.56.78`). |
| `DOCKER_HUB_USERNAME` | Seu usuário no Docker Hub para enviar e baixar a imagem. |
| `DOCKER_HUB_PASSWORD` | Token de acesso pessoal (Access Token) gerado no Docker Hub. |

### 3. Preparação do Docker Hub

- Crie um repositório chamado **`qa-api-pipeline`** em sua conta do Docker Hub.
- Caso utilize outro nome de conta, atualize a variável `IMAGE_NAME` no arquivo `.gitlab-ci.yml` e o nome da imagem no `docker-compose.yml`.

### 4. Configuração Inicial da AWS EC2

O script acessa a máquina EC2 remotamente com o usuário `ec2-user`. Antes do primeiro deploy, sua EC2 deve estar pronta:

1. **Pacotes necessários:** Instale o `docker` e o `docker-compose`. Inicie o serviço do Docker (`sudo systemctl start docker`).
2. **Permissões de usuário:** Adicione o `ec2-user` ao grupo `docker` executando `sudo usermod -aG docker ec2-user`. Assim os comandos rodam sem necessitar de `sudo`.
3. **Chave SSH:** O conteúdo público da sua `PRIVATE_KEY` deve estar presente no arquivo `~/.ssh/authorized_keys` do servidor.
4. **Security Group:** Configure as regras de entrada (Inbound rules):
   - **Porta 22 (TCP):** Liberada para conexões SSH (para o script fazer o deploy na EC2).
   - **Porta 3000 (TCP):** Liberada para permitir que os jobs de teste executem o Newman e o k6 na API recém-implantada.

---
 
## Autor
<div style="text-align: center;">
  <img alt="Raffael Queiroga" height="150" width="150" src="https://avatars.githubusercontent.com/u/117753291?v=4" style="margin-right: 20px"/>
</div>

<p>Olá! Sou <strong>Raffael Queiroga</strong>, estudante de <strong>Sistemas de Informação</strong> na <strong>Unifacisa.</strong> Antes disso, estudei <strong>Engenharia Elétrica</strong> na <strong>Universidade Federal de Campina Grande</strong> por 2,5 anos, onde desenvolvi uma base sólida em linguagens de programação como <strong>C</strong>, <strong>C++</strong> e <strong>Python</strong>.</p>

<p>Meu principal interesse está no desenvolvimento em <strong>Java</strong>, linguagem que estudo há mais de dois anos, com foco em <strong>back-end</strong> e <strong>engenharia de software</strong>. Fiz estágio na <strong>Compass.UOL</strong>, onde trabalhi em projetos voltados para <strong>AWS</strong> e <strong>Performance for Software Quality Engineering</strong>.</p>

<p>Sou de uma cidade do interior, perto de <strong>Campina Grande</strong>, e sou apaixonado por andar a cavalo e pela leitura. Além disso, possuo um bom nível de <strong>inglês</strong> e <strong>alemão</strong>, o que me permite explorar conhecimentos em diferentes idiomas.</p>