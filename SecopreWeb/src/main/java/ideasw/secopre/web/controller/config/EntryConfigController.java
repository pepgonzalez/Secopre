package ideasw.secopre.web.controller.config;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargado del modulo de configuracion,dentro de este
 * controller se podran configurar las opciones disponibles para partidas
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cfg: Indica que esta en el modulo de configuracion</li>
 * <li>entry: Indica que la configuracion pertenece a DashBoard</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class EntryConfigController extends AuthController {

	@Autowired
	private EntryConfigService entryConfigService;

	@RequestMapping(value = "cfg/entry/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(@ModelAttribute("balance") EntryBalance balance,
			ModelMap model, Principal principal) {


		model.addAttribute("entryFilter", new EntryFilter());
		model.addAttribute("districtList",
				secopreCache.getDistrictsByUserMap(principal.getName()));

		return SecopreConstans.MV_CONF_ENTRY;
	}

	@RequestMapping(value = "cfg/entry/search", method = RequestMethod.POST)
	public String searchEntries(
			@ModelAttribute("entryFilter") EntryFilter entryFilter,
			ModelMap model, RedirectAttributes attributes) {
		EntryBalance balance = entryConfigService.getEntryBalance(entryFilter);
		model.addAttribute("balance", balance);
		attributes.addFlashAttribute("balance", balance);

		return "redirect:/auth/cfg/entry/list";
	}

	@RequestMapping(value = "cfg/entry/byDistrict")
	public @ResponseBody Map<Long, String> getEntriesByDistrict(
			@RequestParam(value = "districtId", required = true) Long districtId,
			ModelMap modelMap) {
		Map<Long, String> entryMap = new HashMap<Long, String>();
		Map<String, Object> propertiesMap = new HashMap<String, Object>();

		propertiesMap.put("district",
				baseService.findById(District.class, districtId));
		List<Entry> entryList = accessNativeService
				.getValidEntriesByDistrict(districtId);

		for (Entry item : entryList) {
			entryMap.put(item.getId(), item.getName());
		}
		return entryMap;
	}

	@RequestMapping(value = "cfg/entry/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("entry") Entry entry, ModelMap model) {
		try {
			entry.setActivo(Boolean.TRUE);
			baseService.save(entry);
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el puesto:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_ENTRY_LIST;
	}

	@RequestMapping(value = "cfg/entry/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("id") Long id) {
		try {
			Entry entry = baseService.findById(Entry.class, id);
			if (entry != null) {
				baseService.remove(entry);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar la Partida:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_ENTRY_LIST;
	}

	@RequestMapping(value = "cfg/entry/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id) {
		Entry entry = baseService.findById(Entry.class, id);

		List<ProgrammaticKey> programmaticKeyList = baseService
				.findAll(ProgrammaticKey.class);

		HashMap<Long, String> pkMap = new HashMap<Long, String>();
		for (ProgrammaticKey p : programmaticKeyList) {
			pkMap.put(p.getId(), p.getCode());
		}
		model.addAttribute("pks", pkMap);
		model.addAttribute("entry", entry);

		return SecopreConstans.MV_CAT_ENTRY_ADD;
	}
	// TODO: Realizar los siguientes Metodos
	/**
	 * Clonar partidas: este metodo debe clonar los siguientes elementos: Claves
	 * Programaticas del año en curso y asignarle el año siguiente Partidas
	 * asignadas a las claves actuales, ligarlas a la nuevas claves
	 * programaticas.
	 * 
	 * Generar Template XLSX: este metodo debe generar un template para que
	 * permita actualizar el saldo anual asignado, la distribucion de saldo es
	 * proporcional en todos los meses
	 * 
	 * Actualizar Saldos de Partidas: este metodo debe permitir recibir el
	 * archivo generado en el metodo anterior y actualizar las partidas con la
	 * informacion asignada
	 * 
	 * Borrar/Agregar/Editar Se realizara por la opcion de catalogos
	 */

}
