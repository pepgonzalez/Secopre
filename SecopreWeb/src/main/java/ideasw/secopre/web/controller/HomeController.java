package ideasw.secopre.web.controller;

import ideasw.secopre.exception.ResourceNotFoundException;
import ideasw.secopre.model.security.User;
import ideasw.secopre.model.security.UserAccess;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.ControllerBase;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@Autowired
	protected AccessService accessService;

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
	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null && !StringUtils.isEmpty(error)) {
			if (error.equals("1")) {
				model.addObject("error", "Credenciales Inv�lidas!");
			} else {
				model.addObject("error", "Sesion Inv�lida!");

			}
		}
		model.setViewName(SecopreConstans.MV_LOGIN);
		return model;
	}

	@RequestMapping(value = "login/resetPass")
	public @ResponseBody Boolean resetPass(ModelMap modelMap,
			@RequestParam("username") String username) {
		return accessService.resetPass(username);
	}

	/**
	 * Metodo que atiende las peticiones al contexto /login.htm
	 * 
	 * @param model
	 * @return pagina JSP
	 */
	// Spring Security see this :
	@RequestMapping(value = "/logout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView logout(HttpServletRequest request,
			@RequestParam(value = "logout", required = false) String logout,
			Principal principal) {
		ModelAndView model = new ModelAndView();
		String jSessionId = request.getSession().getId();
		String ipAddress = request.getRemoteAddr();
		User user = secopreCache.getUser(principal.getName());
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("jSessionId", jSessionId);
		params.put("remoteIP", ipAddress);
		params.put("user", user);

		List<UserAccess> accessList = baseService.findByProperties(
				UserAccess.class, params);

		for (UserAccess access : accessList) {
			access.setLogoutDate(new Date());
			baseService.update(access);
		}
		model.addObject("msg", "Sali� de la aplicacion Exitosamente.");

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
	@RequestMapping(value = "/invalidSession", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String invalidSession(Principal user, ModelMap model) {
		model.addAttribute("title", "Upsss! Lo sentimos su session ha expirado");
		if (user != null) {
			model.addAttribute(
					"message",
					"Hola "
							+ user.getName()
							+ ", tu session expiro debido a que se inicio una nueva session desde otro navegador, por favor contacta a tu administrador!");
		} else {
			model.addAttribute(
					"message",
					"tu session expiro debido a que se inicio una nueva session desde otro navegador, por favor contacta a tu administrador!");
		}
		return SecopreConstans.MV_INVALID_SESSION;

	}

	/***
	 * Metodo que redireccion a una pagina custom los accesos no autorizados
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/403", method = { RequestMethod.GET,
			RequestMethod.POST })
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
	@RequestMapping(value = "/404", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void error404(HttpServletRequest req) {
		String originalUri = (String) req
				.getAttribute("javax.servlet.forward.request_uri");

		LOG.info("Recurso no encontrado ==> " + originalUri);
		throw new ResourceNotFoundException(originalUri);
	}
}
