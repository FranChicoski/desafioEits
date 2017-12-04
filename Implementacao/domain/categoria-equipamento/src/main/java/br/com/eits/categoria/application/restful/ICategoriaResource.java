package br.com.eits.categoria.application.restful;

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
import br.com.eits.categoria.domain.entity.Prioridade;
import br.com.eits.common.infrastructure.jersey.DateTimeWrapper;


@Component
@Path("/categoria")
@FeignClient("categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ICategoriaResource {

		/**
		 * 
		 * @param id
		 * @return
		 */
		@GET
		@Path("/findCategoriaBy/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Categoria findCategoriaById( @PathParam("id") Long id );
		
		/**
		 * 
		 * @param Categoria
		 * @return Categoria
		 */
		@POST
		@Path("/insertCategoria")
		@Consumes(MediaType.APPLICATION_JSON)
		public Categoria insertCategoria( Categoria categoria );
		
		/**
		 * 
		 * @param categoria
		 * @return
		 */
		@PUT
		@Path("/updateCategoria")
		@Consumes(MediaType.APPLICATION_JSON)
		public Categoria updateCategoria( Categoria categoria );

		/**
		 * 
		 * @param filter
		 * @param dataCriacaoStart
		 * @param dataCriacaoEnd
		 * @param prioridade
		 * @param pageable
		 * @return
		 */
		@GET
		@Path("/listCategoria")
		@Produces(MediaType.APPLICATION_JSON)
		public Page<Categoria> listCategoriaByFilters( @QueryParam( "filter" ) String filter, 
												  @QueryParam( "dataCriacaoStart" ) DateTimeWrapper dataCriacaoStart, 
												  @QueryParam("dataCriacaoEnd")  DateTimeWrapper dataCriacaoEnd,
												  @QueryParam( "prioridade" ) Prioridade prioridade,
												  PageRequest pageable );	
		
		
		/**
		 * 
		 * @param id
		 */
		@DELETE
		@Path("/deleteCategoria/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public void deleteCategoria(@PathParam("id") Long id );
		
}
