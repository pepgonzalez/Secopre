package ideasw.secopre.web.controller.base;

import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.web.controller.SecopreCache;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/***
 * Clase base que implementa los metodos mas comunes que seran utilizados en la
 * capa de controladores
 * 
 * @author jorge.canoc@gmail.com
 * 
 */
@Component
public class ControllerBase {
	private static final String ERROR_GENERIC = "0001";
	@Autowired
	private MessageSource messageSource;

	@Autowired
	public BaseService baseService;
	
	@Autowired
	public SecopreCache secopreCache;
	
	@Autowired
	public AccessNativeService accessNativeService;
	

	private Locale locale = LocaleContextHolder.getLocale();

	/**
	 * Metodo que permite la obtencion de los mensajes del archido de recursos
	 * en el idioma que corresponda, asi como el paso de valores cuando existan
	 * place holders en el mensaje.
	 * 
	 * @param properties
	 * @param values
	 * @return El mensaje correspondiente
	 */
	public String getMessage(String properties, Object[] values) {
		return messageSource.getMessage(properties, values, locale);
	}

	/**
	 * Metodo que permite la obtencion de los mensajes del archido de recursos
	 * en el idioma que corresponda
	 * 
	 * @param properties
	 * @return El mensaje correspondiente
	 */
	public String getMessage(String properties) {
		return this.getMessage(properties, null);
	}

	/**
	 * Metodo que permite incializar un mapa de errores dado una descripcion de
	 * un error en particular, este metodo debe ser utilizado cuando se
	 * identifique que el metodo en el controller solo regresara un error, en
	 * caso de que pueda regresar multiples errores es necesario subir la logica
	 * a la capa de servicios
	 * 
	 * @param errorDesc
	 * @return
	 */
	public Map<String, String> initErrors(String errorDesc) {
		Map<String, String> errors = new HashMap<String, String>(0);
		errors.put(ERROR_GENERIC, errorDesc);
		return errors;
	}
}
