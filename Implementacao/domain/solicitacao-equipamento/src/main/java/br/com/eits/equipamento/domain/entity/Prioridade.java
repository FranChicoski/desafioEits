package br.com.eits.equipamento.domain.entity;

import org.directwebremoting.annotations.DataTransferObject;

//import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type="enum")
public enum Prioridade {
	IMEDIATA, //0
	URGENTE,  //1
	ALTA,     //3
	NORMAL,   //4
	BAIXA     //5
}
