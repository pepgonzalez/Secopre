package ideasw.secopre.web.controller.catalog;


import java.util.HashMap;
import java.util.List;

import ideasw.secopre.model.catalog.Position;
import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;
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
 * Controller principal encargada de administrar el catalogo de puestos,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>position: Indica que la configuracion pertenece a Puestos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class PositionController extends AuthController {

	@RequestMapping(value = "cat/position/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Position position = new Position();
		model.addAttribute("positionList", baseService.findAll(Position.class));
		model.addAttribute("position", position);
		return SecopreConstans.MV_CAT_POSITION;
	}
	
	@RequestMapping(value = "cat/position/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Position position = baseService.findById(Position.class , id);
		//model.addAttribute("positionList", baseService.findAll(Position.class));
		model.addAttribute("position", position);
		return SecopreConstans.MV_CAT_POSITION_ADD;
	}
	
	@RequestMapping(value = "cat/position/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("position") Position position, ModelMap model,  @RequestParam("id") Long id ) {
		try {
			position.setActivo(Boolean.TRUE);
			baseService.save(position);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el puesto:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_POSITION_LIST;
	}
	
	@RequestMapping(value = "cat/position/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Position position = baseService.findById(Position.class , id);
			if (position!=null){
				baseService.remove(position);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el puesto:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_POSITION_LIST;
	}
	
	@RequestMapping(value = "cat/position/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		Position positionEdit = baseService.findById(Position.class , id);
		positionEdit.setActivo(activo);
		baseService.save(positionEdit);
		
		Position position = new Position();
		model.addAttribute("positionList", baseService.findAll(Position.class));
		model.addAttribute("position", position);
		return SecopreConstans.MV_CAT_POSITION_LIST;
	}
	
	
}
