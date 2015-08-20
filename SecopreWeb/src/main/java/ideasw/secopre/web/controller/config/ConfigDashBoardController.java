package ideasw.secopre.web.controller.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ideasw.secopre.web.controller.base.AuthController;

/**
 * Controller principal encargado del modulo de configuracion,dentro de este
 * controller se podran configurar los dashboards disponibles para la aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cfg: Indica que esta en el modulo de configuracion</li>
 * <li>db: Indica que la configuracion pertenece a DashBoard</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
@RequestMapping("/cfg/db")
public class ConfigDashBoardController extends AuthController {

}
