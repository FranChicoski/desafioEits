package br.com.eits.equipamento.domain.entity;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type="enum")
public enum TipoOperacao {
	MANUTENCAO,	//0
	AQUISICAO	//1
}
