package ideasw.secopre.dto;

public class Authorization {
	
	private String formalityCode;
	private Boolean canUserAuthorize;
	private Boolean superUser;
	private Boolean moreSignatures;
	
	public String getFormalityCode() {
		return formalityCode;
	}

	public void setFormalityCode(String formalityCode) {
		this.formalityCode = formalityCode;
	}

	public Boolean getCanUserAuthorize() {
		return canUserAuthorize;
	}

	public void setCanUserAuthorize(Boolean canUserAuthorize) {
		this.canUserAuthorize = canUserAuthorize;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}
	
	public String toString(){
		return "formalityCode: " + formalityCode + ", canUserAuthorize:" + canUserAuthorize + ", superUser: " + superUser;
	}

	public Boolean getMoreSignatures() {
		return moreSignatures;
	}

	public void setMoreSignatures(Boolean moreSignatures) {
		this.moreSignatures = moreSignatures;
	}

}
