package br.com.eits.equipamento.domain.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.equipamento.domain.AbstractIntegrationTests;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;
import br.com.eits.usuario.application.security.ContextHolder;
import br.com.eits.usuario.domain.entity.Usuario;

public class EquipamentoServiceIntegrationTests extends AbstractIntegrationTests
{
	/*
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@Test
	@Sql({
		"/dataset/solicitacao/equipamento.sql"
	})
	
	public void testInsertSolicitacao() 
	{
		List<SolicitacaoEquipamento> listaSolicitacao = solicitacaoService.listSolicitacaoByFilters(null,null, 
				null, null,null, null, null,	null, null,null).getContent();
	}
	*/
	/*
	private SolicitacaoEquipamento getSolicitacao() 
	{
		SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		Categoria categoriaTeste = new Categoria();
		Usuario usuario = ContextHolder.getAuthenticatedUser();
		
		
		solicitacao.setAutorSolicitacao(usuario);
		solicitacao.setEquipamento("Mouse");
		solicitacao.setJustificativa("Scroll do mouse não funciona mais");
		solicitacao.setJustificativaCancelamento(null);
		solicitacao.setJustificativaRecusa(null);
		solicitacao.setCategoria(null);
		solicitacao.setObservacaoFinalizacao();
		
		return null;
	}
*/
}
