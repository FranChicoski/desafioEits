package br.com.eits.equipamento.domain.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Path;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.categoria.application.restful.ICategoriaResource;
import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.common.infrastructure.jersey.DateTimeWrapper;
import br.com.eits.equipamento.application.restful.ISolicitacaoResource;
import br.com.eits.equipamento.domain.entity.Estado;
import br.com.eits.equipamento.domain.entity.Historico;
import br.com.eits.equipamento.domain.entity.Prioridade;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;
import br.com.eits.equipamento.domain.entity.TipoOperacao;
import br.com.eits.equipamento.domain.repository.IHistoricoRepository;
import br.com.eits.equipamento.domain.repository.ISolicitacaoEquipamentoRepository;
import br.com.eits.usuario.application.restful.IUsuarioResource;
import br.com.eits.usuario.application.security.ContextHolder;
import br.com.eits.usuario.domain.entity.Usuario;

@Service
@RemoteProxy
@Transactional
@DataTransferObject 
//@Path("/solicitacaoService")
public class SolicitacaoService implements ISolicitacaoResource {

	@Autowired
	private ISolicitacaoEquipamentoRepository solicitacaoRepository;
	
	@Autowired
	private ICategoriaResource categoriaResource;
	
	@Autowired
	private IHistoricoRepository historicoRepository;
	
	
	@Autowired
	private IUsuarioResource usuarioResource; 

	
	/**
	 * 
	 */
	@Override
	public SolicitacaoEquipamento findSolicitacaoById(Long id) {
		// TODO Auto-generated method stub
		SolicitacaoEquipamento solicitacao = this.solicitacaoRepository.findById(id);
		if(solicitacao == null) 
		{
			throw new RuntimeException("Solicitação não encontrada!");
		}
		return solicitacao;
	}

	/**
	 * 
	 */
	
	@Override
	public SolicitacaoEquipamento insertSolicitacao(SolicitacaoEquipamento solicitacao) 
	{
		Boolean flag = solicitacao.validarDataAtendimento(solicitacao.getDataPrazoAtendimento());
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		Usuario autorSolicitacao = this.usuarioResource.findUsuarioById(usuarioId);
		
		if(flag==false)
		{
			solicitacao.setDataPrazoAtendimento(solicitacao.getDataPrazoAtendimento().plusDays(1));
		}
		solicitacao.setAutorSolicitacao(autorSolicitacao);
		solicitacao.setEstado(Estado.SOLICITADA);
		
		insertHistorico(solicitacao, solicitacao.getEstado(),solicitacao.getEstado(), autorSolicitacao);
		return this.solicitacaoRepository.save(solicitacao);
		
	}

/**
 * 
 */
	@Override
	public SolicitacaoEquipamento updateSolicitacao(SolicitacaoEquipamento solicitacao) {
		Assert.isTrue(solicitacao.getEstado()==Estado.SOLICITADA,"A solicitação não pode ser alterada por conta do estado em que se encontra");
		
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		Usuario autorAlteracao = this.usuarioResource.findUsuarioById(usuarioId);
		
		Boolean flag = solicitacao.validarDataAtendimento(solicitacao.getDataPrazoAtendimento());
		if(flag==false)
		{
			solicitacao.setDataPrazoAtendimento(solicitacao.getDataPrazoAtendimento().plusDays(1));
		}
		insertHistorico(solicitacao, solicitacao.getEstado(),solicitacao.getEstado(), autorAlteracao);
		return this.solicitacaoRepository.save(solicitacao);
	}

/**
 * 
 */
	@Override
	public SolicitacaoEquipamento updateSolicitacaoToAprovada(SolicitacaoEquipamento solicitacao)
	{
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		Usuario autorAlteracao = this.usuarioResource.findUsuarioById(usuarioId);
				
		final Estado estadoAnterior = solicitacao.getEstado();
		solicitacao.aprovarSolicitacao(solicitacao.getPrioridade(),solicitacao.getDataAprovacao());
		
		insertHistorico(solicitacao, estadoAnterior,solicitacao.getEstado() , autorAlteracao);
		return solicitacao;
	}
	
	/***
	 * 	 
	 */
	@Override
	public SolicitacaoEquipamento updateSolicitacaoToCancelada(SolicitacaoEquipamento solicitacao) 
	{
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		final Usuario autorAlteracao = this.usuarioResource.findUsuarioById(usuarioId);
		final Estado estadoAnterior = solicitacao.getEstado();
		
		solicitacao.cancelarSolicitacao(solicitacao.getJustificativaCancelamento());
		
		insertHistorico(solicitacao, estadoAnterior,solicitacao.getEstado(), autorAlteracao);
		return solicitacao;
	}

	/**
	 * 
	 */
	@Override
	public SolicitacaoEquipamento updateSolicitacaoToRecusada(SolicitacaoEquipamento solicitacao) 
	{
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		final Usuario autorAlteracao = this.usuarioResource.findUsuarioById(usuarioId);
		final Estado estadoAnterior = solicitacao.getEstado();
		
		solicitacao.recusarSolicitacao(solicitacao.getJustificativaRecusa());
						
		insertHistorico(solicitacao, estadoAnterior, solicitacao.getEstado(), autorAlteracao);
		return solicitacao;
	}
	/**
	 * 
	 */
	@Override
	public SolicitacaoEquipamento updateSolicitacaoToConcluida(SolicitacaoEquipamento solicitacao) 
	{
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		final Usuario autorAlteracao = this.usuarioResource.findUsuarioById(usuarioId);
		final Estado estadoAnterior = solicitacao.getEstado();			
		
		solicitacao.concluirSolicitacao(solicitacao.getObservacaoFinalizacao(), solicitacao.getDataFinalizacao());
		
		insertHistorico(solicitacao, estadoAnterior, solicitacao.getEstado(), autorAlteracao);
		return solicitacao;

	}

