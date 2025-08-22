
# Como executar a coleção Postman com seu environment

Neste guia, vamos ensinar como executar a coleção do Postman com o seu environment e gerar um relatório usando o Newman.

## 1. Instalando o Postman

### Para Windows:

1. Acesse o [site do Postman](https://www.postman.com/downloads/).
2. Baixe o instalador para Windows.
3. Execute o instalador e siga as instruções para concluir a instalação.

### Para macOS:

1. Acesse o [site do Postman](https://www.postman.com/downloads/).
2. Baixe o instalador para macOS.
3. Abra o arquivo `.dmg` e arraste o ícone do Postman para a pasta de Aplicativos.

### Para Linux:

1. Acesse o [site do Postman](https://www.postman.com/downloads/).
2. Baixe o arquivo `.tar.gz`.
3. Extraia o arquivo e execute o Postman diretamente a partir da pasta extraída.

## 2. Importando a Coleção e Environment no Postman

1. Abra o Postman.
2. Clique em **Import** no canto superior esquerdo.
3. Selecione **Upload Files** e escolha os seguintes arquivos:
   - `nestjs-cinema.postman_collection.json` (coleção)
   - `environment-nestjs-cinema.postman_environment.json` (environment)
4. Clique em **Importar** para adicionar a coleção e o environment.

## 3. Executando a Coleção no Postman

1. Selecione a coleção que você importou no painel à esquerda.
2. Clique em **Run** no canto superior direito.
3. Selecione o environment `nestjs-cinema` no menu suspenso de environments.
4. Clique em **Start Run** para executar a coleção.

## 4. Instalando o Newman

O Newman é uma ferramenta de linha de comando para executar coleções Postman. Para instalá-lo, siga as instruções abaixo:

### Instalando o Newman via npm:

1. Certifique-se de que o [Node.js](https://nodejs.org/) esteja instalado em sua máquina.
2. Abra o terminal ou prompt de comando e execute o seguinte comando para instalar o Newman globalmente:

   ```bash
   npm install -g newman
   ```

## 5. Executando a Coleção com Newman

1. Abra o terminal ou prompt de comando.
2. Navegue até o diretório onde você salvou os arquivos `nestjs-cinema.postman_collection.json` e `environment-nestjs-cinema.postman_environment.json`.
3. Execute o comando a seguir para rodar a coleção e gerar um relatório:

   ```bash
   newman run nestjs-cinema.postman_collection.json -e environment-nestjs-cinema.postman_environment.json -r html,cli --reporter-html-export report.html
   ```

   - A opção `-e` especifica o arquivo de environment.
   - A opção `-r` especifica o formato do relatório (HTML e CLI).
   - A opção `--reporter-html-export` especifica o nome do arquivo de relatório.

4. Após a execução, o relatório será gerado no arquivo `report.html`.

## 6. Verificando o Relatório

O relatório será gerado na pasta onde você executou o comando `newman run`. Abra o arquivo `report.html` no seu navegador para ver o relatório detalhado da execução da coleção.

---

Se você tiver alguma dúvida, não hesite em entrar em contato!
