package ideasw.secopre.web;

import java.io.File;

public class SecopreConstans {
	public static final String USER_IN_SESSION = "User_In_Session"; 
	//####Constantes de vistas####//
	public static final String MV_START = "/auth/start";
	public static final String MV_LOGIN = "login";
	public static final String REDIRECT_ROOT_AUTH = "redirect:auth/";
	public static final String AUTH_INDEX = "auth/common/layout";
	public static final String MV_403 = "403";
	
	//####Modulo Administracion Usuarios####//
	public static final String MV_ADM_USR_LIST = "auth/admin/users/list";
	public static final String MV_ADM_USR = "auth/admin/users/users";
	public static final String MV_ADM_USR_EDIT = "auth/admin/users/edit";
	public static final String MV_ADM_USR_ADD = "auth/admin/users/add";
	public static final String MV_ADM_DIR = "auth/admin/dir/directory";
	
	//####Modulo Administracion Roles####//
	public static final String MV_ADM_ROLE_LIST = "auth/admin/config/role/list";
	public static final String MV_ADM_ROLE = "auth/admin/config/role/roles";
	public static final String MV_ADM_ROLE_ADD = "auth/admin/config/role/add";

	//####Modulo Administracion Permisos####//
	public static final String MV_ADM_PERMISSION = "auth/admin/config/perm/perms";
	public static final String MV_ADM_PERMISSION_LIST = "auth/admin/config/perm/list";
	public static final String MV_ADM_PERMISSION_ADD = "auth/admin/config/perm/add";
	
	//####Modulo Administracion Menus####//
	public static final String MV_ADM_MENU_LIST = "auth/admin/config/menu/list";
	public static final String MV_ADM_MENU = "auth/admin/config/menu/menus";
	public static final String MV_ADM_MENU_ADD = "auth/admin/config/menu/add";
	
	
	//####Modulo de Tramites####//
	public static final String MV_TRAM_ADD = "auth/tramite/add";
	public static final String MV_TRAM_LIST = "auth/tramite/list";
	public static final String MV_TRAM_MY_LIST = "auth/tramite/myList";
	public static final String MV_TRAM_RECTIFICATION = "auth/tramite/rectification";
	public static final String MV_TRAM_CAPTURE = "auth/tramite/capture";
	public static final String MV_TRAM_AUTH = "auth/tramite/authorization";
	public static final String MV_TRAM_UPLOAD = "auth/tramite/upload";
	public static final String MV_TRAM_HISTORY = "auth/tramite/history";
	public static final String MV_TRAM_DUE_DATES = "auth/tramite/duedates";
	public static final String MV_TRAM_BALANCE = "auth/tramite/balance";
	public static final String MV_TRAM_CURRENT_TOTAL = "auth/tramite/currentTotal";
	public static final String MV_TRAM_PK = "auth/tramite/pk";
	public static final String MV_TRAM_LIST_REDIRECT = "redirect:/auth/tram/list";
	public static final String MV_TRAM_CAPTURE_REDIRECT = "redirect:/auth/tramite/capture/";
	public static final String MV_TRAM_EXCEPTION = "auth/tramite/exception";
	
	//####Modulo de reportes
	public static final String MV_REPORT_LIST = "auth/report/list";
	public static final String MV_REPORT_PARAMS = "auth/report/params";
	public static final String MV_REPORT_LIST_REDIRECT = "redirect:auth/report/list";
	
	
	//####Constantes de carga de archivos####//
	public static final String SECOPRE_RESOURCES_WINDOWS_PATH = "C:"+File.separator+"SecopreResources";
	public static final String SECOPRE_RESOURCES_LINUX_PATH = File.separator+"opt"+File.separator+"SecopreResources";
	public static final String DEFAULT_UPLOAD_FILE_NEXT_STAGE = "SOLCOMP";
	
	//####Modulo de Catalogos Operativos####//
	public static final String MV_CAT_ENTRY = "auth/oper/entry/entry";
	public static final String MV_CAT_ENTRY_ADD = "auth/oper/entry/add";
	public static final String MV_CAT_ENTRY_LIST = "auth/oper/entry/list";
	
	//####Modulo de Configuracion de Partidas####//
	public static final String MV_CONF_ENTRY = "auth/admin/config/entry/entry";
	public static final String MV_CONF_ENTRY_LIST = "auth/admin/config/entry/list";
	
	
	public static final String MV_CAT_PK = "auth/oper/pk/programmaticKey";
	public static final String MV_CAT_PK_ADD = "auth/oper/pk/add";
	public static final String MV_CAT_PK_LIST = "auth/oper/pk/list";
	
	
	public static final String MV_CAT_PERSON = "auth/catalog/person/persons";
	public static final String MV_CAT_PERSON_ADD = "auth/catalog/person/add";
	public static final String MV_CAT_PERSON_LIST = "auth/catalog/person/list";

	public static final String MV_CAT_DISTRICT = "auth/catalog/district/district";
	public static final String MV_CAT_DISTRICT_ADD = "auth/catalog/district/add";	
	public static final String MV_CAT_DISTRICT_LIST = "auth/catalog/district/list";
	public static final String MV_CAT_DISTRICT_EDIT = "auth/catalog/district/edit";

	public static final String MV_CAT_POSITION = "auth/catalog/position/position";
	public static final String MV_CAT_POSITION_ADD = "auth/catalog/position/add";
	public static final String MV_CAT_POSITION_LIST = "auth/catalog/position/list";

	public static final String MV_CAT_ADDRESS = "auth/catalog/address/list";
	public static final String MV_CAT_ADDRESS_ADD = "auth/catalog/address/add";		
	
	public static final String MV_CAT_NOTICE = "auth/oper/notice/notices";
	public static final String MV_CAT_NOTICE_ADD = "auth/oper/notice/add";
	public static final String MV_CAT_NOTICE_LIST = "auth/oper/notice/list";
	
	//####Modulo de Catalogos Parametricos####//
	public static final String MV_CAT_DUEDATE = "auth/param/dueDate/dueDate";
	public static final String MV_CAT_DUEDATE_ADD = "auth/param/dueDate/add";	
	public static final String MV_CAT_DUEDATE_LIST = "auth/param/dueDate/list";	
	
	//####Modulo de Catalogos Director####//
	public static final String MV_CAT_DIRECTOR      = "auth/param/director/director";
	public static final String MV_CAT_DIRECTOR_ADD  = "auth/param/director/add";	
	public static final String MV_CAT_DIRECTOR_LIST = "auth/param/director/list";	
	
	//####Modulo de Perfil Usuario####//
	public static final String MV_ADM_PROFILE = "auth/admin/profile/profiles";
	public static final String MV_ADM_PROFILE_ACCOUNT = "auth/admin/profile/profileAccount";
	
	//####Modulo de presupuesto anual
	public static final String MV_ADM_BUDGET = "auth/admin/config/budget/list";
	public static final String MV__ADM_BUDGET_REDIRECT = "redirect:/auth/adm/bugget";

	//####Modulo de presupuesto anual
	public static final String MV_ADM_SCHEDULING = "auth/admin/config/scheduling/list";
	public static final String MV_ADM_SCHEDULING_REDIRECT = "redirect:/auth/adm/sche";	
	
		
}
