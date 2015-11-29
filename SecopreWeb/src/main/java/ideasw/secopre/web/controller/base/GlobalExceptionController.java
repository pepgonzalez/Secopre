package ideasw.secopre.web.controller.base;

import ideasw.secopre.exception.ResourceNotFoundException;
import ideasw.secopre.web.SecopreConstans;

import java.security.Principal;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@link @Controller} global que permite cachar y manejar todas las excepciones
 * generadas por las clases anotadas con {@link @Controller}
 * 
 * @author jorge.canoc@gmail.com
 * 
 */
@ControllerAdvice
public class GlobalExceptionController {
	static final Logger LOG = LoggerFactory
			.getLogger(GlobalExceptionController.class);

	/**
	 * Metodo que permite las excepciones generales no clasificadas generadas en
	 * la capa del controlador
	 * 
	 * @param exception
	 * @return pagina de error generica
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception exception) {
		LOG.error("##########Ocurrio una excepcion Inesperada#############",
				exception);
		ModelAndView model = new ModelAndView("auth/common/genericError");
		model.addObject("errMsg",
				"Ocurrio un error, por favor intente mas tarde");
		return model;

	}

	/**
	 * Metodo que permite las excepciones generales no clasificadas generadas en
	 * la capa del controlador
	 * 
	 * @param exception
	 * @return pagina de error generica
	 */
	@ExceptionHandler(javax.servlet.ServletException.class)
	public ModelAndView handleAllException(javax.servlet.ServletException exception, Principal principal) {
		LOG.error("##########Se solicito un path no configurado#############",
				exception);
		ModelAndView model; 

		if(principal != null && principal.getName() != null){
			model  = new ModelAndView("auth/start");
			model.addObject("errMsg",
					"Ocurrio un error, por favor intente mas tarde");
		}else{
			model  = new ModelAndView(SecopreConstans.MV_403);
			model.addObject(
					"message",
					"Hola , no tienes permisos para acceder a esta pagina, por favor contacta a tu administrador!");
			
		}
		return model;

	}	
	

	/**
	 * Metodo que permite cachar y evaluar los errores ocacionados por un codigo
	 * HTTP 404
	 * 
	 * @param resourceNotFoundException
	 * @return pagina de error generica
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleResourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException) {
		LOG.error(
				"|||||||Se genero un error al solicitar un recurso no localizado|||||||||",
				resourceNotFoundException);
		ModelAndView model = new ModelAndView("auth/common/genericError");
		model.addObject("errMsg", "El recurso solicitado no fue encontrado: "
				+ resourceNotFoundException.getResourceNotFound());
		return model;
	}

	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(SQLException sqlException) {
		LOG.error("$$$$$$Ocurrio una excepcion de SQL $$$$$$", sqlException);
		ModelAndView model = new ModelAndView("base/error/genericError");
		model.addObject("errMsg",
				"Ocurrio un error, por favor intente mas tarde");
		return model;
	}
}
