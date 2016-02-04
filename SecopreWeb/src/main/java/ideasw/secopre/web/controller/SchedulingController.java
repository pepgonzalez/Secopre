package ideasw.secopre.web.controller;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SchedulingController extends AuthController {
	@Autowired
	private EntryConfigService entryConfigService;

	@RequestMapping(value = "adm/sche", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getEntryList(
			@ModelAttribute("entryFilter") EntryFilter filter, ModelMap model,
			RedirectAttributes attributes, Principal principal) {
		model.addAttribute("entryFilter", filter);
		model.addAttribute("entidadList",
				secopreCache.getStateByUserMap(principal.getName()));
		model.addAttribute("balance", new EntryBalance());

		return SecopreConstans.MV_ADM_SCHEDULING;
	}

	@RequestMapping(value = "adm/sche/apply", method = RequestMethod.POST)
	public String apply(@ModelAttribute("entryFilter") EntryFilter entryFilter,
			ModelMap model, RedirectAttributes redirectAttributes) {

		List<String> errors = new ArrayList<String>();
		if (entryFilter.getStateId() == null
				|| entryFilter.getDistrictId() == null) {
			errors.add("Es necesario seleccionar un estado y un distrito");

			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			return SecopreConstans.MV_ADM_SCHEDULING_REDIRECT;
			
		}
		entryFilter.setType(Boolean.TRUE);
		try {
			entryConfigService.schedulingBalance(entryFilter);
		} catch (Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());

			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
		}
		model.addAttribute("entryFilter", entryFilter);
		return SecopreConstans.MV_ADM_SCHEDULING_REDIRECT;
	}
}
