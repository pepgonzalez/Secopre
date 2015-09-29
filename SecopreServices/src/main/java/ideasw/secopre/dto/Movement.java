package ideasw.secopre.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link REQUEST_DETAIL}
 * 
 * @author pepgonzalez
 *
 */
public class Movement {
	
	//variables correspondientes al tipo de tramite de movimientos
	private Long movementTypeId;
	private String description;
	private Long programaticKeyId;
	private Long entryId;
	private Long initialMonthId;
	private Long finalMonthId;
	private Double monthAmount;
	private Double totalAmount;

}
