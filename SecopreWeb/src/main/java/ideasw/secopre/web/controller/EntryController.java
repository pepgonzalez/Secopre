package ideasw.secopre.web.controller;

import ideasw.secopre.model.Entry;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de partidas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>oper: Indica que el controller es operativo</li>
 * <li>entry: Indica que la configuracion pertenece a Partidas</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class EntryController extends AuthController {

	@RequestMapping(value = "oper/entry/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Entry entry = new Entry();
		model.addAttribute("entryList", baseService.findAll(Entry.class));
		model.addAttribute("entry", entry);
		return SecopreConstans.MV_CAT_ENTRY;
	}
	
	@RequestMapping(value = "oper/entry/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("entry") Entry entry, ModelMap model) {
		try {
			baseService.persist(entry);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el puesto:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_ENTRY;
	}
}
