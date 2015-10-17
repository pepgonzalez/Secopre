package ideasw.secopre.web;

import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clas encargada de almacenar informacion que es consultada muchas veces dentro
 * del aplicativo secopre, pero solo ira a BD 1 sola ves de esta manera se evita
 * realizar consultas inecesarias.
 * 
 * @author jcano
 *
 */
@Component
public class SecopreCache {

	@Autowired
	public BaseService baseService;

	private static List<District> allDistricts = null;
	private static Map<User, List<District>> districtByUser = new HashMap<User, List<District>>();

	public List<District> getAlldistricts() {
		if (allDistricts == null) {
			allDistricts = baseService.findAll(District.class);
		}
		return allDistricts;
	}

	public List<District> getDistrictsByUser(User user,
			boolean forceUpdate) {
		List<District> listByUser = null;

		if (forceUpdate || !districtByUser.containsKey(user)) {
			// Aqui se busca la informacion de BD

			// Se actualiza el mapa

		}
		listByUser = districtByUser.get(user);

		return listByUser;

	}

	public List<District> getDistrictsByUser(User user) {

		return getDistrictsByUser(user, false);

	}
}
