package ideasw.secopre.sql;

public class SQLConstants {

	public static final String GET_FORMALITY_FROM_USER_ID =  "GET_FORMALITY_FROM_USER_ID";
	public static final String GET_FORMALITY_BY_ID =  "GET_FORMALITY_BY_ID";
	public static final String INSERT_REQUEST_CONFIG = "INSERT_REQUEST_CONFIG";
	public static final String GET_NEXT_CONSECUTIVE = "GET_NEXT_CONSECUTIVE";
	public static final String GET_REQUEST_NEXT_CONSECUTIVE = "GET_REQUEST_NEXT_CONSECUTIVE";
	public static final String GET_REQUEST_FIRST_WORKFLOW_CONFIG = "GET_REQUEST_FIRST_WORKFLOW_CONFIG";
	public static final String GET_STAGE_CONFIG_BY_ID = "GET_STAGE_CONFIG_BY_ID";
	public static final String INSERT_OR_UPDATE_REQUEST = "INSERT_OR_UPDATE_REQUEST";
	public static final String CLEAN_REQUEST_DETAIL = "CLEAN_REQUEST_DETAIL";
	public static final String INSERT_OR_UPDATE_REQUEST_DETAIL = "INSERT_OR_UPDATE_REQUEST_DETAIL";
	public static final String INSERT_REQUEST_HISTORY = "INSERT_REQUEST_HISTORY";
	public static final String GET_FORMALITY_INBOX = "GET_FORMALITY_INBOX";
	public static final String GET_MY_FORMALITY_INBOX = "GET_MY_FORMALITY_INBOX";
	public static final String GET_REQUEST_WORKFLOW_CONFIG = "GET_REQUEST_WORKFLOW_CONFIG";
	public static final String INACTIVATE_ACTIVE_STAGE = "INACTIVATE_ACTIVE_STAGE";
	public static final String GET_REQUEST_BY_ID = "GET_REQUEST_BY_ID";
	public static final String GET_REQUEST_CONFIG_BY_ID = "GET_REQUEST_CONFIG_BY_ID";
	public static final String CAN_USER_AUTHORIZE = "CAN_USER_AUTHORIZE";
	public static final String IS_USER_AUTHORIZATION_SUPERUSER = "IS_USER_AUTHORIZATION_SUPERUSER";
	public static final String HAS_MORE_SIGNATURES = "HAS_MORE_SIGNATURES";
	public static final String UPDATE_UPLOADED_FILE_IN_REQUEST = "UPDATE_UPLOADED_FILE_IN_REQUEST";
	public static final String UPDATE_REQUEST_DETAIL = "UPDATE_REQUEST_DETAIL";
	public static final String INSERT_REQUEST_DETAIL = "INSERT_REQUEST_DETAIL";
	public static final String GET_REQUEST_DETAIL = "GET_REQUEST_DETAIL";
	public static final String GET_REQUEST_HISTORY = "GET_REQUEST_HISTORY";
	public static final String GET_VALID_DISTRICTS = "GET_VALID_DISTRICTS";
	public static final String GET_VALID_DISTRICTS_BY_USER = "GET_VALID_DISTRICTS_BY_USER";
	public static final String GET_DISTRICT_LIST_BY_USER = "GET_DISTRICT_LIST_BY_USER";
	public static final String GET_USER_LIST_BY_DISTRICT = "GET_USER_LIST_BY_DISTRICT";	
	
	//Querys para clonacion de partidas
	public static final String VALIDATE_ENTRIES_NEXT_YEAR = "VALIDATE_ENTRIES_NEXT_YEAR";
	public static final String CLONE_ENTRIES = "CLONE_ENTRIES";
	
	//obtiene todas las partidas asociadas a un llave programatica con saldo disponible
	public static final String GET_ENTRY_DETAIL = "GET_ENTRY_DETAIL";
	public static final String GET_VALID_ENTRIES = "GET_VALID_ENTRIES";
	public static final String GET_DISTRICT_ENTRIES_JPQL = "GET_DISTRICT_ENTRIES_JPQL";
	public static final String GET_VALID_ENTRIES_BY_DISTRIC = "GET_VALID_ENTRIES_BY_DISTRIC";
	public static final String IS_USERNAME_VALID = "IS_USERNAME_VALID";
	public static final String GET_ROLE_LIST_BY_USER = "GET_ROLE_LIST_BY_USER";
	public static final String GET_PERMISSION_LIST_BY_ROLE = "GET_PERMISSION_LIST_BY_ROLE";
	public static final String GET_ROLE_LIST_BY_MENU = "GET_ROLE_LIST_BY_MENU";
	public static final String IS_PASSWORD_EXIST = "IS_PASSWORD_EXIST";
	
	//reportes
	public static final String GET_REPORT_LIST = "GET_REPORT_LIST";
	public static final String GET_REPORT_BY_ID = "GET_REPORT_BY_ID";
	public static final String GET_REPORT_RESOURCE = "GET_REPORT_RESOURCE";
	public static final String GET_SUBREPORTS_BY_ID = "GET_SUBREPORTS_BY_ID";
	public static final String GET_REPORT_PARAMETERS = "GET_REPORT_PARAMETERS";
	public static final String GET_CREATED_FORMALITIES_BY_USER = "GET_CREATED_FORMALITIES_BY_USER";
	public static final String GET_USER_MOVEMENT_ACTIONS = "GET_USER_MOVEMENT_ACTIONS";
	
	//presupuesto anual
	public static final String GET_ENTRY_DISTRICT = "GET_ENTRY_DISTRICT";
	
	//validacion fechas de corte
	public static final String IS_VALID_DATE_FOR_CAPTURE = "IS_VALID_DATE_FOR_CAPTURE";
	public static final String HAS_USER_ROLE = "HAS_USER_ROLE";
	public static final String GET_ACTIVE_REQUESTS_IN_CAPTURE = "GET_ACTIVE_REQUESTS_IN_CAPTURE";
	
	//para trabajar con las properties
	public static final String GET_PROPERTY_BY_CODE = "GET_PROPERTY_BY_CODE";
	
	//authorizaciones automaticas
	public static final String GET_AUTOMATIC_AUTHORIZATION = "GET_AUTOMATIC_AUTHORIZATION";
	
	//notificaciones
	public static final String GET_NOTIFICATIONS_BY_USER = "GET_NOTIFICATIONS_BY_USER";
	
}
