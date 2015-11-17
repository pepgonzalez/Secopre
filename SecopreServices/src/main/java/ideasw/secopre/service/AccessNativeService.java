package ideasw.secopre.service;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.dto.UserMovement;
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
	 * Metodo que obtiene el listado de reportes disponibles para un usuario
	 */
	List<Report> getReportList(User user) throws Exception;
	
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
	
	/*Metodo para obtener solamente los tramites con presupuesto asignado en entry district*/
	List<District> getValidDistricts();
	
	/*Metodo para validar si un username ya existe*/
	int isUsernameValid(String username);
	
	/*Metodo para obtener la fuente de datos secopre*/
	Connection getSecopreDSConnection() throws SQLException;
	
	/*Metodo para obtener la fuente de datos historica*/
	Connection getTsadbitntstDataSourceConnection() throws SQLException;
	
	/*Metodo para obtener los roles de un usuario*/
     List<Role> getRolesByUser(Long userId);
     
 	/*Metodo para obtener los permisos de un role*/
     List<Permission> getPermissionsByRole(Long idRole);
     
 	/*Metodo para obtener los roles de un menu*/
     List<Role> getRolesByMenu(Long idMenu);
     
     /*Obtener reporte y parametero en base a id*/
     Report getReportById(Long reportId) throws Exception;
     
     /*Metodo para obtener los parametros de un reporte*/
     List<ReportParameter> getParametersById(Long reportId) throws Exception;
     
     /*Servicio para obtener las ultimas partidas creadas por el usuario*/
     List<UserMovement> getCreatedFormalitiesByUserId(Long userId, int totalMovements);
     
     /*Servicio para obtener todas las acciones realizadas por el usuario*/
     List<UserMovement> getMovementUserActions(Long userId, int totalMovements);
     
     /*Servicio para obtener todas los distritos que pertenece a un usuario*/
     List<District> getDistrictsByUser(Long userId);

     
     List<Entry> getValidEntriesByDistrict(Long districtId);

     /*Servicio para obtener todas los usuarios que pertenece a un distrito*/
     List<User> getUsersByDistrict(Long districtId);
     
 	/*Metodo para validar si el password de un usuario coincide con el actual*/
 	int isPasswordExist(String password, Long userId);
 	
 	/*Metodo para obtener un listado de partidas*/
 	public Map<Long, String> getEntriesMap();
 	
 	/*Metodo para obtener un listado de distritos*/
 	public Map<Long, String> getDistrictsMap();

	List<EntryDistrict> getEntryDistrict();
}
