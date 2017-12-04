package br.com.eits.stub.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.common.infrastructure.jersey.DateTimeWrapper;
import br.com.eits.equipamento.application.restful.ISolicitacaoResource;
import br.com.eits.equipamento.domain.entity.Estado;
import br.com.eits.equipamento.domain.entity.Historico;
import br.com.eits.equipamento.domain.entity.Prioridade;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;
import br.com.eits.equipamento.domain.entity.TipoOperacao;

@Component
@Primary
@ConditionalOnMissingBean(name = "solicitacaoService")
public class SolicitacaoResourceStub implements ISolicitacaoResource
{
	@Override
	public SolicitacaoEquipamento findSolicitacaoById( Long id )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento insertSolicitacao( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento updateSolicitacao( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento updateSolicitacaoToAprovada( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento updateSolicitacaoToCancelada( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento updateSolicitacaoToRecusada( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public SolicitacaoEquipamento updateSolicitacaoToConcluida( SolicitacaoEquipamento solicitacao )
	{
		return null;
	}

	@Override
	public Page<SolicitacaoEquipamento> listSolicitacaoByFilters( String filter, TipoOperacao tipoOperacao, Categoria categoria, Estado estado, Prioridade prioridade, DateTimeWrapper dataCriacaoStart, DateTimeWrapper dataCriacaoEnd, DateTimeWrapper dataPrazoAtendimentoStart, DateTimeWrapper dataPrazoAtendimentoEnd, PageRequest pageable )
	{
		return null;
	}

	@Override
	public List<SolicitacaoEquipamento> findSolicitacaoByCategoriaId( Long categoriaId )
	{
		return null;
	}

	@Override
	public Page<SolicitacaoEquipamento> listTicketsByDatePriority( DateTimeWrapper dataCriacao, Long prioridade, PageRequest pageable )
	{
		return null;
	}

	@Override
	public void deleteSolicitacao( Long id )
	{

	}

	@Override
	public Page<Historico> listHistoricoBySolicitcaoId( Long solicitacaoId, PageRequest pageable )
	{
		return null;
	}
}
