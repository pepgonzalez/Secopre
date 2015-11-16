package ideasw.secopre.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import ideasw.secopre.model.EntryDistrict;

/**
 * Clase de estereotipo DTO para el manejo de informacion del presupuesto anual
 * {@link Listado de EntryDistrict}
 * 
 * @author pepgonzalez
 *
 */
public class AnnualBudget {

	private Long userId;
	private Date updateDate;
	private List<EntryDistrict> amounts;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<EntryDistrict> getAmounts() {
		return amounts;
	}
	public void setAmounts(List<EntryDistrict> amounts) {
		this.amounts = amounts;
	}
	
}
