# Testes de Performance com k6

Este repositório contém scripts de testes de performance para os endpoints **Movies** e **Tickets** da aplicação. Os testes utilizam o [k6](https://k6.io/), uma moderna ferramenta de teste de carga de código aberto, para simular tráfego e medir o desempenho do sistema em diversas condições de carga.

---

## Configuração

Antes de executar os testes, certifique-se de ter o seguinte:

- [k6](https://k6.io/docs/getting-started/) instalado.
- Uma instância da aplicação rodando em `http://localhost:3000` (ou atualize a variável `BASE_URL` nos scripts, se necessário).

---

## Arquivos de Teste

Os scripts de teste estão organizados em diretórios baseados nos endpoints que avaliam:

### **Movies**
- **`tests/k6/qa-api-pipeline-performance/tests/movies/PF-001 - Test Create Movies.js`**  
  Testa a criação de novos filmes.

- **`tests/k6/qa-api-pipeline-performance/tests/movies/PF-002 - Test List Movies.js`**  
  Avalia a listagem de filmes existentes.

### **Tickets**
- **`tests/k6/qa-api-pipeline-performance/tests/tickets/PF-003 - Ticket Life Cycle Test.js`**  
  Realiza um ciclo completo de testes em tickets, incluindo:
  - Carga
  - Estresse
  - Pico
  - Volume
  - Resiliência
  Você pode escolher o tipo de teste utilizando uma variável de ambiente ou rodar todos os tipos de teste em paralelo.

- **`tests/k6/qa-api-pipeline-performance/tests/tickets/PF-004 - Tickets Performance Test.js`**  
  Mede o desempenho da manipulação de tickets em diferentes condições.

---

## Executando os Testes

Para executar os testes, siga estas etapas:

1. Abra um terminal e navegue até o diretório `tests/k6`.
2. Execute scripts individuais diretamente utilizando o comando:
    ```bash
    k6 run <caminho-do-script>
    ```
    Ou simplesmente vá até a pasta do script e execute o comando:
    ```bash
    k6 run <nome-do-teste>
    ```

---

## Relatórios

Os resultados de cada execução de teste serão armazenados no diretório `relatorios` no formato HTML, facilitando a análise visual.

Para gerar os relatórios, basta executar os testes.
---

## Conclusão

Esses testes ajudam a garantir o desempenho dos endpoints Movies e Tickets sob diferentes condições de carga. Ao executar testes para múltiplos estágios, é possível observar como o sistema se comporta com o aumento do tráfego e identificar possíveis gargalos ou problemas.

Para mais informações sobre o k6 e testes de carga, visite a [documentação oficial do k6](https://grafana.com/docs/k6/latest/).
