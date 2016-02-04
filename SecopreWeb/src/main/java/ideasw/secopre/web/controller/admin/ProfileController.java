package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.UserMovement;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.catalog.Position;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.utils.encryption.Encryption;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada del modulo de administracion de
 * usuarios,dentro de este controller se podran configurar los usuarios de la
 * aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>adm: Indica que esta en el modulo de administracion</li>
 * <li>usr: Indica que la configuracion pertenece a Usuarios</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class ProfileController extends AuthController {
	private static final int IMG_WIDTH = 500;
	private static final int IMG_HEIGHT = 500;

	static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AccessService accessService;

	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "adm/profile/show", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String show(ModelMap model, Principal principal,
			HttpServletRequest request) {
		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
		User loggedUser = baseService.findByProperty(User.class, "username",
				name).get(0);
		model.addAttribute("loggedUser", loggedUser);

		User user = baseService.findById(User.class, loggedUser.getId());
		Person person = baseService.findById(Person.class, user.getPerson()
				.getId());
		model.addAttribute("person", person);

		try {
			if (user.getPosition().getName() != null) {
				model.addAttribute("position", user.getPosition().getName());
			} else {
				model.addAttribute("position", "");
			}
		} catch (Exception e) {
			model.addAttribute("position", "");
		}

		model.addAttribute("user", user);

		// test movimientos creados
		List<UserMovement> createdMovements = accessNativeService
				.getCreatedFormalitiesByUserId(loggedUser.getId(), 10);
		for (UserMovement mov : createdMovements) {
			LOG.info(mov.toString());
		}
		model.addAttribute("createdMovements", createdMovements);

		// test total de movimientos de usuario
		List<UserMovement> totalmovs = accessNativeService
				.getMovementUserActions(loggedUser.getId(), 10);
		for (UserMovement mov : totalmovs) {
			LOG.info(mov.toString());
		}
		model.addAttribute("totalmovs", totalmovs);
		
		//Avatar
		String operativeSystem = System.getProperty("os.name").toLowerCase();
		String rootPath = "";
		if (operativeSystem.indexOf("nix") >= 0
				|| operativeSystem.indexOf("nux") >= 0
				|| operativeSystem.indexOf("aix") > 0) {
			rootPath = SecopreConstans.SECOPRE_RESOURCES_LINUX_PATH;
		} else {
			rootPath = SecopreConstans.SECOPRE_RESOURCES_WINDOWS_PATH;
		}

		// se guarda el archivo
		if(user.getAvatar() != null){
			model.addAttribute("avatar", rootPath + File.separator+ user.getAvatar());
		}else{
			model.addAttribute("avatar", rootPath + File.separator+ "default_avatar.jpg");
			
		}
	
		return SecopreConstans.MV_ADM_PROFILE;
	}

	@RequestMapping(value = "adm/profile/showProfileAccount", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String showProfileAccount(ModelMap model, Principal principal,
			HttpServletRequest request) {

		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
		User loggedUser = baseService.findByProperty(User.class, "username",
				name).get(0);
		model.addAttribute("loggedUser", loggedUser);

		User user = baseService.findById(User.class, loggedUser.getId());
		Person person = baseService.findById(Person.class, user.getPerson()
				.getId());
		model.addAttribute("person", person);
		model.addAttribute("user", user);

		List<Position> position = baseService.findAll(Position.class);
		HashMap<Long, String> positionMap = new HashMap<Long, String>();
		for (Position p : position) {
			positionMap.put(p.getId(), p.getName());
		}
		model.addAttribute("positions", positionMap);

		return SecopreConstans.MV_ADM_PROFILE_ACCOUNT;
	}

	@RequestMapping(value = "adm/profile/changePersonalInfo/{idUser}/{idPerson}", method = RequestMethod.POST)
	public String changePersonalInfo(ModelMap model,
			@ModelAttribute("user") User user,
			@ModelAttribute("person") Person person,
			@PathVariable("idUser") Long idUser,
			@PathVariable("idPerson") Long idPerson, Principal principal,
			HttpServletRequest request) {

		User userEdit = baseService.findById(User.class, idUser);
		Person personEdit = baseService.findById(Person.class, idPerson);

		personEdit.setName(person.getName());
		personEdit.setSecondName(person.getSecondName());
		personEdit.setFatherLastName(person.getFatherLastName());
		personEdit.setMotherLastName(person.getMotherLastName());
		personEdit.setMobileTelepone(person.getMobileTelepone());
		personEdit.setTelephone(person.getTelephone());
		personEdit.setTwitter(person.getTwitter());
		personEdit.setFacebook(person.getFacebook());
		personEdit.setWebSite(person.getWebSite());

		userEdit.setNickname(user.getNickname());
		userEdit.setEmail(user.getEmail());
		userEdit.setPosition(user.getPosition());
		userEdit.setInformation(user.getInformation());

		baseService.save(personEdit);
		baseService.save(userEdit);

		return SecopreConstans.MV_ADM_PROFILE;
	}

	@RequestMapping(value = "adm/profile/changePassword/{idUser}", method = RequestMethod.POST)
	public String changePassword(ModelMap model,
			@ModelAttribute("user") User user,
			@PathVariable("idUser") Long idUser, Principal principal,
			HttpServletRequest request) {

		User userEdit = baseService.findById(User.class, idUser);
		if (user.getPassword() != null) {
			userEdit.setPassword(Encryption.encrypByBCrypt(user.getPassword()));
		}

		baseService.save(userEdit);

		return SecopreConstans.MV_ADM_PROFILE;
	}

	@RequestMapping(value = "adm/profile/changeAvatar", method = RequestMethod.POST)
	public String changeAvatar(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("attachment") MultipartFile attachment,
			ModelMap model, Principal principal,
			RedirectAttributes redirectAttributes) {
		String operativeSystem = System.getProperty("os.name").toLowerCase();
		String rootPath = "";
		try {
			if (operativeSystem.indexOf("nix") >= 0
					|| operativeSystem.indexOf("nux") >= 0
					|| operativeSystem.indexOf("aix") > 0) {
				rootPath = SecopreConstans.SECOPRE_RESOURCES_LINUX_PATH;
			} else {
				rootPath = SecopreConstans.SECOPRE_RESOURCES_WINDOWS_PATH;
			}
			// Multipart to File
			File file = new File(attachment.getOriginalFilename());
			attachment.transferTo(file);

			// File to BufferedImage
			BufferedImage originalImage = ImageIO.read(file);

			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();

			// Resize Image
			BufferedImage resizeImage = resizeImage(originalImage, type);

			// Save Image
			// se guarda el archivo
			String documentPath = rootPath + File.separator
					+ principal.getName() + ".jpg";

			File outputfile = new File(documentPath);
			ImageIO.write(resizeImage, "jpg", outputfile);

			User user = secopreCache.getUser(principal.getName());
			user.setAvatar(principal.getName() + ".jpg");
			
			baseService.update(user);
			
		} catch (Exception e) {
			LOG.error("Error al subir avatar", e);

		}
		return SecopreConstans.MV_ADM_PROFILE;
	}

	@RequestMapping(value = "adm/profile/checkPasswordExist/{password}", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> checkPasswordExist(ModelMap model,
			@PathVariable("password") String password, Principal principal) {
		boolean password_verified = false;
		int result = 0;
		Map<String, Object> returnObject = new HashMap<String, Object>();

		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
		User loggedUser = baseService.findByProperty(User.class, "username",
				name).get(0);
		String apassword_hash = loggedUser.getPassword();
		password_verified = BCrypt.checkpw(password, apassword_hash);
		if (password_verified) {
			result = 0;
		} else {
			result = 1;
		}

		returnObject.put("result", result);
		return returnObject;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage,
			int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}
}
