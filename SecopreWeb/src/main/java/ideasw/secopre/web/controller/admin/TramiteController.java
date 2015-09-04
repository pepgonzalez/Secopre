package ideasw.secopre.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

@Controller
public class TramiteController extends AuthController {
	
	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "tram/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String addTramite(ModelMap model, RedirectAttributes attributes) {
		List<User> userList = baseService.findAll(User.class);
		User user = new User();
		model.addAttribute("userList", userList);
		model.addAttribute("user", user);
		return SecopreConstans.MV_TRAM_ADD;
	}

}
