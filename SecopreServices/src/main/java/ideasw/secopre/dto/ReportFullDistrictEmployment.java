package ideasw.secopre.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link REPORT_PARAMETER}
 * 
 * @author pepgonzalez
 *
 */
public class ReportFullDistrictEmployment {

	//campos base
	private Long reportId;
	private Long employmentId;
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public Long getEmploymentId() {
		return employmentId;
	}
	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

}
