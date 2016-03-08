package ideasw.secopre.service;

import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;


public interface ReportService {
	
	/*
	 * Metodo que genera el reporte y lo retorna al controlador
	 * */
	Report getReport(Long reportId, Long userId) throws Exception;
	
	Report getReport(Long reportId, Long userId, ReportParameter params) throws Exception;
	
	Report getReport(Long reportId, Long userId, String reportType) throws Exception;

	Report getReport(Long reportId, Long userId, ReportParameter params, String reportType)throws Exception;
//	
//	Report getReport(Long reportId, Long userId,  String reportType)throws Exception;
}
