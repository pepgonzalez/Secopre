package ideasw.secopre.web.controller.base;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ideasw.secopre.dto.Report;

@Controller
@RequestMapping("/auth**")
public class AuthController extends ControllerBase {

	public void flushReport(HttpServletResponse response, Report report) throws IOException{
		String REPORT_TYPE_PDF = "application/pdf";
		String REPORT_TYPE_XLS = "application/vnd.ms-excel";
		
		String fileName = report.getDescription() + "." + report.getReportType().toLowerCase();
		
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
