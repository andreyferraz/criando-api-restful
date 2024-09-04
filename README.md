# Criando APIs com Spring Boot

## Descrição do Projeto

Este projeto é uma API RESTful desenvolvida usando Spring Boot, Java 17 e Hibernate. O projeto fornece um CRUD para a entidade `User`, com funcionalidades de criação, leitura, atualização e exclusão de usuários. Além disso, inclui tratamento de exceções personalizadas e testes unitários para garantir a qualidade do código.

### Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Facilita a implementação de repositórios de dados.
- **Spring Security**: Gerencia a segurança da aplicação.
- **Spring Actuator**: Fornece endpoints para monitoramento e gerenciamento.
- **SpringDoc OpenAPI**: Gera documentação da API.
- **JUnit 5**: Framework de testes.
- **Mockito**: Framework para criação de mocks e testes unitários.
- **H2 Database**: Banco de dados em memória para testes.
- **Lombok**: Reduz a quantidade de código boilerplate.

### Funcionalidades

- **POST /api/users**: Cria um novo usuário.
- **GET /api/users/{id}**: Obtém um usuário pelo ID.
- **GET /api/users**: Obtém todos os usuários.
- **PUT /api/users/{id}**: Atualiza um usuário existente.
- **DELETE /api/users/{id}**: Deleta um usuário pelo ID.

### Tratamento de Exceções

- **UserNotFoundException**: Lançada quando um usuário não é encontrado.
- **UsernameAlreadyExistsException**: Lançada quando um nome de usuário já existe.

### Compilar e Executar

Para compilar e executar o projeto, siga os seguintes passos:

1. **Clonar o Repositório**

   ```bash
   git clone https://github.com/seu-usuario/criandoapis.git
   cd criandoapis
2. **Compilar e Executar**
   ```bash
   mvn clean install
   mvn spring-boot:run

### Acessar a API

A API estará disponível em http://localhost:8080/api/users.

### Executar os Testes

Para executar os testes, use o seguinte comando:

       mvn clean install

### Documentação da API

A documentação da API está disponível em http://localhost:8080/swagger-ui/index.html através do SpringDoc OpenAPI.
