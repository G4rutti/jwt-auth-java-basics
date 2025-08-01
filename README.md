# API de Autenticação com JWT em Spring Boot

Este projeto é um boilerplate robusto e um estudo de caso para a criação de uma API REST com sistema de cadastro, login e endpoints protegidos utilizando JSON Web Tokens (JWT). A arquitetura segue os princípios da **Clean Architecture**, dividindo o sistema em camadas de responsabilidade para garantir alta coesão, baixo acoplamento e facilidade de manutenção e testes.

Este projeto foi construído como parte de uma intensa sessão de aprendizado e debug, representando uma jornada completa desde um CRUD simples até um sistema de segurança complexo.

## ✨ Features

- ✅ **Cadastro de Usuários:** Endpoint para registrar novos usuários com senha criptografada (BCrypt).
- ✅ **Login de Usuários:** Endpoint de autenticação que retorna um token JWT em caso de sucesso.
- ✅ **Autorização via JWT:** Endpoints protegidos que só podem ser acessados com um token JWT válido no cabeçalho `Authorization`.
- ✅ **Arquitetura Limpa:** Código organizado em camadas (`domain`, `application`, `infrastructure`) para separação de responsabilidades.
- ✅ **Tratamento de Erros:** Configuração de segurança que lida com falhas de autenticação e autorização.

## 🏛️ Arquitetura

O projeto utiliza uma abordagem baseada em Clean Architecture para organizar o código de forma clara e escalável.

- **`domain`**: A camada mais interna. Contém as entidades de negócio puras (ex: `User`) e as regras de negócio centrais. Não depende de nenhum framework. É o coração da aplicação.
- **`application`**: A camada de orquestração. Contém os "casos de uso" (os `Services`) que implementam a lógica da aplicação, conectando o domínio com a infraestrutura.
- **`infrastructure`**: A camada mais externa. Contém os detalhes de implementação e tudo que fala com o mundo exterior:
    - `web/controllers`: Os endpoints REST que expõem a API.
    - `web/dto`: Objetos de Transferência de Dados para as requisições e respostas.
    - `persistence`: As implementações dos repositórios do Spring Data JPA.
    - `security`: As configurações do Spring Security, filtros JWT e tudo relacionado à segurança.

**Regra Principal:** A dependência só pode apontar para dentro. `Infrastructure` conhece `Application`, que conhece `Domain`. O `Domain` não conhece ninguém.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **JSON Web Token (JWT)** - Biblioteca `java-jwt` da Auth0
- **MySQL** (Banco de Dados)
- **Lombok**
- **Maven**

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8 ou superior
- Uma instância do MySQL rodando
- Um cliente de API como Postman ou Insomnia

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    ```

2.  **Configure o Banco de Dados:**
    Abra o arquivo `src/main/resources/application.properties` e configure suas credenciais do MySQL.

    ```properties
    # Exemplo de configuração
    spring.datasource.url=jdbc:mysql://localhost:3306/auth_db?createDatabaseIfNotExist=true
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update

    # Chave secreta para assinar o JWT
    security.jwt.secret=sua-chave-secreta-super-longa-e-segura-aqui-12345
    ```

3.  **Compile e Rode a Aplicação:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    A aplicação estará rodando em `http://localhost:8080`.

## Endpoints da API

### Autenticação

#### 1. Registrar um Novo Usuário
- **Endpoint:** `POST /auth/register`
- **Body (JSON):**
  ```json
  {
    "name": "Seu Nome",
    "email": "seu@email.com",
    "password": "sua_senha_forte"
  }
  ```
- **Resposta de Sucesso:** `200 OK` (sem corpo)

#### 2. Realizar Login
- **Endpoint:** `POST /auth/login`
- **Body (JSON):**
  ```json
  {
    "email": "seu@email.com",
    "password": "sua_senha_forte"
  }
  ```
- **Resposta de Sucesso:** `200 OK` com o token JWT.
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

### Endpoints Protegidos

#### 1. Buscar Dados do Usuário Autenticado
- **Endpoint:** `GET /data/me`
- **Autenticação:** **Obrigatória**.
- **Como usar:**
    1.  Copie o `token` recebido na resposta do login.
    2.  Na sua requisição, vá na aba **Authorization**.
    3.  Selecione o tipo **"Bearer Token"**.
    4.  Cole o token no campo correspondente.
- **Resposta de Sucesso:** `200 OK` com a mensagem do controller.

## 🔮 Próximos Passos (Evolução do Projeto)

- [ ] Implementar validação nos DTOs de entrada (`@Valid`).
- [ ] Criar um `@ControllerAdvice` para tratamento de exceções global.
- [ ] Adicionar um endpoint para refresh de tokens.
- [ ] Criar roles de usuário mais complexas (ex: `ADMIN`, `USER`) e proteger rotas por role.
- [ ] Implementar testes unitários e de integração.
- [ ] Adicionar um mapper (ex: MapStruct) para conversão entre DTOs e Entidades.