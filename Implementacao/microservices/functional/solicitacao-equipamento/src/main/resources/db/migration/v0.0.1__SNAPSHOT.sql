CREATE TABLE solicitacao.revisao
(
	 id BIGINT NOT NULL,
	 id_usuario BIGINT,
	"timestamp" bigint NOT NULL,
		CONSTRAINT revisao_pkey PRIMARY KEY (id)
)WITH (
 	OIDS=FALSE
);

CREATE TABLE entidade_revisao
(
	revisao BIGINT NOT NULL,
	entidade character varying(255),
	CONSTRAINT fks6354amh77svj71q8xjc2y49q FOREIGN KEY (revisao)
 		 REFERENCES solicitacao.revisao (id) MATCH SIMPLE
  		ON UPDATE NO ACTION ON DELETE NO ACTION
);
	
	
CREATE TABLE solicitacao.solicitacao_equipamento
(
  	id BIGINT NOT NULL,
   	usuario_autor BIGINT NOT NULL,
   	equipamento CHARACTER VARYING(120) NOT NULL,
  	justificativa TEXT NOT NULL,
  	observacao_finalizacao TEXT,
  	justificativa_cancelamento TEXT, 
  	justificativa_recusa TEXT,
  	tipo_operacao INTEGER NOT NULL,
  	categoria_id BIGINT NOT NULL,
  	estado_solicitacao INTEGER NOT NULL, 
  	prioridade_solicitacao INTEGER NOT NULL,
  	data_prazo_atendimento TIMESTAMP,
  	data_aprovacao TIMESTAMP,
  	data_finalizacao TIMESTAMP,
  	data_recusa TIMESTAMP,
  	data_cancelamento TIMESTAMP,
  	criacao TIMESTAMP NOT NULL,
  	atualizacao TIMESTAMP,
  	versao BIGINT ,
  	CONSTRAINT solicitacao_pkey PRIMARY KEY (id),
  	CONSTRAINT categoria_fk  FOREIGN KEY (categoria_id)
  		REFERENCES categoria.categoria_equipamento(id)
  		ON UPDATE NO ACTION ON DELETE NO ACTION,
       	 CONSTRAINT usuario_fk FOREIGN KEY (usuario_autor)
       	 	REFERENCES  usuario.usuario(id) MATCH SIMPLE
      		ON UPDATE NO ACTION ON DELETE NO ACTION
)WITH (
  OIDS=FALSE
);
 	
  	
CREATE TABLE solicitacao.solicitacao_equipamento_auditado
(
  	id BIGINT NULL,
  	revisao BIGINT NOT NULL,
  	tipo_revisao smallint,
   	usuario_autor BIGINT NOT NULL,
   	equipamento VARCHAR(120) NOT NULL,
  	justificativa TEXT NOT NULL,
  	observacao_finalizacao TEXT,
  	justificativa_cancelamento TEXT, 
  	justificativa_recusa TEXT,
  	tipo_operacao INTEGER NOT NULL,
  	categoria_id BIGINT NOT NULL,
  	estado_solicitacao INTEGER NOT NULL, 
  	prioridade_solicitacao INTEGER NOT NULL,
  	data_prazo_atendimento TIMESTAMP,
  	data_aprovacao TIMESTAMP,
  	data_finalizacao TIMESTAMP,
  	data_recusa TIMESTAMP,
  	data_cancelamento TIMESTAMP,
  	CONSTRAINT solicitacao_equipamento_auditado_pkey PRIMARY KEY (id, revisao),
    	CONSTRAINT fkjotpoo0ya6s77rju62g1h50o1 FOREIGN KEY (revisao)
        	  REFERENCES solicitacao.revisao (id) MATCH SIMPLE
      		ON UPDATE NO ACTION ON DELETE NO ACTION
  );

  CREATE TABLE solicitacao.historico_auditado
  (
  	id BIGINT NOT NULL ,
  	revisao bigint NOT NULL,
  	tipo_revisao smallint,
  	usuario_responsavel BIGINT NOT NULL,
  	estado_anterior INTEGER,
  	estado_atual INTEGER NOT NULL,
  	solicitacao_id BIGINT NOT NULL,
  	data_alteracao TIMESTAMP  NOT NULL,
  	CONSTRAINT historico_auditado_pkey PRIMARY KEY (id, revisao),
	CONSTRAINT fks6354amh77svj71q8xjc2y49q FOREIGN KEY (revisao)
  		REFERENCES solicitacao.revisao (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
  );
  
  CREATE TABLE solicitacao.historico
  (
  	id BIGINT NOT NULL ,
  	usuario_responsavel BIGINT,
  	estado_anterior INTEGER,
  	estado_atual INTEGER NOT NULL,
  	solicitacao_id BIGINT NOT NULL,
  	data_alteracao TIMESTAMP  NOT NULL,
  	criacao TIMESTAMP  NOT NULL,
  	atualizacao TIMESTAMP,	
  	versao BIGINT  NOT NULL,
  	CONSTRAINT historico_pkey PRIMARY KEY (id),
	CONSTRAINT fkd9rq6ds6dhm9e4v7n0nibdjp3  FOREIGN KEY (solicitacao_id)  
		REFERENCES  solicitacao.solicitacao_equipamento(id) 
	 	MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
  )WITH (
  OIDS=FALSE
);