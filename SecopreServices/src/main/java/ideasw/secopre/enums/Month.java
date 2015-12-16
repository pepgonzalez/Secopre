package ideasw.secopre.enums;

public enum Month {
	ENERO(1L), FEBRERO(2L), MARZO(3L), ABRIL(4L), MAYO(5L), JUNIO(6L),
	JULIO(7L), AGOSTO(8L), SEPTIEMBRE(9L), OCTUBRE(10L), NOVIEMBRE(11L), DICIEMBRE(12L);
	
	private Long id;
	
	private Month(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
}
