package br.com.eits.equipamento.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.Assert;

import br.com.eits.categoria.domain.entity.Categoria; 
import br.com.eits.common.domain.entity.AbstractEntity;
import br.com.eits.usuario.domain.entity.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Audited
@DataTransferObject(javascript="SolicitacaoEquipamento")
@EqualsAndHashCode(exclude = {"historico_solicitacao"},callSuper=true)
@Table(name="solicitacao_equipamento"  )
public class SolicitacaoEquipamento extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8555503246672357933L;

	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Audited
	@NotEmpty
	@Size(max=120)
	@NotNull(message="Informe o equipamento para a solicitação")
	@Column(nullable=false, length=120)
	private String equipamento;
	
	@Audited
	@NotEmpty
	@NotNull(message="Informe uma justificativa para a solicitação")
	@Column(nullable=false, columnDefinition="text")
	private String justificativa;
	
	@Audited
	@Column(columnDefinition="text")
	private String justificativaCancelamento;
	
	@Audited
	@Column(columnDefinition="text")
	private String justificativaRecusa;
	
	@Audited
	@Column(columnDefinition="text")
	private String observacaoFinalizacao;
	
	@Audited
	@Column(nullable = true)
	private LocalDateTime dataPrazoAtendimento;
	
	@Audited
	@Column(nullable = true)
	private LocalDateTime dataAprovacao;
	
	@Audited
	@Column(nullable = true)
	private LocalDateTime dataCancelamento;
	
	@Audited
	@Column(nullable = true)
	private LocalDateTime dataRecusa;
	
	@Audited
	@Column(nullable = true)
	private LocalDateTime dataFinalizacao;
	
	
	@Audited
	@OneToMany(mappedBy = "solicitacao", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE )
	List<Historico> historico_solicitacao;

	@Audited
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estado_solicitacao", nullable=false)
	private Estado estado;

	@Audited
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "prioridade_solicitacao", nullable=false)
	private Prioridade prioridade;
	
	@Audited
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private TipoOperacao tipoOperacao;

	@Audited
	@NotNull
	@Type(type="transientEntity", 
		parameters=@Parameter(name="entity", value="br.com.eits.usuario.domain.entity.Usuario")
	) //estudar o prq deste tipo
	@Column(nullable= false,name = "usuario_autor")
	private Usuario autorSolicitacao;
	
	@Audited
	@NotNull
	@Type(type="transientEntity", 
	parameters=@Parameter(name="entity", value="br.com.eits.categoria.domain.entity.Categoria")
           )
	@Column(nullable= false,name = "categoria_id")
	private Categoria categoria;
		

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

	/*
	 * Utilizando o construtor da classe pai AbstractEntity
	 */
	public SolicitacaoEquipamento() {
		super();
	}

	public SolicitacaoEquipamento(Long id) {
		super(id);
	}

	/*-------------------------------------------------------------------
	 *  ------------------------------BEHAVIORS---------------------------
	 */

	/**
	 * 
	 * @param prioridadeAlteracao
	 * @param dataAprovada
	 */
	public void aprovarSolicitacao(Prioridade prioridadeAlteracao, LocalDateTime dataAprovada) 
	{
				Assert.isTrue((this.getEstado()==Estado.SOLICITADA),"Esta solicitação não pode ser aprovada");
				Assert.notNull(dataAprovada,"Data não pode ser nula");
				if ((dataAprovada.isAfter((LocalDateTime.now().plusDays(1)))  || dataAprovada.equals( dataAprovada.toLocalDate().atStartOfDay())))
				{
							this.dataAprovacao = dataAprovada;
				}
				if (this.getPrioridade() != prioridadeAlteracao) 
				{
							this.setPrioridade(prioridadeAlteracao); 
				}
				this.setEstado(Estado.APROVADA);
	}
	

	/**
	 * 
	 * @param justificativa
	 */
	public void recusarSolicitacao(String justificativa) 
	{
		Assert.isTrue(estado == Estado.SOLICITADA,"Estado da solicitação não permite recusa");
		Assert.notNull(justificativa, "O campo justificativa não pode ser nulo");
		this.justificativaRecusa = justificativa;
		this.dataRecusa = LocalDateTime.now();
		this.estado = Estado.RECUSADA;
	}

	/**
	 * 
	 * @param observacao
	 * @param dataConclusao
	 */
	public void concluirSolicitacao(String observacao, LocalDateTime dataConclusao) 
	{
		Assert.isTrue(estado == Estado.APROVADA,"Estado da solicitação não permite finalização");
		Assert.notNull(dataConclusao,"Data não pode ser nula");
		if(this.dataFinalizacao.isBefore(LocalDateTime.now().toLocalDate().atStartOfDay()))
		{
			this.dataFinalizacao = 	LocalDateTime.now();
		}else 
		{
			this.dataFinalizacao = dataConclusao;
		}
		this.observacaoFinalizacao = observacao;
		this.estado = Estado.CONCLUIDA;
	}

	
/**
 * 
 * @param justificativa
 */
	public void cancelarSolicitacao(String justificativa) 
	{
		Assert.isTrue((this.estado==Estado.APROVADA),"Esta solicitação não pode ser cancelada");
		Assert.notNull(justificativa,"Justificativa não pode ser nula");
		Assert.hasText(justificativa, "Justificativa precisa ser preenchida");
		this.dataCancelamento = LocalDateTime.now();
		this.justificativaCancelamento = justificativa;
		this.estado = Estado.CANCELADA;
	}

	/**
	 * 
	 * @param prazoAtendimento
	 * @return
	 */
	public Boolean validarDataAtendimento(LocalDateTime prazoAtendimento) 
	{
		Timestamp convertData = Timestamp.valueOf(prazoAtendimento);
		Timestamp hoje = Timestamp.valueOf(LocalDateTime.now().toLocalDate().atStartOfDay());
		/*if((prazoAtendimento.isEqual(LocalDateTime.now().toLocalDate().atStartOfDay()))
					 || (prazoAtendimento.isBefore(LocalDateTime.now().toLocalDate().atStartOfDay())))*/
		if((convertData.equals(hoje)) || (convertData.before( hoje)))
		{
			return false;
		}
		 return true;
	}
	
	/**
	 * 
	 * @param usuarioId
	 * @param estado
	 * @return
	 */
	  public Boolean validarExclusao(Long usuarioId, Estado estado) 
	  {  
		  try 
		  {
			  Assert.isTrue((this.autorSolicitacao.getId()).equals(usuarioId),"Usuário não é autor da solicitação");
			  Assert.isTrue(this.estado == Estado.APROVADA,"Estado da solicitação 'APROVADA' não permite cancelamento");
			  return true;
		  }catch(Exception e)
		  {
			  e.printStackTrace();
			  return false;
		  }
	  }

	  
}

	
	




