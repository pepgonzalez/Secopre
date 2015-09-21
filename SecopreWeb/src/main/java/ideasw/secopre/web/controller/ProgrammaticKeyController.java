package ideasw.secopre.web.controller;

import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Claves
 * Programaticas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>oper: Indica que el controller es operativo</li>
 * <li>pk: Indica que la configuracion pertenece a Clave Programatica</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class ProgrammaticKeyController extends AuthController {

	@RequestMapping(value = "oper/pk/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		ProgrammaticKey pk = new ProgrammaticKey();
		model.addAttribute("pkList", baseService.findAll(ProgrammaticKey.class));
		model.addAttribute("pk", pk);
		return SecopreConstans.MV_CAT_ENTRY;
	}
}
