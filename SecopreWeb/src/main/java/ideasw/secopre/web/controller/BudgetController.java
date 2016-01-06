package ideasw.secopre.web.controller;

import ideasw.secopre.dto.AnnualBudgetFile;
import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		model.addAttribute("entriesNextYear", entryConfigService.numberEntriesNextYear());
		model.addAttribute("entriesInConfig", entryConfigService.existEntriesInConfig(true));
		return SecopreConstans.MV_ADM_BUDGET;
	}

	@RequestMapping(value = "adm/bugget/upload", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String upload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("attachment") MultipartFile attachment,
			ModelMap model, Principal principal,
			RedirectAttributes redirectAttributes) {
		AnnualBudgetFile uploadItem = new AnnualBudgetFile();

		uploadItem.setFile(attachment);

		try {
			entryConfigService.importExcel(uploadItem, principal.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());

			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);

		}
		return SecopreConstans.MV__ADM_BUDGET_REDIRECT;
	}

	@RequestMapping(value = "adm/budget/search", method = RequestMethod.POST)
	public String searchEntries(
			@ModelAttribute("entryFilter") EntryFilter entryFilter,
			ModelMap model) {
		EntryBalance balance = entryConfigService.getEntryBalance(entryFilter,
				StatusEntry.CONFIG);
		model.addAttribute("balance", balance);
		model.addAttribute("entryFilter", entryFilter);
		return "auth/admin/config/entry/byDistrict";
	}

	@RequestMapping(value = "adm/bugget/clone", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String clone(ModelMap model, Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			entryConfigService.cloneEntries(principal.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());

			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
		}
		return SecopreConstans.MV__ADM_BUDGET_REDIRECT;
	}
	@RequestMapping(value = "adm/bugget/release", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String release(ModelMap model, Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			entryConfigService.releaseBudget(principal.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());

			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
		}
		return SecopreConstans.MV__ADM_BUDGET_REDIRECT;
	}
}
