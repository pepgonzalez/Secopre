package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class APIController extends AuthController {

	@Autowired
	private AccessNativeService accessNativeService;
	

	static final Logger LOG = LoggerFactory.getLogger(APIController.class);


	@RequestMapping(value = "API/get/entry/{programaticKeyId}/{districtId}", method = { RequestMethod.GET })
	public @ResponseBody List<Entry> getEntriesByProgramaticKey(
			@PathVariable("programaticKeyId") Long programaticKeyId,
			@PathVariable("districtId") Long districtId,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

			//List<Entry> entryList = accessNativeService.getEntries(programaticKeyId);
			List<Entry> entryList = accessNativeService.getEntries(districtId, programaticKeyId);
		
			return entryList;
	}
	
	@RequestMapping(value = "API/get/movOk/{districtId}/{entryId}/{month}/{amount}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getValidMov(
			@PathVariable("districtId") Long districtId, 
			@PathVariable("entryId") Long entryId,
			@PathVariable("month") Long month,
			@PathVariable("amount") Double amount){
		
		EntryDistrict balance = accessNativeService.getEntryBalance(districtId, entryId, month);
		LOG.info("Obteniendo balance por distrito: " + districtId + ", partida: " + entryId + ", mes: " + month);
		LOG.info(balance.toString());
		
		Map<String, Object> returnObject = new HashMap<String, Object>();
		
		if(balance == null){
			returnObject.put("result", -1);
			returnObject.put("msg", "Distrito/Partida/Mes sin presupuesto asociado");
			return returnObject;
		}
		
		returnObject.put("result",(balance.isValidMovement(amount)? 1:0));
		returnObject.put("msg", "La partida " + balance.getEntry().getName() + " asociada al distrito " + balance.getDistrict().getNumber() + 
								" no tiene presupuesto asignado suficiente para el movimiento en el mes de " + balance.getMonthString() + 
								": " + (balance.getBudgetAmountAssign() - balance.getCommittedAmount()) );
		
		return returnObject;
	}
	

}
