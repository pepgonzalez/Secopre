package ideasw.secopre.web.controller;

import ideasw.secopre.model.Notice;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.DashboardService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Autowired
	DashboardService dashboardService;

	@RequestMapping(value = "oper/notice/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Notice notice = new Notice();
		model.addAttribute("noticeList", baseService.findAll(Notice.class));
		model.addAttribute("notice", notice);
		model.addAttribute("districts", secopreCache.getAlldistricts());
		return SecopreConstans.MV_CAT_NOTICE;
	}

	@RequestMapping(value = "oper/notice/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id) {
		Notice notice = baseService.findById(Notice.class, id);
		model.addAttribute("notice", notice);
		model.addAttribute("districts", secopreCache.getAlldistricts());

		return SecopreConstans.MV_CAT_NOTICE_ADD;
	}

	@RequestMapping(value = "oper/notice/changeStatus", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String changeStatus(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id, @RequestParam("activo") Boolean activo) {
		Notice noticeEdit = baseService.findById(Notice.class, id);
		noticeEdit.setActivo(activo);
		baseService.save(noticeEdit);

		Notice notice = new Notice();
		model.addAttribute("noticeList", baseService.findAll(Notice.class));
		model.addAttribute("notice", notice);
		model.addAttribute("districts", secopreCache.getAlldistricts());

		return SecopreConstans.MV_CAT_NOTICE_LIST;
	}

	@RequestMapping(value = "oper/notice/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("notice") Notice notice,
			@RequestParam("districts") String district, ModelMap model) {
		try {

			List<District> distritList = new ArrayList<District>();
			List<String> items = Arrays.asList(district.split("\\s*,\\s*"));

			for (String distrid : items) {
				District distr = baseService.findById(District.class,
						Long.parseLong(distrid));
				distritList.add(distr);
				notice.setDistrs(distritList);
				notice.setActivo(Boolean.TRUE);
			}
			notice.setActivo(Boolean.TRUE);
			baseService.save(notice);
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el aviso:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_NOTICE_LIST;
	}

	@RequestMapping(value = "oper/notice/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("id") Long id) {
		try {
			Notice notice = baseService.findById(Notice.class, id);
			if (notice != null) {
				baseService.remove(notice);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el aviso:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_NOTICE_LIST;
	}

	@RequestMapping(value = "oper/notice/getNotice")
	public @ResponseBody Notice getNotice(ModelMap modelMap, Principal principal) {
		User user = secopreCache.getUser(principal.getName());
		Notice notice = dashboardService.getNotice(user,
				secopreCache.getDistrictsByUser(user));
		return notice;
	}

}
