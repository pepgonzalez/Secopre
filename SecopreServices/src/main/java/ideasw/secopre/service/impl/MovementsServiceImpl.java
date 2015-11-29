package ideasw.secopre.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.MovementsService;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

@Service
public class MovementsServiceImpl extends AccessNativeServiceBaseImpl implements MovementsService{

	@Autowired
	QueryContainer queryContainer;

	@Autowired
	public BaseService baseService;
	
	
	static final Logger LOG = LoggerFactory.getLogger(MovementsServiceImpl.class);

	@Override
	public int savePartialRequest(Request request) {
		
		this.cleanMirrorMovements(request.getRequestId());
		this.insertMirrorMovementList(request.getDownMovements(), request);
		this.insertMirrorMovementList(request.getUpMovements(), request);
		
		return 0;
	}
	
	private int cleanMirrorMovements(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.CLEAN_REQUEST_DETAIL_MIRROR), params);
	}
	
	private void insertMirrorMovementList(List<Movement> movementList, Request request){
		for(Movement m : movementList){
			m.setRequestId(request.getRequestId());
			this.insertMirrorMovement(m);
		}
	}
	
	private int insertMirrorMovement(Movement m){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", m.getRequestId())
				.addValue("movementTypeId", m.getMovementTypeId())
				.addValue("programaticKeyId", m.getProgramaticKeyId())
				.addValue("entryId", m.getEntryId())
				.addValue("initialMonth", m.getInitialMonthId())
				.addValue("finalMonth", m.getFinalMonthId())
				.addValue("monthAmount", m.getMonthAmount())
				.addValue("totalAmount", m.getTotalAmount())
				.addValue("active", 1);
		LOG.info("insertando movimiento");
		LOG.info(m.toString());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_DETAIL_MIRROR), params);
	}
	
}
