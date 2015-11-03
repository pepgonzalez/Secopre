package ideasw.secopre.service;

import ideasw.secopre.dto.Report;


public interface ReportService {
	
	/*
	 * Metodo que genera el reporte y lo retorna al controlador
	 * */
	Report getReport(Long reportId) throws Exception;
}
