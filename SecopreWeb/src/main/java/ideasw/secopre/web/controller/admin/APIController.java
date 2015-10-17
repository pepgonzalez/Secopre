package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.Entry;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class APIController extends AuthController {

	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "API/get/entry/{programaticKey}", method = { RequestMethod.GET })
	public @ResponseBody List<Entry> getEntriesByProgramaticKey(
			@PathVariable("programaticKey") Long programaticKey,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

			List<Entry> entryList = accessNativeService.getEntries(programaticKey);

			return entryList;
	}

}
