package br.com.eits.categoria.domain.service;

import java.util.List;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.categoria.domain.AbstractIntegrationTests;
import br.com.eits.categoria.domain.entity.Categoria;
import br.com.eits.categoria.domain.entity.Prioridade;

public class CategoriaServiceIntegrationTests extends AbstractIntegrationTests
{
	@Autowired
	private CategoriaService categoriaService;

	@Test
	@Sql({
		"/dataset/categoria/categoria.sql"
	})
	
	
	public void testInsertCategoriaTrue() 
	{
		
		List<Categoria> listaCategoria = categoriaService.listCategoriaByFilters(null,null,null,null,null).getContent();
		Categoria categoria = new Categoria();
		categoria.setTituloCategoria("TESTE 3");
		categoria.setDescricaoCategoria("categoria teste 3");
		categoria.setPrioridadeCategoria(Prioridade.NORMAL);
		categoriaService.insertCategoria(categoria);
		Assert.assertNotNull(categoria);
		for(Categoria categoriaTeste : listaCategoria) 
		{
			System.out.println(categoriaTeste);
		}
		
	}
	
	@Test(expected=ValidationException.class)
	public void testInsertCategoriaFalse() 
	{
		List<Categoria> listaCategoria = categoriaService.listCategoriaByFilters(null,null,null,null,null).getContent();
		Categoria categoria = new Categoria();
		categoria.setTituloCategoria("Notebooks");
		categoria.setDescricaoCategoria("null");
		categoria.setPrioridadeCategoria(null);
		categoria.setCriacao(null);;
		categoriaService.insertCategoria(categoria);
		for(Categoria categoriaTeste : listaCategoria) 
		{
			System.out.println(categoriaTeste);
		}
		Assert.fail();
	}
	/**
	 * 	 
	 */
	public void testFindCategoriaByIdTrue()
	{
		Categoria categoria = this.categoriaService.findCategoriaById(502L);
		Assert.assertNotNull(categoria);
		Assert.assertNotNull(categoria.getId());
	}
	
	/**
	 * 
	 */
	@Test(expected=ValidationException.class)
	public void testFindCategoriaByIdFalse() 
	{
		Categoria categoria = this.categoriaService.findCategoriaById(300L);
		Assert.assertNotNull(categoria);
		Assert.fail();
	}
	
	/**
	 * 
	 */
	public void testUpdateCategoriaTrue() 
	{
		Categoria categoria = new Categoria();
		categoria = this.categoriaService.findCategoriaById(501L);
		categoria.setDescricaoCategoria("Teclados,mouses e Mario");
		categoria.setPrioridadeCategoria(Prioridade.BAIXA);
		this.categoriaService.updateCategoria(categoria);
		
		Assert.assertEquals("Teclados,mouses e Mario", categoria.getDescricaoCategoria());
		Assert.assertEquals(Prioridade.BAIXA, categoria.getPrioridadeCategoria());
	}
	
	/**
	 * 
	 */
	@Test(expected=ValidationException.class)
	public void testUpdateCategoriaFalse() 
	{
		final Categoria categoria = this.categoriaService.findCategoriaById(501L);
		categoria.setPrioridadeCategoria(null);
		this.categoriaService.updateCategoria(categoria);
		Assert.fail();
	}
	
}
