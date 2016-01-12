package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;
import ideasw.secopre.model.security.Permission;

public class PermissionMapper implements RowMapper<Object> {    
	 
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Permission permission = new Permission();
		permission.setId(rs.getLong("PERMISSION_ID"));
		permission.setName(rs.getString("NAME"));
		Path path = new Path();
		path.setId(rs.getLong("PATH_ID"));
	    path.setUrl(rs.getString("PATH_URL"));
	    Menu menu = new Menu();
	    menu.setId(rs.getLong("MENU_ID"));
	    menu.setCssClass(rs.getString("MENU_CSSCLASS"));
	    menu.setDescription(rs.getString("MENU_DESCRIPTION"));
	    menu.setActive(rs.getBoolean("MENU_ACTIVE"));
	    menu.setJsId(rs.getString("MENU_JSID"));
	    menu.setName(rs.getString("MENU_NAME"));
	    menu.setOrder (rs.getInt("MENU_ORDER"));
	    menu.setParentId(rs.getLong("MENU_PARENT_ID"));
        menu.setJsFunction(rs.getString("MENU_JSFUNCTION"));
        path.setMenu(menu);
		permission.setPath(path);
		return permission;   
	 }    
} 