	/***
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SolicitacaoEquipamento> listSolicitacaoByFilters(String filter,TipoOperacao tipoOperacao, 
			Categoria categoria, Estado estado, Prioridade prioridade, DateTimeWrapper dataCriacaoStart,
			DateTimeWrapper dataCriacaoEnd, DateTimeWrapper dataPrazoAtendimentoStart, DateTimeWrapper dataPrazoAtendimentoEnd,
			PageRequest pageable) 
	{
		Calendar dataCriacaoStarts = dataCriacaoStart.toCalendar();
		Calendar dataCriacaoEnds = dataCriacaoEnd.toCalendar();
		LocalDateTime dataPrazoAtendStart = dataPrazoAtendimentoStart.toLocalDateTime();
		LocalDateTime dataPrazoAtendEnd = dataPrazoAtendimentoEnd.toLocalDateTime();
		
		Page<SolicitacaoEquipamento> solicitacoes = this.solicitacaoRepository.listByFilters(filter, tipoOperacao, categoria, estado, prioridade, 
				dataCriacaoStarts, dataCriacaoEnds, dataPrazoAtendStart, dataPrazoAtendEnd, pageable);

		for(SolicitacaoEquipamento solicitacao: solicitacoes.getContent())
		{
			solicitacao.setAutorSolicitacao(this.usuarioResource.findUsuarioById(solicitacao.getAutorSolicitacao().getId()));
			
			solicitacao.setCategoria(this.categoriaResource.findCategoriaById(solicitacao.getCategoria().getId()));
		}
		return solicitacoes;
	}
	
	/**
	 *
	 */
	@Override
	public void deleteSolicitacao(Long id) 
	{
		final Long usuarioId =  ContextHolder.getAuthenticatedUser().getId();
		SolicitacaoEquipamento solicitacao = this.solicitacaoRepository.findById(id);
		Assert.isTrue((solicitacao.validarExclusao(usuarioId, solicitacao.getEstado())), "Usuário não pode excluir a solicitação");
		this.solicitacaoRepository.delete(id);	
	}

	
	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Historico> listHistoricoBySolicitcaoId(Long solicitacaoId, PageRequest pageable) 
	{

		Page<Historico> historicos = this.historicoRepository.listHistoricoBySolicitcao(solicitacaoId, pageable);
		for(Historico historico: historicos.getContent())
		{
			historico.setUsuarioResponsavel(this.usuarioResource.findUsuarioById(historico.getUsuarioResponsavel().getId()));
		}
		return historicos;
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SolicitacaoEquipamento> listTicketsByDatePriority( DateTimeWrapper dataCriacao,Long prioridade,PageRequest pageable )
	{
		Calendar dataCriacaoInit = dataCriacao.toCalendar();
		
		Page<SolicitacaoEquipamento> solicitacoes = this.solicitacaoRepository.listTicketsByDatePriority(dataCriacaoInit, prioridade, pageable);
		for(SolicitacaoEquipamento solicitacao: solicitacoes.getContent())
		{
			solicitacao.setAutorSolicitacao(this.usuarioResource.findUsuarioById(solicitacao.getAutorSolicitacao().getId()));
			solicitacao.setCategoria(this.categoriaResource.findCategoriaById(solicitacao.getCategoria().getId()));
		}
		return solicitacoes;
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SolicitacaoEquipamento> findSolicitacaoByCategoriaId(Long categoriaId)
	{
		System.out.println(categoriaId);
		List<SolicitacaoEquipamento> listaSolicitacoes = this.solicitacaoRepository.findSolicitacaoByCategoriaId(categoriaId); 
		return listaSolicitacoes;
	}
	
	/**
	 * 
	 * @param solicitacao
	 * @param estadoAtual
	 * @param estadoAnterior
	 * @param usuarioResponsavel
	 * @return
	 */
	public Historico insertHistorico(SolicitacaoEquipamento solicitacao,Estado estadoAtual, Estado estadoAnterior,Usuario usuarioResponsavel) 
	{
		Historico historico = new Historico();
		historico.setSolicitacao(solicitacao);
		historico.setUsuarioResponsavel(usuarioResponsavel);
		historico.setEstadoAnterior(estadoAnterior);
		historico.setEstadoAtual(estadoAtual);
		this.historicoRepository.save(historico);
		return historico;
	}

	/**
	 * 
	 * @return
	 */
	public List<Estado> listEstadosSolicitacao()
	{
		List<Estado> listaEstado = Arrays.asList(Estado.values());
		return listaEstado;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Prioridade> listPrioridadeSolicitacao()
	{
		List<Prioridade> listaPrioridade = Arrays.asList(Prioridade.values());
		return listaPrioridade;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TipoOperacao> listTipoOperacaoSolicitacao()
	{
		List<TipoOperacao> listaTipoOperacao = Arrays.asList(TipoOperacao.values());
		return listaTipoOperacao;
	}
	
}
