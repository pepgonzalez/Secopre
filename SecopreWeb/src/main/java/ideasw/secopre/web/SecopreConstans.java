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
	public static final String MV_TRAM_MOVS = "auth/tramite/movements";
	
	//####Modulo de Catalogos####//
	public static final String MV_CAT_PERSON = "auth/catalog/person/persons";
	public static final String MV_CAT_PERSON_ADD = "auth/catalog/person/add";

		
	
}
