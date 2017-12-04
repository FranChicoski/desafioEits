package br.com.eits.categoria.domain.repository;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.categoria.domain.entity.Prioridade;


@Transactional
@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria,Long>
{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query(value="SELECT c FROM Categoria c WHERE c.id = :id")
	public Categoria findById(@Param("id") Long id);
	
	
	/**
	 * 
	 * @param filter
	 * @param dataCriacaoStart
	 * @param dataCriacaoEnd
	 * @param prioridade
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT  distinct categoria FROM Categoria categoria "
			+ "WHERE ( "
			+" ( FILTER (categoria.tituloCategoria, :filter) = TRUE "  
			+" AND FILTER ( categoria.descricaoCategoria, :filter) = TRUE )"
			+" AND ("
				+ " ( :dataCriacaoStart >= categoria.criacao ) OR ( :dataCriacaoStart = NULL) "
				+" AND ( ( :dataCriacaoEnds <= categoria.criacao ) OR ( :dataCriacaoEnds = NULL) ) )  " 
			+" AND ( ( :prioridade = categoria.prioridadeCategoria ) OR ( :prioridade = NULL) ) "
			+") "
	)
	public Page<Categoria> listByFilters(@Param("filter") String filter, 
			@Param("dataCriacaoStart") Calendar dataCriacaoStart,
			@Param("dataCriacaoEnds") Calendar dataCriacaoEnd,
			@Param("prioridade") Prioridade prioridade, 
			Pageable pageable );
}

