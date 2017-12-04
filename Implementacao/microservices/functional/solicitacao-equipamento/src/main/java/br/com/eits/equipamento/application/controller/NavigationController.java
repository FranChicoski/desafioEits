package br.com.eits.equipamento.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author rodrigo
 */
@Controller
public class NavigationController
{
	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView( "modules/equipamento/index" );
	}
}
