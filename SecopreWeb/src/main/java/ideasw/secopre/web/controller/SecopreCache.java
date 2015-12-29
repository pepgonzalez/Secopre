package ideasw.secopre.web.controller;

import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("singleton")
public class SecopreCache {

	@Autowired
	public BaseService baseService;

	@Autowired
	private AccessNativeService accessNativeService;

	private static List<District> allDistricts = null;
	private static List<District> validDistricts = null;
	private static List<District> validDistrictsByUser = null;
	private static Map<User, List<District>> districtByUser = new HashMap<User, List<District>>();
	private static List<State> allStates = null;
	private static Map<Long, String> allStateMap = new HashMap<Long, String>();
	private static Map<Long, String> validDistrictsMap = new HashMap<Long, String>();
	private static Map<Long, String> validDistrictsMapByUser = new HashMap<Long, String>();

	/**
	 * Metodo que ejecuta la configuracion de las constantes que utilizara
	 * secopre
	 */
	public void onInit() {
		getAlldistricts();
		getAllStates();

	}

	public List<State> getAllStates() {
		if (allStates == null) {
			allStates = baseService.findAll(State.class);
		}
		return allStates;
	}

	public Map<Long, String> getAllStatesMap() {
		if (allStateMap.isEmpty()) {

			for (State state : getAllStates()) {
				allStateMap.put(state.getId(), state.getName());
			}
		}
		return allStateMap;
	}

	public List<District> getAlldistricts() {
		if (allDistricts == null) {
			allDistricts = baseService.findAll(District.class);
		}
		return allDistricts;
	}

	public List<District> getDistrictsByUser(User user, boolean forceUpdate) {
		List<District> listByUser = null;

		if (forceUpdate || !districtByUser.containsKey(user)) {
			// Aqui se busca la informacion de BD

			if (user.getId() == null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("username", user.getUsername());
				user = baseService.findByProperties(User.class, params).get(0);
			}

			List<District> districts = user.getDistricts();
			// Se actualiza el mapa
			districtByUser.put(user, districts);
		}
		listByUser = districtByUser.get(user);

		return listByUser;

	}

	public List<District> getDistrictsByUser(User user) {

		return getDistrictsByUser(user, true);

	}

	public Map<Long, String> getStateByUserMap(String username) {
		List<District> districts = getDistrictsByUser(username);
		Map<Long, String> statesMap = new HashMap<Long, String>();
		for (District item : districts) {
			statesMap.put(item.getState().getId(), item.getState().getName());
		}
		return statesMap;
	}

	public Map<Long, String> getDistrictsByUserMap(String username) {
		List<District> districts = getDistrictsByUser(username);
		Map<Long, String> districtsMap = new HashMap<Long, String>();
		for (District item : districts) {
			districtsMap.put(item.getId(), item.getEntity() + " DTO-"
					+ item.getNumber());
		}
		return districtsMap;
	}

	public List<District> getDistrictsByUser(String username) {

		return getDistrictsByUser(getUser(username), true);

	}

	public User getUser(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		return baseService.findByProperties(User.class, params).get(0);
	}

	public Map<Long, String> getValidDistrictsMap() {
		if (validDistrictsMap.isEmpty()) {
			for (District district : getValidDistricts()) {
				validDistrictsMap.put(district.getId(), district.getNumber());
			}
		}

		return validDistrictsMap;
	}
	
	public List<District> getValidDistricts() {

		if (validDistricts == null) {
			validDistricts = accessNativeService.getValidDistricts();
		}
		return validDistricts;
	}
	
	
	/* filtro de distritos por asignacion a usuario*/
	public Map<Long, String> getValidDistrictsMapByUserId(Long userId) {
		if (validDistrictsMapByUser.isEmpty()) {
			for (District district : getValidDistrictsByUser(userId)) {
				validDistrictsMapByUser.put(district.getId(), district.getNumber());
			}
		}

		return validDistrictsMapByUser;
	}
	
	public List<District> getValidDistrictsByUser(Long userId) {

		if (validDistrictsByUser == null) {
			validDistrictsByUser = accessNativeService.getValidDistrictsByUserId(userId);
		}
		return validDistrictsByUser;
	}

}
