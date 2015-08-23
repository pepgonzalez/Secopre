package ideasw.secopre.service.impl;

import ideasw.secopre.dao.JpaDao;
import ideasw.secopre.dto.RenderSingleMenu;
import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	JpaDao jpaDao;

	@Override
	public User loadUserByUsername(String username) {
		List<User> users = jpaDao.findByProperty(User.class, "username",
				username);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RenderSingleMenu> getMenuByUserName(String username) {
		User user = loadUserByUsername(username);
		List<Role> roles = (List<Role>) user.getAuthorities();
		List<Menu> menus = new ArrayList<Menu>(0);

		if (roles != null && !roles.isEmpty()) {

			for (Role role : roles) {
				List<Permission> permissions = role.getPermissions();
				if (permissions != null && !permissions.isEmpty()) {
					for (Permission permission : permissions) {
						if (permission.getMenu() != null)
							menus.add(permission.getMenu());
					}
				}
			}
		}

		if (!menus.isEmpty()) {
			// Se ordenan los menus en base al Order
			Collections.sort(menus, new Comparator<Menu>() {
				@Override
				public int compare(Menu menu0, Menu menu1) {
					return Integer.valueOf(menu0.getOrder()).compareTo(
							Integer.valueOf(menu1.getOrder()));
				}
			});
			
			return buildMenu(menus);
		}
		return null;
	}

	private List<RenderSingleMenu> buildMenu(List<Menu> orderMenus) {
		RenderSingleMenu singleMenu = null;
		List<RenderSingleMenu> buildMenus = new ArrayList<RenderSingleMenu>(0);
		for (Menu menu : orderMenus) {
			// Solo se evaluan menus que no sean childs
			if (menu.getParentId() != null && menu.getParentId().intValue() > 0) {
				continue;
			}
			singleMenu = new RenderSingleMenu();
			singleMenu.setParent(menu);
			singleMenu.setChilds(getChilds(orderMenus, menu));
			buildMenus.add(singleMenu);
		}

		return buildMenus;
	}

	private List<Menu> getChilds(List<Menu> all, Menu menu) {
		final Menu finalMenu = menu;
		List<Menu> childs = (List<Menu>) CollectionUtils.select(all,
				new Predicate<Menu>() {
					@Override
					public boolean evaluate(Menu arg0) {
						return arg0.getParentId() != null
								&& arg0.getParentId().intValue() == finalMenu
										.getId().intValue();
					}

				});
		return childs;
	}
}
