package ideasw.secopre.web.controller.catalog;

import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de direcciones,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>address: Indica que la configuracion pertenece a Direcciones</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class AddressController extends AuthController {

	@RequestMapping(value = "cat/address/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Address address = new Address();
		model.addAttribute("addressList", baseService.findAll(Address.class));
		model.addAttribute("address", address);
		return SecopreConstans.MV_CAT_ADDRESS;
	}
}
