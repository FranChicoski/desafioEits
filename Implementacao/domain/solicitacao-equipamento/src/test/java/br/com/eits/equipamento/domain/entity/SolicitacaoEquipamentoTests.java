package br.com.eits.equipamento.domain.entity;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.eits.usuario.domain.entity.Usuario;


public class SolicitacaoEquipamentoTests {
	/**
	 * 
	 */
	@Test
	public void testAprovarSolicitacaoTrue() 
	{
			final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
			solicitacao.setPrioridade(Prioridade.ALTA);
			solicitacao.setEstado(Estado.SOLICITADA);
			solicitacao.setDataAprovacao(LocalDateTime.now().plusDays(1));
			
			solicitacao.aprovarSolicitacao(solicitacao.getPrioridade(), solicitacao.getDataAprovacao());	
			
			Assert.assertEquals(Prioridade.ALTA,solicitacao.getPrioridade());
	}
	
	/**
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAprovarSolicitacaoFalse() 
	{
			final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
			solicitacao.setPrioridade(Prioridade.NORMAL);
			solicitacao.setEstado(Estado.CANCELADA);
			solicitacao.setDataAprovacao(LocalDateTime.now().minusDays(2));
			
			solicitacao.aprovarSolicitacao(solicitacao.getPrioridade(), solicitacao.getDataAprovacao());
			
			Assert.assertEquals(Estado.CANCELADA,solicitacao.getEstado());
			Assert.assertEquals(Prioridade.NORMAL,solicitacao.getPrioridade());
			Assert.fail("Falhou pois estado não é igual a SOLICITADA");
	}
	
	/**
	 * 
	 */
	@Test
	public void testRecusarSolicitacaoTrue() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		String testeJustificativa = "Isto é um teste";
		solicitacao.setEstado(Estado.SOLICITADA);
		solicitacao.setJustificativaRecusa(testeJustificativa);
		
		solicitacao.recusarSolicitacao(solicitacao.getJustificativaRecusa());
		
		Assert.assertNotNull(solicitacao.getJustificativaRecusa());
		Assert.assertEquals(Estado.RECUSADA,solicitacao.getEstado());
		System.out.println(solicitacao.getJustificativaRecusa());
		System.out.println(solicitacao.getEstado());
	}
	
	/**
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRecusarSolicitacaoFalse() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setJustificativaRecusa("");
		solicitacao.setEstado(Estado.CONCLUIDA);
		
		solicitacao.recusarSolicitacao(solicitacao.getJustificativaRecusa());
		
		Assert.assertEquals( "" , solicitacao.getJustificativaRecusa());
		Assert.assertEquals(Estado.CONCLUIDA,solicitacao.getEstado());
		System.out.println("Justificativa é vazia "+solicitacao.getJustificativaRecusa());
		System.out.println("Estado não permite recusa "+solicitacao.getEstado());
		Assert.fail();
	}
	

/**
 * 	
 */
	@Test
	public void testConcluirSolicitacaoTrue() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setObservacaoFinalizacao( "Isto é um teste de conclusão");
		solicitacao.setEstado(Estado.APROVADA);
		solicitacao.setDataFinalizacao(LocalDateTime.of(2017,11,10,10,15));
		
		solicitacao.concluirSolicitacao(solicitacao.getObservacaoFinalizacao(), solicitacao.getDataFinalizacao());
		
		Assert.assertNotNull(solicitacao.getDataFinalizacao());
		Assert.assertEquals(Estado.CONCLUIDA,solicitacao.getEstado());
		System.out.println("Observação  "+solicitacao.getObservacaoFinalizacao());
		System.out.println(solicitacao.getDataFinalizacao());
		System.out.println("Estado alterado para: "+solicitacao.getEstado());
	}
	
	/**
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConcluirSolicitacaoFalse() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setObservacaoFinalizacao( "");
		solicitacao.setEstado(Estado.RECUSADA);
		solicitacao.setDataFinalizacao(null);
		
		solicitacao.concluirSolicitacao(solicitacao.getObservacaoFinalizacao(), solicitacao.getDataFinalizacao());
		
		Assert.assertEquals(null,solicitacao.getDataFinalizacao());
		Assert.assertEquals(Estado.RECUSADA,solicitacao.getEstado());
		System.out.println("Data deve emitir null"+solicitacao.getDataFinalizacao());
		System.out.println("Estado permanece inalterado para: "+solicitacao.getEstado());
		Assert.fail();
	}
	/**
	 * 
	 */
	@Test
	public void testCancelarSolicitacaoTrue() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setJustificativaCancelamento("Ele não vai com a minha cara");;
		solicitacao.setEstado(Estado.APROVADA);
		
		solicitacao.cancelarSolicitacao(solicitacao.getJustificativaCancelamento());
		
		Assert.assertNotNull(solicitacao.getJustificativaCancelamento());
		System.out.println("Solicitacao deve admitir o estado"+solicitacao.getEstado());
	}
	
	/**
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCancelarSolicitacaoFalse() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setJustificativaCancelamento(" ");
		solicitacao.setEstado(Estado.APROVADA);
				
		solicitacao.cancelarSolicitacao(solicitacao.getJustificativaCancelamento());
		
		Assert.assertEquals(Estado.APROVADA, solicitacao.getEstado());
		Assert.assertEquals(" ",solicitacao.getJustificativaCancelamento()," ");
		System.out.println("Solicitacao não deve admitir o estado de CANCELADA. "+solicitacao.getEstado());
		System.out.println("Justificativa" + solicitacao.getJustificativaCancelamento());
		Assert.fail("Solicitação não admite o estado de CANCELADA");
	}
	
	/**
	 * 
	 */
	@Test
	public void testValidarDataAtendimentoTrue()
	{
//		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
//		solicitacao.setDataPrazoAtendimento(LocalDateTime.of(2017,11,11,14,25));
//
//		Boolean teste = solicitacao.validarDataAtendimento(solicitacao.getDataPrazoAtendimento());
//
//		Assert.assertSame(true, teste);
//		System.out.println("Data de solicitação deve estar "+solicitacao.getDataPrazoAtendimento());
	}
	
	/**
	 * 
	 */
	@Test
	public void testValidarDataAtendimentoFalse()
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		solicitacao.setDataPrazoAtendimento(LocalDateTime.now().minusDays(1));
		
		Assert.assertSame(false, solicitacao.validarDataAtendimento(solicitacao.getDataPrazoAtendimento()));
		System.out.println("Data de solicitação deve estar "+solicitacao.getDataPrazoAtendimento());
	}
	
	/**
	 * 
	 */
	@Test
	public void testValidarExclusaoTrue() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		final Usuario  user= new Usuario();
		user.setId(1L);
		solicitacao.setAutorSolicitacao(user);
		solicitacao.setEstado(Estado.APROVADA);
		Assert.assertEquals(true, solicitacao.validarExclusao(user.getId(), solicitacao.getEstado()));
		System.out.println("Solicitação excluida com sucesso");
	}
	/**
	 * 
	 */
	@Test
	public void testValidarExclusaoFalse() 
	{
		final SolicitacaoEquipamento solicitacao = new SolicitacaoEquipamento();
		final Usuario  user= new Usuario();
		user.setId(1L);
		final Usuario  user1= new Usuario();
		user.setId(2L);
		solicitacao.setAutorSolicitacao(user);
		solicitacao.setEstado(Estado.SOLICITADA);
		Assert.assertEquals(false, solicitacao.validarExclusao(user1.getId(), solicitacao.getEstado()));
		System.out.println("Solicitação não pode ser excluída");
	}
	

}
