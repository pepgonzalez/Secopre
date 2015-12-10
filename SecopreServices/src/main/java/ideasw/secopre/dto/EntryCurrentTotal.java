package ideasw.secopre.dto;

import java.util.List;

public class EntryCurrentTotal {

	private Long districtId;
	private String districtNumber;
	private String state;
	private Long entryId;
	private String entryDescription;
	private String entryCode;
	
	private Double totalAmount;
	private Double enero;
	private Double febrero;
	private Double marzo;
	private Double abril;
	private Double mayo;
	private Double junio;
	private Double julio;
	private Double agosto;
	private Double septiembre;
	private Double octubre;
	private Double noviembre;
	private Double diciembre;
	
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictNumber() {
		return districtNumber;
	}
	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public String getEntryDescription() {
		return entryDescription;
	}
	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getEnero() {
		return enero;
	}
	public void setEnero(Double enero) {
		this.enero = enero;
	}
	public Double getFebrero() {
		return febrero;
	}
	public void setFebrero(Double febrero) {
		this.febrero = febrero;
	}
	public Double getMarzo() {
		return marzo;
	}
	public void setMarzo(Double marzo) {
		this.marzo = marzo;
	}
	public Double getAbril() {
		return abril;
	}
	public void setAbril(Double abril) {
		this.abril = abril;
	}
	public Double getMayo() {
		return mayo;
	}
	public void setMayo(Double mayo) {
		this.mayo = mayo;
	}
	public Double getJunio() {
		return junio;
	}
	public void setJunio(Double junio) {
		this.junio = junio;
	}
	public Double getJulio() {
		return julio;
	}
	public void setJulio(Double julio) {
		this.julio = julio;
	}
	public Double getAgosto() {
		return agosto;
	}
	public void setAgosto(Double agosto) {
		this.agosto = agosto;
	}
	public Double getSeptiembre() {
		return septiembre;
	}
	public void setSeptiembre(Double septiembre) {
		this.septiembre = septiembre;
	}
	public Double getOctubre() {
		return octubre;
	}
	public void setOctubre(Double octubre) {
		this.octubre = octubre;
	}
	public Double getNoviembre() {
		return noviembre;
	}
	public void setNoviembre(Double noviembre) {
		this.noviembre = noviembre;
	}
	public Double getDiciembre() {
		return diciembre;
	}
	public void setDiciembre(Double diciembre) {
		this.diciembre = diciembre;
	}
	
	public String getDesc(){
		return this.getState() + " (" + this.getDistrictNumber() + ") - " + this.getEntryDescription();
	}
	public String getEntryCode() {
		return entryCode;
	}
	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}
}
