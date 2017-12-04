package br.com.eits.equipamento.domain.entity;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type="enum")
public enum Estado {
	SOLICITADA,		//0
	APROVADA,		//1
	RECUSADA,		//2
	CANCELADA,		//3
	CONCLUIDA		//4
}
