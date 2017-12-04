package br.com.eits.categoria.domain.service;

import java.util.Calendar;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.categoria.application.restful.ICategoriaResource;
import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.categoria.domain.entity.Prioridade;
import br.com.eits.categoria.domain.repository.ICategoriaRepository;
import br.com.eits.common.infrastructure.jersey.DateTimeWrapper;
import br.com.eits.equipamento.application.restful.ISolicitacaoResource;
import br.com.eits.equipamento.domain.entity.SolicitacaoEquipamento;

@RemoteProxy
@Service
@Transactional
//@Path("/categoriaService")
public class CategoriaService implements ICategoriaResource
{

	/*--------------------ATRIBUTES-------------------------------
	 * ---------------------------------------------------*/

	/**
	 *
	 */
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	
	@Autowired
	private ISolicitacaoResource solicitacaoResource;
	
/**
 * 
 */
	public Categoria insertCategoria(Categoria categoria1)
	{
		Assert.notNull( categoria1, "categoria não pode ser nula");
		return this.categoriaRepository.save(categoria1);
	}

	/**
	 * 
	 */
	@Override
	public Categoria findCategoriaById(Long id) {
		Assert.notNull(id, "Código nulo");
		Categoria categoria = this.categoriaRepository.findById(id);
		if(categoria == null) 
		{
			throw new RuntimeException("Categoria não encontrada!");
		}
		return categoria;
	}
	
/**
 * 	
 */
	@Override
	public Categoria updateCategoria(Categoria categoria) 
	{
		Assert.notNull(categoria, "Categoria Vazia");
		Assert.notNull(categoria.getId(), "Id nulo");
		return this.categoriaRepository.save(categoria);
	}


	/**
	 * 
	 */
	@Override
	public Page<Categoria> listCategoriaByFilters(String filter, DateTimeWrapper dateCriacaoStart , 
			DateTimeWrapper dateCriacaoEnd, Prioridade prioridade, PageRequest  pageable) 
	{	
			System.out.println(dateCriacaoStart);
			System.out.println(dateCriacaoEnd);
			Calendar dataCriacaoStart = dateCriacaoStart != null ? dateCriacaoStart.toCalendar() : null;
			Calendar dataCriacaoEnds = dateCriacaoEnd != null ? dateCriacaoEnd.toCalendar() : null;
			return this.categoriaRepository.listByFilters(filter, dataCriacaoStart, dataCriacaoEnds, prioridade, pageable); 
	}
	
	/**
	 * 
	 */
	
	@Override
	public void deleteCategoria(Long id)
	{
		System.out.println(id);
		Assert.notNull( id, "Id de categoria nulo" );
		//List<SolicitacaoEquipamento> listaSolicitacao = this.solicitacaoResource.findSolicitacaoByCategoriaId(id);
		//Assert.isTrue(listaSolicitacao.isEmpty(),"Categoria não pode ser deletada"); 
		this.categoriaRepository.delete(id);
		}

}
