package br.com.eits.equipamento.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.equipamento.domain.entity.Historico;


@Transactional
@Repository
public interface IHistoricoRepository extends JpaRepository<Historico, Long>{
	
	@Query(value="SELECT historico FROM Historico historico WHERE historico.solicitacao.id = :solicitacaoId ")
	public Page<Historico> listHistoricoBySolicitcao(@Param("solicitacaoId") Long solicitacaoId,
			Pageable pageable );
}
