package ideasw.secopre.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import ideasw.secopre.constants.PropertyConstants;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Property;
import ideasw.secopre.dto.Request;
import ideasw.secopre.exception.EntryDistrictException;
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
	
	@Autowired
	public AccessNativeService accessNativeService;

	
	static final Logger LOG = LoggerFactory.getLogger(MovementsServiceImpl.class);
	
	
	public void rollbackRequestDetail(Request request){
		//obtengo lo movimientoss de request detail
		//los borro de request detail
		//los inserto en mirror
	}
	
	@Override
	public boolean isValidMovement(Long requestId, Long movementTypeId) throws EntryDistrictException {
		boolean isValid = true;
		
		//se obtiene el total de registros en mirror
		Request requestForm = accessNativeService.getRequestAndPartialDetailById(requestId);
		
		
		//si no existe ningun movimiento, se lanza error
		if((requestForm.getUpMovements().size() + requestForm.getDownMovements().size()) <= 0){
			throw new EntryDistrictException("Ingrese al menos un movimiento para continuar");
		}
		
		Property p = accessNativeService.getPropertyByCode(PropertyConstants.COMPENSATED_MOVEMENT_TYPE);
		//si es compensado, se valida que la suma de los movimientos a la alza mas los movimientos a la baja resulte 0
		if(movementTypeId != null && p.getLongValue().longValue() == movementTypeId.longValue()){
			//se obtiene el total de las amplicaciones compensadas
			Double upTotal = this.getTotalFromRequest(1L, requestId);
			//se obtiene el total de las reducciones compensadas
			Double downTotal = this.getTotalFromRequest(-1L, requestId);
			//si es distinto, se levanta un error
			if(upTotal.doubleValue() != downTotal.doubleValue()){
				throw new EntryDistrictException("El monto total de ampliaciones compensadas (" + upTotal + ") difiere con el monto total de reducciones compensadas (" + downTotal +"). Verifique.");
			}
		}
		//regresa validacion correcta
		return isValid;
	}
	
	private Double getTotalFromRequest(Long movementTypeId, Long requestId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("requestId", requestId).addValue("movementTypeId", movementTypeId);
		return this.queryForObject(Double.class, queryContainer.getSQL(SQLConstants.GET_MIRROR_MOVEMENT_TOTAL), params);
	}

	@Override
	public int savePartialRequest(Request request) {
		
		this.cleanMirrorMovements(request.getRequestId());
		this.insertMirrorMovementList(request.getDownMovements(), request);
		this.insertMirrorMovementList(request.getUpMovements(), request);
		
		return 0;
	}
	
	@Override
	public int removeMirrorElement(Long requestDetailId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("requestDetailId", requestDetailId);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.REMOVE_REQUEST_DETAIL_ELEMENT), params);
	}
	
	@Override
	public int cleanMirrorMovements(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("requestId", requestId);
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
				.addValue("monthAmount", m.getMonthAmountValue())
				.addValue("totalAmount", m.getTotalAmountValue())
				.addValue("active", 1);
		LOG.info("insertando movimiento");
		LOG.info(m.toString());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_DETAIL_MIRROR), params);
	}
	
}
