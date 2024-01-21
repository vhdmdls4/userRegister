# Projeto CRUD de Usuários e Transações

## Descrição
Este projeto em desenvolvimento utiliza Spring Boot, Maven, MySQL, Lombok e JPA para criar um CRUD de usuários. Além disso, está em progresso a implementação de funcionalidades para realizar transações entre usuários.

## Pré-requisitos
Antes de começar, certifique-se de ter instalado:
- Java Development Kit (JDK)
- Maven
- MySQL Server

## Configuração do Banco de Dados
1. Crie um banco de dados no MySQL com o nome `crud_usuarios`.
2. Configure as credenciais no arquivo `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud_usuarios
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
