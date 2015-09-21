package ideasw.secopre.web;

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
	
	//####Modulo de Tramites####//
	public static final String MV_TRAM_ADD = "auth/tramite/add";
	public static final String MV_TRAM_LIST = "auth/tramite/list";
	public static final String MV_TRAM_CAPTURE = "auth/tramite/capture";
	public static final String MV_TRAM_AUTH = "auth/tramite/authorization";

	//####Modulo de Catalogos Operativos####//
	public static final String MV_CAT_ENTRY = "auth/oper/entry/list";
	public static final String MV_CAT_ENTRY_ADD = "auth/oper/entry/add";
	
	
	//####Modulo de Catalogos####//
	public static final String MV_CAT_PERSON = "auth/catalog/person/persons";
	public static final String MV_CAT_PERSON_ADD = "auth/catalog/person/add";

	public static final String MV_CAT_DISTRICT = "auth/catalog/district/list";
	public static final String MV_CAT_DISTRICT_ADD = "auth/catalog/district/add";		

	public static final String MV_CAT_POSITION = "auth/catalog/position/list";
	public static final String MV_CAT_POSITION_ADD = "auth/catalog/position/add";			

	public static final String MV_CAT_ADDRESS = "auth/catalog/address/list";
	public static final String MV_CAT_ADDRESS_ADD = "auth/catalog/address/add";			

}
