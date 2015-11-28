package ideasw.secopre.web.controller;

import java.security.Principal;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
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
public class BudgetController extends AuthController {

	@Autowired
	private EntryConfigService entryConfigService;

	@RequestMapping(value = "adm/bugget", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getEntryList(
			@ModelAttribute("entryFilter") EntryFilter filter, ModelMap model,
			RedirectAttributes attributes, Principal principal) {
		model.addAttribute("entryFilter", filter);
		model.addAttribute("entidadList",
				secopreCache.getStateByUserMap(principal.getName()));
		model.addAttribute("balance", new EntryBalance());

		return SecopreConstans.MV_ADM_BUDGET;
	}

	@RequestMapping(value = "adm/budget/upload", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String upload(ModelMap model, RedirectAttributes attributes) {
		return SecopreConstans.MV_ADM_BUDGET;
	}

	@RequestMapping(value = "adm/budget/search", method = RequestMethod.POST)
	public String searchEntries(
			@ModelAttribute("entryFilter") EntryFilter entryFilter,
			ModelMap model) {
		EntryBalance balance = entryConfigService.getEntryBalance(entryFilter, StatusEntry.CONFIG);
		model.addAttribute("balance", balance);
		model.addAttribute("entryFilter", entryFilter);
		return "auth/admin/config/entry/byDistrict";
	}

}
