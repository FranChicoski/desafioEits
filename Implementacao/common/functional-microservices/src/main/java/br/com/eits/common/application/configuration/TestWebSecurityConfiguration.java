package br.com.eits.common.application.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("test")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "br.com.eits.stub")
public class TestWebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser( "admin@admin.com" ).password( "admin" ).roles( "ADMIN" );
	}
}
