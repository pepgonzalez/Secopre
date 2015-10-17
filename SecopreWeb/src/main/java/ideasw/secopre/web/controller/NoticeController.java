package ideasw.secopre.web.controller;


import ideasw.secopre.model.Notice;
import ideasw.secopre.model.catalog.Position;
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
 * Controller principal encargada de administrar el catalogo de Avisos,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>position: Indica que la configuracion pertenece a Puestos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class NoticeController extends AuthController {

	@RequestMapping(value = "cat/notice/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Notice notice = new Notice();
		model.addAttribute("noticeList", baseService.findAll(Notice.class));
		model.addAttribute("notice", notice);
		return SecopreConstans.MV_CAT_NOTICE;
	}
	
	@RequestMapping(value = "cat/notice/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Notice notice = baseService.findById(Notice.class , id);
		model.addAttribute("notice", notice);
		return SecopreConstans.MV_CAT_NOTICE;
	}
	
	@RequestMapping(value = "cat/notice/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("notice") Position notice, ModelMap model,  @RequestParam("id") Long id ) {
		try {
			baseService.save(notice);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el aviso:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_NOTICE;
	}
	
	@RequestMapping(value = "cat/notice/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Notice notice = baseService.findById(Notice.class , id);
			if (notice!=null){
				baseService.remove(notice);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el aviso:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_NOTICE;
	}
	
	
}
