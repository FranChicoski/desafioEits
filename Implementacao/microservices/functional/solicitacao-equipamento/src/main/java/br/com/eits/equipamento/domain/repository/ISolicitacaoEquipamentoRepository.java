package br.com.eits.equipamento.domain.repository;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.equipamento.domain.entity.Estado;
import br.com.eits.equipamento.domain.entity.Prioridade;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;
import br.com.eits.equipamento.domain.entity.TipoOperacao;
 

@Transactional
@Repository
public interface ISolicitacaoEquipamentoRepository extends JpaRepository<SolicitacaoEquipamento, Long> {
	/*@EntityGraph(attributePaths = {"solicitacaoEquipamento"}) possa ser que eu tenha do historico aqui*/
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query(value="SELECT s FROM SolicitacaoEquipamento s WHERE s.id = :id")
	public SolicitacaoEquipamento findById(@Param("id") Long id);	
	
	/**
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 */
	@Query(value="FROM SolicitacaoEquipamento solicitacao WHERE solicitacao.categoria.id = :categoriaId")
	public List<SolicitacaoEquipamento> findSolicitacaoByCategoriaId(@Param("categoriaId") Long categoriaId);
	
	/**
	 * 
	 * @param dataCriacao
	 * @param prioridade
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT distinct solicitacao FROM SolicitacaoEquipamento solicitacao  "+ 
			"WHERE ( "
					+"(( :dataRegistroInicial = NULL) OR ( :dataRegistroInicial = solicitacao.criacao)) "
					+"AND (( :prioridade = NULL)  OR  ( :prioridade = solicitacao.prioridade)) "+") ")
	public Page<SolicitacaoEquipamento> listTicketsByDatePriority(@Param("dataRegistroInicial") Calendar criacao, 
			@Param("prioridade") Long prioridade, 
			Pageable pageable );
	

	/**
	 * 
	 * @param filter
	 * @param tipoOperacao
	 * @param categoria
	 * @param estado
	 * @param prioridade
	 * @param dataCriacaoStart
	 * @param dataCriacaoEnd
	 * @param dataPrazoAtendimentoStart
	 * @param dataPrazoAtendimentoEnd
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT distinct solicitacao FROM SolicitacaoEquipamento solicitacao " +
				  "WHERE ( "
				  		+ "( FILTER ( solicitacao.id, :filter) = TRUE "
				  		+ "AND FILTER ( solicitacao.equipamento, :filter) = TRUE  " 
				  		+ "AND FILTER ( solicitacao.justificativa, :filter) = TRUE) "
				  		+ "AND (( :tipoOperacao = NULL)  OR  ( :tipoOperacao= solicitacao.tipoOperacao)) "
				  		+ "AND ((:categoriaId = NULL)  OR  (:categoriaId =solicitacao.categoria.id)) "
				  		+ "AND (( :estado = NULL)  OR  ( :estado = solicitacao.estado)) "
				  		+ "AND (( :prioridade = NULL)  OR  ( :prioridade = solicitacao.prioridade)) "
				  		+ "AND  ( solicitacao.criacao BETWEEN :dataRegistroInicialStart   AND   :dataRegistroInicialEnd   ) "
				  		+ "AND ( solicitacao.dataPrazoAtendimento BETWEEN  :dataRegistroAtendimentoStart AND  :dataRegistroAtendimentoEnd   ) "							
				  	+ ") "
		)

	public Page<SolicitacaoEquipamento> listByFilters(@Param("filter") String filter, 
			@Param("tipoOperacao") TipoOperacao tipoOperacao,
			@Param("categoriaId") Categoria categoria,
			@Param("estado") Estado estado,
			@Param("prioridade") Prioridade prioridade,
			@Param("dataRegistroInicialStart") Calendar dataCriacaoStart,
			@Param("dataRegistroInicialEnd") Calendar dataCriacaoEnd, 
			@Param("dataRegistroAtendimentoStart") LocalDateTime dataPrazoAtendimentoStart,
			@Param("dataRegistroAtendimentoEnd") LocalDateTime dataPrazoAtendimentoEnd,
			Pageable pageable 
			);
	
}

