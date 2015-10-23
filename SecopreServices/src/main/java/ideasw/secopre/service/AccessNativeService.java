package ideasw.secopre.service;

import java.util.List;
import java.util.Map;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.model.security.User;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;


public interface AccessNativeService {
	
	/*
	 * Metodo que retorna todas los tramites disponibles a inicializar por el usuario
	 * param user - Usuario en cuestion
	 * return List<Formality> - Listado de tramites disponibles
	 */
	List<Formality> getFormalityAvailableByUser(User user);
	
	/*
	 * Metodo transaccional para inicial el tramite del folio
	 * param request - Objeto con informacion de nuevo tramite
	 * param userId - Id de usuario que inicializa el tramite
	 */
	Long startFormality(Request request, Long userId) throws Exception;
	
	/*
	 * Metodo para obtener todos los tramites en tuberia que puede tratar el folio
	 * param userId - Identificador del usuario
	 */
	List<Inbox> getInboxByUserId(Long userId);
	
	/*
	 * Metodo transaccional para avanzar un folio de etapa
	 * param requestForm - Informacion del folio que se esta avanzando de etapa
	 * param userId - Identificador del usuario que avanza la etapa
	 */
	void invokeNextStage(Request requestForm,  Long userId);
	
	/*
	 * Metodo para obtener el siguiente consecutivo para la creacion de un tramite
	 * return Long requestId - nuevo id del tramite
	 */
	Long getRequestNextConsecutive();
	
	/*
	 * Metodo para obtener la informacion general del tramite
	 * param requestId - identificador del tramite
	 * return request - objeto con la informacion general del tramite
	 */

	Request getRequestById(Long requestId);
	
	/*
	 * Metodo para obtener la informacion general del tramite, y los registros de movimientos capturados
	 * param requestId - Id del tramite
	 * return request - objeto con la informacion general del tramite
	 */
	Request getRequestAndDetailById(Long requestId);
	
	/*
	 * Metodo para obtener la historia del folio
	 * param requestId - Id del folio
	 * return List<RequestHistory> - listado de etapas por las que paso el tramite
	 */
	List<RequestHistory> getRequestHistory(Long requestId);
	
	/*
	 * Metodo que actualiza la informacion de detalle de un folio
	 * param request - objeto con la informacion del tramite
	 */

	int insertOrUpdateRequestDetail(Request request) throws Exception;

	/*
	 * Metodo para actualizar la ruta absoluta del documento anexo carga al tramite por el sistema
	 * requestId - Id del tramite
	 * uploadedFilePath - Ruta absoluta del directorio
	 */
	int updateRequestUploadedFile(Long requestId, String uploadedFilePath);

	/*
	 * Metodo que obtiene la configuracion de la etapa actual de autorizacion de un folio
	 */
	Authorization getAuthorization(Request request, User user);
	
	/*
	 * Metodos accesores de catalogos requeridos en pantallas de captura
	 */
	 
	/* Listado de Partidas en base a llave programatica*/
	List<Entry> getEntries(Long programaticKey);
	
	/* Listado de Partidas en base a llave programatica y distrito*/
	List<Entry> getEntries(Long district, Long programaticKey);
	
	/* Listado de llaves programaticas a modo de Mapa */
	Map<Long, String> getProgramaticKeysMap();
	
	/* Listado de partidas a modo de mapa */
	Map<Long, String> getEntriesMap(Long programaticKey);
	
	/* Listado de tipos de movimiento a modo de mapa */
	Map<Long, String> getMovementTypesMap();
	
	/* Listado de meses a modo de Mapa */
	Map<Long, String> getMonthsMap();
	
	/* Obtener informacion de partida*/
	EntryDistrict getEntryBalance(Long districtId, Long entryId, Long month);
	
	/*Metodo para obtener el balance anual de una partida-distrito*/
	List<EntryDistrict> getAnnualEntryBalance(Long districtId, Long entryId);
}
