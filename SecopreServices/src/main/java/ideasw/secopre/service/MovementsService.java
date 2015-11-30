package ideasw.secopre.service;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.dto.UserMovement;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.dto.Notification;
import ideasw.secopre.model.DueDate;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface MovementsService {
	
	int savePartialRequest(Request request);
	
	int removeMirrorElement(Long requestDetailId);
	
	boolean isValidMovement(Long requestId, Long movementTypeId) throws EntryDistrictException;

	int cleanMirrorMovements(Long requestId);
	
}
