-- master.dbo.usuarios definição

-- Drop table

-- DROP TABLE master.dbo.usuarios;

CREATE TABLE master.dbo.usuarios (
	id bigint IDENTITY(1,1) NOT NULL,
	email varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	nome varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	perfil varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	senha varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PK__usuarios__3213E83F6ED76B98 PRIMARY KEY (id),
	CONSTRAINT UKkfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email)
);
ALTER TABLE master.dbo.usuarios WITH NOCHECK ADD CONSTRAINT CK__usuarios__perfil__2E3BD7D3 CHECK (([perfil]='USER' OR [perfil]='ADMIN'));


-- master.dbo.depoimentos definição

-- Drop table

-- DROP TABLE master.dbo.depoimentos;

CREATE TABLE master.dbo.depoimentos (
	id bigint IDENTITY(1,1) NOT NULL,
	atualizado_em datetime2 NULL,
	criado_em datetime2 NOT NULL,
	descricao varchar(3000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	atualizado_por_id bigint NULL,
	criado_por_id bigint NOT NULL,
	CONSTRAINT PK__depoimen__3213E83F7890D35F PRIMARY KEY (id),
	CONSTRAINT FK4ij6bh28vftfbjyu1i91x7e6u FOREIGN KEY (criado_por_id) REFERENCES master.dbo.usuarios(id),
	CONSTRAINT FKgyc4xiluhgon2mfnd79joh8wk FOREIGN KEY (atualizado_por_id) REFERENCES master.dbo.usuarios(id)
);


-- master.dbo.estatistica_eventos definição

-- Drop table

-- DROP TABLE master.dbo.estatistica_eventos;

CREATE TABLE master.dbo.estatistica_eventos (
	id bigint IDENTITY(1,1) NOT NULL,
	criado_em datetime2 NOT NULL,
	entidade_id bigint NOT NULL,
	origem varchar(80) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tipo_entidade varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tipo_evento varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	usuario_id bigint NULL,
	CONSTRAINT PK__estatist__3213E83F64F0403E PRIMARY KEY (id),
	CONSTRAINT FKcxwfhq0he9b7b4yeqn6ao8s1w FOREIGN KEY (usuario_id) REFERENCES master.dbo.usuarios(id)
);
ALTER TABLE master.dbo.estatistica_eventos WITH NOCHECK ADD CONSTRAINT CK__estatisti__tipo___22CA2527 CHECK (([tipo_entidade]='USUARIO' OR [tipo_entidade]='FORMULARIO' OR [tipo_entidade]='TREINAMENTO' OR [tipo_entidade]='DEPOIMENTO' OR [tipo_entidade]='NOTICIA'));
ALTER TABLE master.dbo.estatistica_eventos WITH NOCHECK ADD CONSTRAINT CK__estatisti__tipo___23BE4960 CHECK (([tipo_evento]='EXCLUSAO' OR [tipo_evento]='ATUALIZACAO' OR [tipo_evento]='CRIACAO' OR [tipo_evento]='COMENTARIO' OR [tipo_evento]='COMPARTILHAMENTO' OR [tipo_evento]='DESLIKE' OR [tipo_evento]='LIKE' OR [tipo_evento]='VISUALIZACAO'));


-- master.dbo.formularios definição

-- Drop table

-- DROP TABLE master.dbo.formularios;

CREATE TABLE master.dbo.formularios (
	id bigint IDENTITY(1,1) NOT NULL,
	atualizado_em datetime2 NULL,
	categoria varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	criado_em datetime2 NOT NULL,
	data_envio datetime2 NOT NULL,
	email varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	nome varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	observacao text COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	telefone varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	atualizado_por_id bigint NULL,
	criado_por_id bigint NOT NULL,
	usuario_id bigint NULL,
	CONSTRAINT PK__formular__3213E83F00DA43DD PRIMARY KEY (id),
	CONSTRAINT FK10ngrhnjuymyb4bj952acn71b FOREIGN KEY (criado_por_id) REFERENCES master.dbo.usuarios(id),
	CONSTRAINT FKqin91uua9mu4ggech7e1p80qp FOREIGN KEY (atualizado_por_id) REFERENCES master.dbo.usuarios(id),
	CONSTRAINT FK_formularios_usuario FOREIGN KEY (usuario_id) REFERENCES master.dbo.usuarios(id)
);
ALTER TABLE master.dbo.formularios WITH NOCHECK ADD CONSTRAINT CK_formularios_categoria CHECK (([categoria]='CEGO' OR [categoria]='FAMILIA_ACOLHEDORA' OR [categoria]='SOCIALIZADORA' OR [categoria]='DOACAO'));


-- master.dbo.noticias definição

-- Drop table

-- DROP TABLE master.dbo.noticias;

CREATE TABLE master.dbo.noticias (
	id bigint IDENTITY(1,1) NOT NULL,
	atualizado_em datetime2 NULL,
	autor varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	categoria varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	conteudo text COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	criado_em datetime2 NOT NULL,
	imagem_url varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	publicado_em datetime2 NULL,
	resumo varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	status varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tags varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	titulo varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	atualizado_por_id bigint NULL,
	criado_por_id bigint NOT NULL,
	CONSTRAINT PK__noticias__3213E83F710486A8 PRIMARY KEY (id),
	CONSTRAINT FK21ppep66eofh6fbjif2xyrlms FOREIGN KEY (criado_por_id) REFERENCES master.dbo.usuarios(id),
	CONSTRAINT FKou7a61gc66rwk22n6k7sqryql FOREIGN KEY (atualizado_por_id) REFERENCES master.dbo.usuarios(id)
);
ALTER TABLE master.dbo.noticias WITH NOCHECK ADD CONSTRAINT CK__noticias__catego__2882FE7D CHECK (([categoria]='GERAL' OR [categoria]='ACESSIBILIDADE' OR [categoria]='MEIO_AMBIENTE' OR [categoria]='ECONOMIA' OR [categoria]='POLITICA' OR [categoria]='CULTURA' OR [categoria]='ESPORTE' OR [categoria]='TECNOLOGIA' OR [categoria]='EDUCACAO' OR [categoria]='SAUDE'));
ALTER TABLE master.dbo.noticias WITH NOCHECK ADD CONSTRAINT CK__noticias__status__297722B6 CHECK (([status]='ARQUIVADO' OR [status]='PUBLICADO' OR [status]='RASCUNHO'));


-- master.dbo.treinamentos definição

-- Drop table

-- DROP TABLE master.dbo.treinamentos;

CREATE TABLE master.dbo.treinamentos (
	id bigint IDENTITY(1,1) NOT NULL,
	atualizado_em datetime2 NULL,
	criado_em datetime2 NOT NULL,
	descricao varchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	etapa varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	titulo varchar(150) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	atualizado_por_id bigint NULL,
	criado_por_id bigint NOT NULL,
	CONSTRAINT PK__treiname__3213E83FC023BB1E PRIMARY KEY (id),
	CONSTRAINT FKj1nuilxsoy3d16mmmsaps7f4l FOREIGN KEY (criado_por_id) REFERENCES master.dbo.usuarios(id),
	CONSTRAINT FKjs9ihncqq3ffxgqcfev2c4ga8 FOREIGN KEY (atualizado_por_id) REFERENCES master.dbo.usuarios(id)
);