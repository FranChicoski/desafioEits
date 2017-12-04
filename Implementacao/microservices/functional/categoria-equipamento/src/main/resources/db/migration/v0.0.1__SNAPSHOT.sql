CREATE TABLE categoria.revisao
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
  entidade CHARACTER VARYING(255),
  CONSTRAINT fks6354amh77svj71q8xjc2y49q FOREIGN KEY (revisao)
  REFERENCES revisao (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE categoria.categoria_equipamento
(
  	id BIGINT   NOT NULL ,
    titulo CHARACTER VARYING (120) NOT NULL UNIQUE,
  	descricao TEXT  NOT NULL,
  	prioridade integer NOT NULL,
   	atualizacao TIMESTAMP,
   	criacao TIMESTAMP NOT NULL,
   	versao BIGINT ,
  	CONSTRAINT categoria_pkey PRIMARY KEY (id)
)WITH (
  OIDS=FALSE
);

  	
CREATE TABLE categoria.categoria_equipamento_auditado
(
  	id  BIGINT NOT NULL,
  	revisao bigint NOT NULL,
  	tipo_revisao smallint,
   	titulo CHARACTER VARYING(120),
  	descricao TEXT,
  	prioridade INTEGER,
   	 	CONSTRAINT categoria_auditado_pkey PRIMARY KEY (id, revisao),
    CONSTRAINT fkjotpoo0ya6s77rju62g1h50o1 FOREIGN KEY (revisao)
          REFERENCES categoria.revisao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  );
  
  INSERT INTO categoria.categoria_equipamento
	(id,titulo,descricao, prioridade, atualizacao ,criacao,versao )
   	VALUES (1,'Notebooks', 'Computador portátil',0, now(), now(),1),
   	(2,'Perifericos','Teclados, mouses',3, now(), '2017-10-18 10:23:54', 1),
   	(3,'Monitores','monitores',3, now(), '2017-10-26 12:30:00', 1);

CREATE SEQUENCE  categoria.revisao_id_seq
START WITH 4 INCREMENT BY 1 NO MINVALUE NO MAXVALUE
CACHE 1;   	
   	
   	
CREATE SEQUENCE  categoria.categoria_equipamento_id_seq
START WITH 4 INCREMENT BY 1 NO MINVALUE NO MAXVALUE
CACHE 1;

ALTER TABLE categoria.categoria_equipamento ALTER COLUMN id 
SET DEFAULT nextval('categoria.categoria_equipamento_id_seq'::regclass);   

ALTER TABLE categoria.revisao ALTER COLUMN id 
SET DEFAULT nextval('categoria.revisao_id_seq'::regclass);   


  