package br.com.eits.categoria.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@DataTransferObject(javascript="Categoria")
@Data
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(name="CATEGORIA_EQUIPAMENTO",uniqueConstraints = {@UniqueConstraint(columnNames={"titulo"})})
public class Categoria extends AbstractEntity
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7974834759183694536L;
	
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Audited
	@NotNull
	@NotEmpty
	@Size(max=50)
	@Column(name = "titulo",length=50,nullable=false,unique= true)
	private String tituloCategoria;
	
	@Audited
	@NotNull
	@NotEmpty
	@Column(name = "descricao",columnDefinition="text", nullable=false)
	private String descricaoCategoria;
	
	@Audited
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "prioridade",nullable=false)
	private Prioridade prioridadeCategoria;
	
	

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

	public Categoria() {
		super();
	}

	public Categoria(Long id) {
		super(id);
	}

	/*-------------------------------------------------------------------
	 *  -------------------------BEHAVIORS---------------------------
	 */
	
	
}
