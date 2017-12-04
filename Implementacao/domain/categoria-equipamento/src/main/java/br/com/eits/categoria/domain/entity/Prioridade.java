package br.com.eits.categoria.domain.entity;

import org.directwebremoting.annotations.DataTransferObject;

//import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type="enum")
public enum Prioridade {
	IMEDIATA, //0
	URGENTE,  //1
	ALTA,     //2
	NORMAL,   //3
	BAIXA     //4
}
