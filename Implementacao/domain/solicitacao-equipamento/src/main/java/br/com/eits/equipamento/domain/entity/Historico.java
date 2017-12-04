package br.com.eits.equipamento.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.com.eits.usuario.domain.entity.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "historico")
@EqualsAndHashCode( callSuper=true)
@DataTransferObject(javascript="Historico")
public class Historico extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8451653983426329581L;
	/**
	 * /*---------------------- ATRIBUTES -------------------------------------------------------------------
	 */
	/**
	 */
	@Audited
	@NotNull
	@Type(type="transientEntity",
		parameters=@Parameter(name="entity", value="br.com.eits.usuario.domain.entity.Usuario")
	)
	@Column(name = "usuario_responsavel")
	private Usuario usuarioResponsavel;
	
	@Audited
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Estado estadoAnterior;
	
	@NotNull
	@Audited	
	@Enumerated(EnumType.ORDINAL)
	private Estado estadoAtual;
	
	@Audited
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "solicitacao_id")
	private SolicitacaoEquipamento solicitacao;
	

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

	public Historico() {
		super();
	}

	public Historico(Long id) {
		super(id);
	}

	/*-------------------------------------------------------------------
	 *  ------------------------------BEHAVIORS---------------------------
	 *  This class has no specific behavior
	 */
	
}
