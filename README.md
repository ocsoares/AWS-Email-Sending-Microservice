# **AWS Email Sending Microservice**

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/neliocursos/exemplo-readme/blob/main/LICENSE)

# Autor

👤 Cauã Soares

💼 https://www.linkedin.com/in/ocauasoares

# Sobre o projeto

## Deploy na plataforma Render:

🚀 https://aws-email-sending-microservice.onrender.com/swagger-ui/index.html <br>

Esse é um microsserviço de **enviar email** com **filas**, desenvolvido usando **Arquitetura Limpa** e as ferramentas de
**mensageria** da **AWS** chamadas **AWS SNS** e **AWS SQS**.

⚠️ **ATENÇÃO**: O email definido no **deploy** para enviar os emails é: nodevalidation12@gmail.com

# Estrutura do projeto

![Estrutura](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/structure.png)

Esse projeto foi desenvolvido para usar as técnicas de **mensageria**, então **autenticação** nas rotas **não** foi o
foco
principal. Porém, foi implementado os **testes unitários** em todo o projeto.

## Publish e Consumer das filas

### Pastas

![Pastas do Publish e Consumer](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/folder-publish-consumer.png)

### Trechos de códigos

### Publish

![Trecho de código do Publish](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/publish-code-snippet.png)

### Consumer

![Trecho de código do Consumer](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/consumer-code-snippet.png)

### Javamail service

![Trecho de código do Javamail service](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/javamail-service.png)

## AWS SQS Queues

![AWS SQS Queues](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/aws-sqs-queues.png)

## Testes

### Trechos de códigos

### Javamail

![Teste do Javamail](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/javamail-test.png)

### AWS SQS Consumer

![Teste do AWS SQS Consumer](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/aws-sqs-consumer-test.png)

# Principais tecnologias e bibliotecas utilizadas

- Java 21
- Spring Boot 3.2
- Docker
- AWS SNS
- AWS SQS
- Javamail
- PostgreSQL
- JPA
- JUnit 5
- Swagger (documentação)

## Características e funcionalidades do projeto:

### Estrutural 🛠️

- Clean Code
- SOLID
- Clean Architecture
- PostgreSQL usado no **Docker**
  <br>
  <br>

### Funcionalidades 🎯

- O usuário pode enviar um **email** para qualquer outro email que especificar no body da requisição.
- O email que será responsável por enviar é definido por meios das **variáveis de ambiente** e passados para o serviço
  do **Javamail**.

# Documentação

Documentação feita com a ferramenta **Swagger** na rota **/swagger-ui/index.html**

![Documentação](https://raw.githubusercontent.com/ocsoares/images/master/aws-email-sending-microservice/docs.png)

# Executar o projeto localmente

Pré-requisitos: Java 21, Maven e Docker

```bash
# clonar o repositório
git clone https://github.com/ocsoares/AWS-Email-Sending-Microservice

# instalar as dependências do pom.xml

# configurar as variáveis de ambiente do arquivo application.properties

# iniciar o container do docker
docker-compose up

# buildar e executar o projeto
```