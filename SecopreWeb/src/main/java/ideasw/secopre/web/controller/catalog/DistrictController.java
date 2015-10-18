package ideasw.secopre.web.controller.catalog;

import ideasw.secopre.model.catalog.District;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Distritos,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>district: Indica que la configuracion pertenece a Distritos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class DistrictController extends AuthController {

	@RequestMapping(value = "cat/district/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		District district = new District();
		model.addAttribute("districtList", baseService.findAll(District.class));
		model.addAttribute("district", district);
		
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("district") District district,ModelMap model) {
		try {
			baseService.save(district);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el Distrito:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DISTRICT;
	}	
}
