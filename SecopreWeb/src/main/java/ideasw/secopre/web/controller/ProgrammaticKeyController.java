package ideasw.secopre.web.controller;

import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.security.User;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Claves
 * Programaticas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>oper: Indica que el controller es operativo</li>
 * <li>pk: Indica que la configuracion pertenece a Clave Programatica</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class ProgrammaticKeyController extends AuthController {
	
	@RequestMapping(value = "oper/pk/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		ProgrammaticKey pk = new ProgrammaticKey();
		model.addAttribute("pkList", baseService.findAll(ProgrammaticKey.class));
		model.addAttribute("pk", pk);
		return SecopreConstans.MV_CAT_PK;
	}
	
	@RequestMapping(value = "oper/pk/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("pk") ProgrammaticKey pk, ModelMap model) {
		try {
			if(pk.getId()==null)
			{
               pk.setActivo(Boolean.TRUE);
			}
			else
			{
				ProgrammaticKey pkEdit = baseService.findById(ProgrammaticKey.class , pk.getId());
				pkEdit.setYear(pk.getYear());
				pkEdit.setRamo(pk.getRamo());
				pkEdit.setUnitResponsable(pk.getUnitResponsable());
				pkEdit.setProgram(pk.getProgram());
				pkEdit.setFinancingSource(pk.getFinancingSource());
				pkEdit.setFunctionalGroup(pk.getFunctionalGroup());
				pkEdit.setActivity(pk.getActivity());
				pkEdit.setFunction(pk.getFunction());
				pkEdit.setProgramBudget(pk.getProgramBudget());
				pkEdit.setSubfunction(pk.getSubfunction());
				pkEdit.setExpenseType(pk.getExpenseType());			
				pkEdit.setFinality(pk.getFinality());
				pkEdit.setCode(pk.getCode());
				pk=pkEdit;
			}
			
			baseService.save(pk);
		} catch (Exception e) {	
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el puesto:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_PK_LIST;
	}
	
	@RequestMapping(value = "oper/pk/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		ProgrammaticKey pk = baseService.findById(ProgrammaticKey.class , id);
		//model.addAttribute("positionList", baseService.findAll(Position.class));
		model.addAttribute("pk", pk);
		return SecopreConstans.MV_CAT_PK_ADD;
	}
	
	@RequestMapping(value = "oper/pk/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			ProgrammaticKey pk = baseService.findById(ProgrammaticKey.class , id);
			if (pk!=null){
				baseService.remove(pk);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar la clave programática:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_PK_LIST;
	}
	
	@RequestMapping(value = "oper/pk/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		ProgrammaticKey pkEdit = baseService.findById(ProgrammaticKey.class , id);
		pkEdit.setActivo(activo);
		baseService.save(pkEdit);
		
		ProgrammaticKey pk = new ProgrammaticKey();
		model.addAttribute("pkList", baseService.findAll(ProgrammaticKey.class));
		model.addAttribute("pk", pk);
		return SecopreConstans.MV_CAT_PK_LIST;
	}
}
