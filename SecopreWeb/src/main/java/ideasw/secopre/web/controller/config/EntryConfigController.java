package ideasw.secopre.web.controller.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ideasw.secopre.web.controller.base.AuthController;

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
@RequestMapping("/cfg/entry")
public class EntryConfigController extends AuthController {

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
