package br.com.eits.equipamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import br.com.eits.categoria.application.restful.ICategoriaResource;
import br.com.eits.common.CommonConfiguration;
import br.com.eits.common.domain.entity.IEntity;
import br.com.eits.usuario.application.restful.IUsuarioResource;

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
@EnableFeignClients(clients = {
		ICategoriaResource.class,
		IUsuarioResource.class
})
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
