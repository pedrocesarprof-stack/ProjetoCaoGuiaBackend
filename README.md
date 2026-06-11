# ProjetoCaoGuiaBackend

Backend da aplicacao Cao Guia, construido com Spring Boot, JPA, seguranca JWT e SQL Server.

## Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (jjwt)
- SQL Server (mssql-jdbc)
- Docker Compose
- OpenAPI/Swagger (springdoc)

## Estrutura principal

- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/core`: configuracoes globais, excecoes e bootstrap
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/core/security`: autenticacao/autorizacao
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/noticias`: modulo de noticias
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/treinamento`: modulo de treinamento
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/depoimento`: modulo de depoimentos
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/formularios`: modulo de formularios
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/estatisticas`: modulo de estatisticas
- `src/main/java/com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/usuarios`: modulo de usuarios

## Pre-requisitos

- Java 21 instalado e no `PATH`
- Docker Desktop ativo (para SQL Server via Compose)
- Maven Wrapper do projeto (`mvnw.cmd`)

## Como rodar em desenvolvimento

1. Suba o banco:

```powershell
docker compose up -d
```

2. Rode a aplicacao:

```powershell
.\mvnw.cmd spring-boot:run
```

## Como gerar e rodar JAR

1. Gere o pacote:

```powershell
.\mvnw.cmd -DskipTests package
```

2. Rode o JAR:

```powershell
java -jar .\target\ProjetoCaoGuiaBackend-0.0.1-SNAPSHOT.jar
```

## Configuracao

As configuracoes principais ficam em `src/main/resources/application.properties`.

Pontos importantes:

- Datasource SQL Server (`spring.datasource.*`)
- JPA/Hibernate (`spring.jpa.*`)
- JWT (`jwt.secret`, `jwt.expiration`)
- Porta HTTP (`server.port`)
- Integracao com Docker Compose do Spring (`spring.docker.compose.*`)

## API docs

Com a aplicacao em execucao, acesse o Swagger UI em:

- `http://localhost:8080/swagger-ui/index.html`

## Banco inicial

No primeiro bootstrap com tabela de usuarios vazia, o sistema cria apenas o usuario admin padrao.

Credenciais padrao:

- email: `admin@caoguia.com`
- senha: `admin123`

## Historico de mudancas

Consulte `CHANGELOG.md` para o historico versionado (SemVer) e dump de commits.

