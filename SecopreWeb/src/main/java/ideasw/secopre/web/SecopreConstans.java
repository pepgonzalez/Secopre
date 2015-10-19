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
	public static final String MV_ADM_USR_LIST = "auth/admin/usr/list";
	public static final String MV_ADM_USR = "auth/admin/users/users";
	public static final String MV_ADM_DIR = "auth/admin/dir/directory";
	
	//####Modulo Administracion Roles####//
	public static final String MV_LIST = "list";
	public static final String MV_ADM_ROLE = "auth/admin/config/role/roles";

	//####Modulo Administracion Permisos####//
	public static final String MV_ADM_PERMISSION = "auth/admin/config/perm/perms";
	
	//####Modulo Administracion Menus####//
	public static final String MV_ADM_MENU_LIST = "auth/admin/config/menu/list";
	public static final String MV_ADM_MENU = "auth/admin/config/menu/menus";
	
	
	//####Modulo de Tramites####//
	public static final String MV_TRAM_ADD = "auth/tramite/add";
	public static final String MV_TRAM_LIST = "auth/tramite/list";
	public static final String MV_TRAM_CAPTURE = "auth/tramite/capture";
	public static final String MV_TRAM_AUTH = "auth/tramite/authorization";
	public static final String MV_TRAM_UPLOAD = "auth/tramite/upload";
	public static final String MV_TRAM_HISTORY = "auth/tramite/history";
	public static final String MV_TRAM_LIST_REDIRECT = "redirect:/auth/tram/list";
	
	
	//####Constantes de carga de archivos####//
	public static final String SECOPRE_RESOURCES_WINDOWS_PATH = "C:"+File.separator+"SecopreResources";
	public static final String SECOPRE_RESOURCES_LINUX_PATH = File.separator+"SecopreResources";
	public static final String DEFAULT_UPLOAD_FILE_NEXT_STAGE = "SOLCOMP";
	
	//####Modulo de Catalogos Operativos####//
	public static final String MV_CAT_ENTRY = "auth/oper/entry/entry";
	public static final String MV_CAT_ENTRY_ADD = "auth/oper/entry/add";
	
	public static final String MV_CAT_PK = "auth/oper/pk/programmaticKey";
	public static final String MV_CAT_PK_ADD = "auth/oper/pk/add";
	
	
	public static final String MV_CAT_PERSON = "auth/catalog/person/persons";
	public static final String MV_CAT_PERSON_ADD = "auth/catalog/person/add";

	public static final String MV_CAT_DISTRICT = "auth/catalog/district/district";
	public static final String MV_CAT_DISTRICT_ADD = "auth/catalog/district/add";	
	public static final String MV_CAT_DISTRICT_LIST = "auth/catalog/district/list";

	public static final String MV_CAT_POSITION = "auth/catalog/position/position";
	public static final String MV_CAT_POSITION_ADD = "auth/catalog/position/add";			

	public static final String MV_CAT_ADDRESS = "auth/catalog/address/list";
	public static final String MV_CAT_ADDRESS_ADD = "auth/catalog/address/add";		
	
	public static final String MV_CAT_NOTICE = "auth/catalog/notice/notice";
	public static final String MV_CAT_NOTICE_ADD = "auth/catalog/notice/add";	
	
	//####Modulo de Catalogos Parametricos####//
	public static final String MV_CAT_DUEDATE = "auth/param/dueDate/dueDate";
	public static final String MV_CAT_DUEDATE_ADD = "auth/param/dueDate/add";	
	
	
		
}
