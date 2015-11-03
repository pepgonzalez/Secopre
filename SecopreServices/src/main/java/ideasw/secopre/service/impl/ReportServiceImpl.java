package ideasw.secopre.service.impl;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestConfig;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.dto.StageConfig;
import ideasw.secopre.dto.WorkFlowConfig;
import ideasw.secopre.enums.Month;
import ideasw.secopre.enums.WorkFlowCode;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.MovementType;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.ReportService;
import ideasw.secopre.service.impl.mapper.DistrictMapper;
import ideasw.secopre.service.impl.mapper.EntryMapper;
import ideasw.secopre.service.impl.mapper.FormalityMapper;
import ideasw.secopre.service.impl.mapper.InboxMapper;
import ideasw.secopre.service.impl.mapper.RequestConfigMapper;
import ideasw.secopre.service.impl.mapper.RequestHistoryMapper;
import ideasw.secopre.service.impl.mapper.RequestMapper;
import ideasw.secopre.service.impl.mapper.StageConfigMapper;
import ideasw.secopre.service.impl.mapper.MovementMapper;
import ideasw.secopre.service.impl.mapper.ReportMapper;
import ideasw.secopre.service.impl.mapper.WorkFlowConfigMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sun.util.calendar.BaseCalendar.Date;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportServiceImpl extends AccessNativeServiceBaseImpl implements ReportService{

	@Autowired
	QueryContainer queryContainer;

	@Autowired
	public BaseService baseService;
	
	@Autowired
	public AccessNativeService nativeService;
	
	static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

	private Report getReportCompiledObject(Long reportId) throws Exception{
		Report report = this.getReportById(reportId);
		report.setResource(this.getReportResource(reportId));
		
		Blob reportBlob = report.getResource();
		System.out.println("tama de archivo: " + reportBlob.length());
		InputStream reportStream = reportBlob.getBinaryStream();
		
		JasperDesign reportDesing = JRXmlLoader.load(reportStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(reportDesing);
		
		reportStream.close();
		
		//se llena el reporte
		fillReport(jasperReport, report);
		
		return report;
	}
	
	
	private void fillReport(JasperReport jasperReport, Report report) throws JRException, SQLException{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("REPORT_CONNECTION", nativeService.getSecopreDSConnection());
		JasperPrint jasperPrint;
		
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
		report.setReport(getBytes(jasperPrint, report.getReportType())); 
	}
	
	private byte[] getBytes(JasperPrint jasperPrint, String reportType) throws JRException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		if(reportType.equals("PDF")){
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,	outputStream);
			pdfExporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			pdfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			pdfExporter.exportReport();
		}if(reportType.equals("XLS")){
			JExcelApiExporter xlsExporter = new JExcelApiExporter(); 
			xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			xlsExporter.exportReport();
		}
		return outputStream.toByteArray();
	}

	private Report getReportById(Long reportId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reportId", reportId);	
		return this.queryForList(Report.class, queryContainer.getSQL(SQLConstants.GET_REPORT_BY_ID), namedParameters, new ReportMapper()).get(0);
	}
	
	private Blob getReportResource(Long reportId) throws Exception {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reportId", reportId);	
		return this.queryForObject(Blob.class, queryContainer.getSQL(SQLConstants.GET_REPORT_RESOURCE), namedParameters);
	}


	@Override
	public Report getReport(Long reportId) throws Exception {
		Report report = this.getReportCompiledObject(reportId);
		
		return report;
	}	

}
