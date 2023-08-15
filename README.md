# Backend PicPay Application

Codificação em Java com Spring Boot para uma aplicação estilo do PicPay que simula um sistema de transferência entre usuários e lojistas foi implementando o uso de JWT para gerar um token de seguraça para o usuário. Foi usado banco de dados MYSQL e Docker para deixar aplicação conteinerizada.

## Pré-requisitos

- Java 11
- Maven
- Docker e Docker Compose

## Como executar

### Construir a aplicação

Primeiro, construa a aplicação usando Maven:
mvn clean package


### Usando Docker

Depois de construir a aplicação, você pode usar Docker e Docker Compose para iniciar a aplicação e o banco de dados:

docker-compose up --build


A aplicação estará disponível em [http://localhost:8080].

## Endpoints

- `/login`: Endpoint para autenticação.
- `/transaction`: Endpoint para realizar transações entre usuários.

## Testes

Para executar os testes:

mvn test


### Autor
Emerson Amorim
