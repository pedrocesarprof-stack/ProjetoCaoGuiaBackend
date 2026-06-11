# Changelog

Este arquivo segue o padrao Semantic Versioning (SemVer).

Formato de versao:

- `MAJOR`: quebra de compatibilidade
- `MINOR`: novas funcionalidades compativeis
- `PATCH`: correcao e ajustes sem quebra

## [0.5.0] - 2026-06-11

### Added

- Vinculacao automatica de Usuario em todas as entidades (Noticia, Depoimento, Treinamento, Formulario).
- Campo `criadoPor` adicionado em todas as entidades para rastreabilidade.
- Campo `atualizadoPor` adicionado em todas as entidades para auditoria.
- Validacao de propriedade: usuarios somente podem editar/excluir seus proprios registros.
- Administradores podem gerenciar todos os registros do sistema.

### Changed

- DTOs atualizados para incluir informacoes do usuario (ID, nome, email).
- Services refatorados para obter usuario autenticado via `AuthenticationFacade`.
- Campo `autor` removido da entidade Noticia (agora usa relacao com Usuario).
- Endpoints de criacao nao requerem mais `usuarioId` no body (automatico via token JWT).

### Fixed

- Corrigida seguranca: usuarios nao podem mais modificar conteudo de outros usuarios.
- Melhorada auditoria: todas as operacoes registram quem criou e quem atualizou.

### Technical

- Implementado padrao de auditoria com `@PrePersist` e `@PreUpdate`.
- Adicionado `AuthenticationFacade` para abstrair obtencao do usuario autenticado.
- Relacionamento `@ManyToOne` com Usuario em todas as entidades principais.

## [0.4.0] - 2026-06-11

### Added

- Atualizacao da configuracao de banco para SQL Server.
- Integracao/ajuste de Docker Compose para ambiente local.

### Changed

- Ajustes de infraestrutura para execucao da aplicacao com banco externo.

Commits:

- `efb8a247` feat: Atualizado configuracao do banco de dados para SQL Server e Docker Compose

## [0.3.0] - 2026-06-11

### Added

- Collection Postman do projeto.
- Camada de seguranca de usuarios.
- Modulo de estatisticas e padronizacao no Swagger.

### Changed

- Melhorias na organizacao de contrato/documentacao de endpoints.

### Removed

- Remocao de collection antiga/datada.

Commits:

- `13f1a37c` feat: Criado collection do postman.
- `aff65777` feat: Implementado seguranca de usuarios.
- `90ae6bc3` feat: Implementado estatisticas e atualizado swagger para padronizacao.
- `81c49cdb` feat: Implementado estatisticas e atualizado swagger para padronizacao.
- `4711d70d` delete: Removido collection datada.
- `298318d6` delete: Removido collection datada.

## [0.2.0] - 2026-06-09

### Added

- Modulos de formulario e usuario.
- Modulo de depoimento.
- Modulo de treinamento.
- Evolucoes de Swagger.

### Changed

- Merge de atualizacoes da branch remota principal.

Commits:

- `a5116447` Swagger
- `c5702931` Swagger
- `82c447a6` Merge remote-tracking branch 'origin/master'
- `930dceb6` Formulario e Usuario
- `b3e2a2c0` Depoimento
- `e293a70b` Treinamento

## [0.1.0] - 2026-05-06

### Added

- Estrutura inicial do projeto.

Commits:

- `eb97e01f` Initial Commit

---
