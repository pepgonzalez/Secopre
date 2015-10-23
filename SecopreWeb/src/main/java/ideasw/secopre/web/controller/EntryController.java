package ideasw.secopre.web.controller;

import java.util.HashMap;
import java.util.List;

import ideasw.secopre.enums.AccountingType;
import ideasw.secopre.model.Entry;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.Position;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de partidas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>oper: Indica que el controller es operativo</li>
 * <li>entry: Indica que la configuracion pertenece a Partidas</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class EntryController extends AuthController {

	@RequestMapping(value = "oper/entry/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Entry entry = new Entry();
		model.addAttribute("entryList", baseService.findAll(Entry.class));
		model.addAttribute("entry", entry);
		List<ProgrammaticKey> programmaticKeyList = baseService.findAll(ProgrammaticKey.class);
		
		HashMap<Long, String> pkMap = new HashMap<Long, String>();
		for (ProgrammaticKey p : programmaticKeyList) {
			pkMap.put(p.getId(),p.getCode());
		}

		model.addAttribute("accountingTypes", AccountingType.values());
		model.addAttribute("pks", pkMap);
	
		return SecopreConstans.MV_CAT_ENTRY;
	}
	
	@RequestMapping(value = "oper/entry/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("entry") Entry entry ,ModelMap model) {
		try {
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
	
	@RequestMapping(value = "cat/entry/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Entry entry = baseService.findById(Entry.class , id);
			if (entry!=null){
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
	
	
	@RequestMapping(value = "cat/entry/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Entry entry = baseService.findById(Entry.class , id);
		
	    List<ProgrammaticKey> programmaticKeyList = baseService.findAll(ProgrammaticKey.class);
		
		HashMap<Long, String> pkMap = new HashMap<Long, String>();
		for (ProgrammaticKey p : programmaticKeyList) {
			pkMap.put(p.getId(),p.getCode());
		}
		model.addAttribute("pks", pkMap);
		model.addAttribute("entry", entry);
		
		return SecopreConstans.MV_CAT_ENTRY_ADD;
	}
	
	@RequestMapping(value = "cat/entry/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		Entry entryEdit = baseService.findById(Entry.class , id);
		entryEdit.setActivo(activo);
		baseService.save(entryEdit);
		
		List<ProgrammaticKey> programmaticKeyList = baseService.findAll(ProgrammaticKey.class);
		
		HashMap<Long, String> pkMap = new HashMap<Long, String>();
		for (ProgrammaticKey p : programmaticKeyList) {
			pkMap.put(p.getId(),p.getCode());
		}

		model.addAttribute("accountingTypes", AccountingType.values());
		model.addAttribute("pks", pkMap);
	
		return SecopreConstans.MV_CAT_ENTRY_LIST;
	}
}
