package br.com.eits.equipamento.application.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.equipamento.domain.entity.Estado;
import br.com.eits.equipamento.domain.entity.Historico;
import br.com.eits.equipamento.domain.entity.Prioridade;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;
import br.com.eits.equipamento.domain.entity.TipoOperacao;
import br.com.eits.common.infrastructure.jersey.DateTimeWrapper;



@Component
@Path("/solicitacao")
@FeignClient("solicitacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ISolicitacaoResource {
		
	/**
	 * 
	 * @param id
	 * @return SolicitacaoEquipamento
	 */
	@GET
	@Path("/findSolicitacaoBy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento findSolicitacaoById(@PathParam("id") Long id);
		
	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@POST
	@Path("/insertSolicitacao")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento insertSolicitacao(SolicitacaoEquipamento solicitacao);

	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PUT
	@Path("/updateSolicitacao")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento updateSolicitacao(SolicitacaoEquipamento solicitacao);
	
	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PUT
	@Path("/updateToAprovada")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento updateSolicitacaoToAprovada(SolicitacaoEquipamento solicitacao);
	
	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PUT
	@Path("/updateToCanceladaRecusada")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento updateSolicitacaoToCancelada(SolicitacaoEquipamento solicitacao);

	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PUT
	@Path("/updateToRecusada")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento updateSolicitacaoToRecusada(SolicitacaoEquipamento solicitacao);
	
	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PUT
	@Path("/updateToConcluida")
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoEquipamento updateSolicitacaoToConcluida(SolicitacaoEquipamento solicitacao);
	
	/**
	 * 
	 * @param filter
	 * @param equipamento
	 * @param justificativa
	 * @param tipoOperacao
	 * @param categoria
	 * @param estado
	 * @param prioridade
	 * @param dataCriacao
	 * @param dataRegistroFinal
	 * @param dataPrazoAtendimento
	 * @param dataFinalizacao
	 * @param pageable
	 * @return
	 */
	@GET
	@Path("/listSolicitacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<SolicitacaoEquipamento> listSolicitacaoByFilters(@QueryParam( "filter" ) String filter, 
			  @QueryParam( "tipoOperacao" )TipoOperacao tipoOperacao,
			  @QueryParam("categoria")Categoria categoria,
			  @QueryParam("estado")Estado estado,
			  @QueryParam("prioridade") Prioridade prioridade,
			  @QueryParam("dataRegistroInicial") DateTimeWrapper dataCriacaoStart,
			  @QueryParam("dataRegistroFinal") DateTimeWrapper dataCriacaoEnd,
			  @QueryParam("dataPrazoInicial") DateTimeWrapper dataPrazoAtendimentoStart,
			  @QueryParam("dataPrazoFinal") DateTimeWrapper dataPrazoAtendimentoEnd,
			  PageRequest pageable);


/**
 * 
 * @param categoriaId
 * @return
 */
	@GET
	@Path("/getSolicitacaoCategoria/{categoriaId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SolicitacaoEquipamento> findSolicitacaoByCategoriaId( 
			  @PathParam( "categoriaId" ) Long categoriaId);
	/**
	 * 	
	 * @param dataCriacao
	 * @param prioridade
	 * @param pageable
	 * @return
	 */
	
	@GET
	@Path("/getSolicitacaoData")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<SolicitacaoEquipamento> listTicketsByDatePriority(@QueryParam("dataRegistroInicial") DateTimeWrapper dataCriacao,
			@QueryParam("prioridade") Long prioridade, 
			PageRequest pageable );
	
	/**
	 * 
	 * @param id
	 */
	@DELETE
	@Path("/deleteSolicitacao/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteSolicitacao(@PathParam("id") Long id);
	
	/**
	 * 
	 * @param solicitacaoId
	 * @param pageable
	 * @return
	 */
	@GET
	@Path("/getHistoricoSolicitacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Historico> listHistoricoBySolicitcaoId(@QueryParam("SolicitacaoId") Long solicitacaoId,
												PageRequest pageable );
	
}
