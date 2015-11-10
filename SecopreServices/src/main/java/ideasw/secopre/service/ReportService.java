package ideasw.secopre.service;

import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;


public interface ReportService {
	
	/*
	 * Metodo que genera el reporte y lo retorna al controlador
	 * */
	Report getReport(Long reportId) throws Exception;
	
	Report getReport(Long reportId, ReportParameter params) throws Exception;
	
}
