# TaskFlow: Gerenciador de Tarefas Colaborativo (Backend)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white )
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white )
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white )
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white )

Um sistema backend robusto para gerenciamento de tarefas colaborativo, desenvolvido com Spring Boot. O TaskFlow permite que usuários criem projetos, gerenciem tarefas, atribuam responsabilidades e acompanhem o progresso, facilitando a organização e a colaboração em equipes.

Este projeto foca na construção de uma API RESTful escalável e segura, demonstrando boas práticas de desenvolvimento backend com Java e Spring Boot.

## 🚀 Funcionalidades Atuais (MVP)

-   **Gerenciamento de Tarefas:**
    -   Criação de novas tarefas.
    -   Listagem de todas as tarefas.
    -   *(Em breve: Visualização, edição e exclusão de tarefas individuais, associação a projetos, definição de status, prioridade e prazo.)*

## ✨ Tecnologias Utilizadas

-   **Backend:**
    -   [Spring Boot](https://spring.io/projects/spring-boot ): Framework para desenvolvimento de aplicações Java.
    -   [Java](https://www.java.com/ ): Linguagem de programação.
    -   [Spring Data JPA](https://spring.io/projects/spring-data-jpa ): Para persistência de dados e interação com o banco de dados.
    -   [Lombok](https://projectlombok.org/ ): Para reduzir código boilerplate.
-   **Banco de Dados:**
    -   [PostgreSQL](https://www.postgresql.org/ ): Banco de dados relacional.
-   **Containerização:**
    -   [Docker](https://www.docker.com/ ): Para empacotar e executar a aplicação e o banco de dados em contêineres.

## 🛠️ Como Rodar o Projeto

Para executar o TaskFlow localmente, siga os passos abaixo:

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

-   [Docker](https://docs.docker.com/get-docker/ )
-   [Docker Compose](https://docs.docker.com/compose/install/ )
-   [Java Development Kit (JDK) 17 ou superior](https://www.oracle.com/java/technologies/downloads/ )
-   [Maven](https://maven.apache.org/install.html ) 

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/DanielBR0612/TaskFlow.git
    cd TaskFlow
    ```

2.  **Configurar Variáveis de Ambiente (Opcional, mas recomendado para produção ):**
    Para desenvolvimento local, as configurações do `application.properties` (ou `application.yml`) geralmente são suficientes. Para ambientes de produção, considere usar variáveis de ambiente para credenciais do banco de dados.

### Executando com Docker Compose

Este método é o mais recomendado, pois ele irá subir o banco de dados PostgreSQL e a aplicação Spring Boot em contêineres isolados.

1.  **Construa a imagem Docker da aplicação Spring Boot:**
    Navegue até a raiz do projeto (onde está o `pom.xml` ou `build.gradle` e o `Dockerfile`) e execute:
    ```bash
    docker build -t taskflow-app .
    ```

2.  **Suba os serviços com Docker Compose:**
    Na raiz do projeto (onde está o `docker-compose.yml`), execute:
    ```bash
    docker-compose up --build
    ```
    Isso irá:
    -   Criar e iniciar o contêiner do PostgreSQL.
    -   Criar e iniciar o contêiner da aplicação `taskflow-app`.
    -   As migrações do banco de dados (se configuradas com Flyway/Liquibase) serão executadas automaticamente na inicialização da aplicação.

3.  **Acesse a API:**
    A API estará disponível em `http://localhost:8080`.


## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para sugestões de funcionalidades, relatar bugs ou enviar pull requests.


            
