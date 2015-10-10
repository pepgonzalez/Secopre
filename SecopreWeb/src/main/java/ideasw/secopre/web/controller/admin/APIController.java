package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.catalog.MovementType;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;
import ideasw.secopre.model.Entry;


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
