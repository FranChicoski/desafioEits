package br.com.eits.categoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import br.com.eits.common.CommonConfiguration;
import br.com.eits.common.domain.entity.IEntity;
import br.com.eits.equipamento.application.restful.ISolicitacaoResource;

import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author rodrigo.p.fraga
 */
@EnableDiscoveryClient
@EntityScan(basePackageClasses = {
	IEntity.class,
	Application.class
})
@SpringBootApplication(scanBasePackageClasses = {
	CommonConfiguration.class,
	Application.class
})
@EnableFeignClients(clients = {ISolicitacaoResource.class})
public class Application
{
	/**
	 * 
	 * @param args
	 */
	
	
	public static void main( String[] args )
	{
		SpringApplication.run( Application.class, args );
	}
	/*-------------------------------------------------------------------
	 * 		 					  BEANS
	 *-------------------------------------------------------------------*/
}
