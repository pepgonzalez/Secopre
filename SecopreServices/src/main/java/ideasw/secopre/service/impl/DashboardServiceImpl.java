package ideasw.secopre.service.impl;

import ideasw.secopre.model.Dashboard;
import ideasw.secopre.model.Indicator;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.DashboardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

	private static final String MAIN_DIV = "<div class=\"col-lg-3 col-md-3 col-sm-6 col-xs-12\">";
	private static final String END_DIV = "</div>";
	private static final String CONTAINER_DIV = "<div class=\"dashboard-stat2\">";
	private static final String DISPLAY_DIV = "<div class=\"display\">";
	private static final String NUMBER_DIV = "<div class=\"number\">";
	private static final String H3_GREEN = "<h3 class=\"font-green-sharp\">";
	private static final String END_H3 = "</h3>";
	private static final String SMALL_GREEN = "<small class=\"font-green-sharp\">";
	private static final String END_SMALL = "</small>";
	private static final String SMALL = "<small>";
	private static final String ICON_DIV = "<div class=\"icon\">";
	
	
	
	@Autowired
	BaseService baseService;
	
	
	@Override
	public List<String> getIndicators(User user) {
		//Se obtiene el ultimo dashboard por usuario;
		User userInBD = baseService.getReference(User.class, user.getId());
		
		if(userInBD.getDashboard() == null){
			return null;
		}
		
		Dashboard dashboard = userInBD.getDashboard();
		
		
		return null;
	}
	
	private List<String> buildIndicators(Dashboard dashboard){
		return null;
	}

	private String buildIndicator(Indicator indicator, User user){
		
		//Se ejecuta la sentencia SQL 
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(MAIN_DIV);
		buffer.append(CONTAINER_DIV);
		buffer.append(DISPLAY_DIV);
		
		
		return buffer.toString();
	}	
}
