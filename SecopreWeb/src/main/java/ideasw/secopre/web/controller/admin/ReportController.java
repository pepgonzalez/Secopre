package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.UserMovement;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.ReportService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "report/list", method = { RequestMethod.GET })
	public String showReportList(ModelMap model, RedirectAttributes attributes,  Principal principal) throws Exception {
		
		LOG.info("showReportList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Report> reportList = accessNativeService.getReportList(loggedUser);		
		model.addAttribute("reportList", reportList);
				
		return SecopreConstans.MV_REPORT_LIST;
	}
	
	@RequestMapping(value = "report/download/{reportId}", method ={RequestMethod.GET})
	public void downloadReport(@PathVariable("reportId") Long reportId, HttpServletResponse response,  Principal principal) throws Exception{
		LOG.info("Descargando reportId : " +  reportId);
		
		User u = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		Report report = reportService.getReport(reportId, u.getId());
		
		super.flushReport(response, report);
	}
	
	
	@RequestMapping(value = "report/params/{reportId}", method ={RequestMethod.GET})
	public String showReportParams(@PathVariable("reportId") Long reportId, ModelMap model, HttpServletResponse response) throws Exception{
		LOG.info("mostrando parametros de reportId : " +  reportId);
		
		Report report = accessNativeService.getReportById(reportId);
		
		ReportParameter params = new ReportParameter();
		params.setReportId(report.getReportId());
		
		//TODO validacion distritos en reportes
		
		model.addAttribute("reportParameters", report.getReportParameters());
		model.addAttribute("reportParametersForm",params);
		model.addAttribute("reportName", report.getDescription());
		
		return SecopreConstans.MV_REPORT_PARAMS;
	}
	
	@RequestMapping(value = "report/download/paramReport", method = { RequestMethod.GET })
	public void downloadParamReport(@ModelAttribute("reportParametersForm") ReportParameter reportParameterForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LOG.info("Descargando reporte con parametros");
		LOG.info(reportParameterForm.toString());
		
		User u = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		Report report = reportService.getReport(reportParameterForm.getReportId(), u.getId(), reportParameterForm);
		
		String fileName = report.getDescription() + "." + report.getReportType().toLowerCase();
		
		String REPORT_TYPE_PDF = "application/pdf";
		String REPORT_TYPE_XLS = "application/vnd.ms-excel";
		
		OutputStream outputStream  = response.getOutputStream();
		response.setContentType(report.getReportType().equals("PDF") ? REPORT_TYPE_PDF : REPORT_TYPE_XLS);
		response.setContentLength(report.getReport().length);
		response.addHeader("Content-Disposition","attachment;filename="+fileName);
		response.setBufferSize(1024 * 15);
		outputStream.write(report.getReport());
		outputStream.flush();
		outputStream.close();
	}
	
}
