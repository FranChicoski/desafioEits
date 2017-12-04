
TRUNCATE tarefa.tarefa CASCADE;

INSERT INTO equipamento.solicitacao_equipamento
(
 	id,usuario_autor,equipamento, justificativa, observacao_finalizacao,justificativa_cancelamento ,
 	justificativa_recusa,tipo_operacao,categoria_id,estado_solicitacao,prioridade_solicitacao,
 	data_criacao,data_prazo_atendimento,data_aprovacao,data_finalizacao, data_recusa,
 	data_cancelamento,atualizacao, criacao, versao 	
 )VALUES 
   	(1,1, "Tablet Samgung 9.7 polegadas","Não está mais ligando",null,null,null,1,503,0,5, now(),null, null, null,null,null,now(),now(),1),
   	(2,1, "Monitor LG","Monitor está funcionando só metade da tela",null,null,null,0,502,1,3, '2017-10-31 12:30:00','2017-11-08 12:30:00', '2017-11-05 12:30:00', null,null,null,now(),'2017-10-31 12:30:00',1),
   	(3,1, "Notebook Samsung","Teclado com problema",null,null,null,1,500,3,0, '2017-11-10 12:30:00','2017-11-14 12:30:00','2017-11-13 12:30:00', null,null,'2017-11-10 12:30:00',now(),'2017-11-10 12:30:00',1)
 );
   