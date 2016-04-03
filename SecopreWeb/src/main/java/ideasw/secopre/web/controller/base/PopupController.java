package ideasw.secopre.web.controller.base;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.Report;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.web.SecopreConstans;

@Controller
@RequestMapping("/popup/")
public class PopupController extends ControllerBase {

	@Autowired
	private EntryConfigService entryConfigService;

	@RequestMapping(value = "cfg/entry/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(@ModelAttribute("entryFilter") EntryFilter filter,
			ModelMap model, Principal principal) {

		model.addAttribute("entryFilter", filter);
		model.addAttribute("entidadList",
				secopreCache.getStateByUserMap(principal.getName()));
		model.addAttribute("balance", new EntryBalance());
		return SecopreConstans.MV_POPUP_CONF_ENTRY;
	}
}
