package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReportController extends AuthController {

	static final Logger LOG = LoggerFactory
			.getLogger(AuthController.class);
	
	@Autowired
	private AccessNativeService accessNativeService;
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(value = "report/list", method = { RequestMethod.GET })
	public String showReportList(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		LOG.info("showReportList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Report> reportList = accessNativeService.getReportList(loggedUser);		
		model.addAttribute("reportList", reportList);
				
		return SecopreConstans.MV_REPORT_LIST;
	}
	
	@RequestMapping(value = "report/download/{reportId}", method ={RequestMethod.GET})
	public void downloadReport(@PathVariable("reportId") Long reportId, HttpServletResponse response){
		LOG.info("Descargando reportId : " +  reportId);
	}
	
}
