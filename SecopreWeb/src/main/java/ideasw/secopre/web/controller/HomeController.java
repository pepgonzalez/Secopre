package ideasw.secopre.web.controller;

import ideasw.secopre.exception.ResourceNotFoundException;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.ControllerBase;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller principal que atiende las peticiones mas genericas como /, /index,
 * /home, /inicio
 * 
 * @author jorge.canoc@gmail.com
 * 
 */
@Controller
public class HomeController extends ControllerBase {

	static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Metodo que atiende las peticiones al contexto /
	 * 
	 * @param model
	 * @return pagina JSP
	 */
	@RequestMapping("/")
	public String rootPath(ModelMap model) {
		return "redirect:/" + SecopreConstans.MV_LOGIN;
	}

	/**
	 * Metodo que atiende las peticiones al contexto /login.htm
	 * 
	 * @param model
	 * @return pagina JSP
	 */
	// Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null && !StringUtils.isEmpty(error)) {
			model.addObject("error", "Credenciales Inválidas!");
		}
		model.setViewName(SecopreConstans.MV_LOGIN);
		return model;
	}

	/**
	 * Metodo que atiende las peticiones al contexto /login.htm
	 * 
	 * @param model
	 * @return pagina JSP
	 */
	// Spring Security see this :
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("msg", "Salió de la aplicacion Exitosamente.");
		
		model.setViewName(SecopreConstans.MV_LOGIN);
		return model;
	}

	/***
	 * Metodo que redireccion a una pagina custom los accesos no autorizados
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Principal user, ModelMap model) {
		model.addAttribute("title", "Upsss! Lo sentimos no tienes permisos");
		if (user != null) {
			model.addAttribute(
					"message",
					"Hola "
							+ user.getName()
							+ ", no tienes permisos para acceder a esta pagina, por favor contacta a tu administrador!");
		} else {
			model.addAttribute(
					"message",
					"no tienes permisos para acceder a esta pagina, por favor contacta a tu administrador!");
		}
		return SecopreConstans.MV_403;

	}

	/**
	 * Metodo que atiende las peticiones de la URL /404, esta peticion es
	 * cachada en el web.xml mediante el tag: error-page y obtiene el recurso
	 * que fue solicitado en el request original, para posterirormente lanzar la
	 * excepcion y esta sea cachada por {@link ResourceNotFoundException}
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/404")
	public void error404(HttpServletRequest req) {
		String originalUri = (String) req
				.getAttribute("javax.servlet.forward.request_uri");

		LOG.info("Recurso no encontrado ==> " + originalUri);
		throw new ResourceNotFoundException(originalUri);
	}
}
