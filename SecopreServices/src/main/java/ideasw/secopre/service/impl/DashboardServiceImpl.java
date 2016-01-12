package ideasw.secopre.service.impl;

import ideasw.secopre.model.Dashboard;
import ideasw.secopre.model.Indicator;
import ideasw.secopre.model.Notice;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.DashboardService;
import ideasw.secopre.service.impl.mapper.NoticeMapper;
import ideasw.secopre.utils.time.TimeUtils;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl extends AccessNativeServiceBaseImpl implements
		DashboardService {

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
		// Se obtiene el ultimo dashboard por usuario;
		User userInBD = baseService.getReference(User.class, user.getId());

		if (userInBD.getDashboard() == null) {
			return null;
		}

		Dashboard dashboard = userInBD.getDashboard();

		return null;
	}

	private List<String> buildIndicators(Dashboard dashboard) {
		return null;
	}

	private String buildIndicator(Indicator indicator, User user) {

		// Se ejecuta la sentencia SQL

		StringBuffer buffer = new StringBuffer();
		buffer.append(MAIN_DIV);
		buffer.append(CONTAINER_DIV);
		buffer.append(DISPLAY_DIV);

		return buffer.toString();
	}

	@Override
	public Notice getNotice(User user, List<District> districts) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String list = "";
		for (District district : districts) {
			list+=district.getId()+",";
		}
        
		
		if(list.length()!=0)		
		{
			StringBuffer buffer = new StringBuffer();
			// Busca primero una aviso por el alguno de los distritos.
		
			buffer.append("SELECT N.* ");
			buffer.append("FROM secopre.NOTICE N ");
			buffer.append("INNER JOIN secopre.NOTICE_DISTRICT ND ");
			buffer.append("ON N.ID = ND.NOTICE_ID ");
			buffer.append("WHERE ND.DISTRICT_ID IN ( "+list.substring(0, list.length()-1)+" ) ");
			buffer.append("AND DISPLAY_DATE = DATE('"+TimeUtils.defaultDateFormat.format(new Date())+"') ");
			buffer.append("AND ACTIVE = 1 ");
			buffer.append("ORDER BY N.REGISTER_DATE ");
	
			List<Notice> notices = this.queryForList(Notice.class, buffer.toString(), params, new NoticeMapper());
	
			if (notices != null && !notices.isEmpty() ) {
				return notices.get(0);
			}
			
			// En caso que no exista algun aviso busca uno general;
			buffer = new StringBuffer();
			buffer.append("SELECT N.* ");
			buffer.append("FROM secopre.NOTICE N ");
			buffer.append("WHERE N.DISPLAY_DATE = DATE('"+TimeUtils.defaultDateFormat.format(new Date())+"') ");
			buffer.append("AND N.ID NOT IN (SELECT ND.NOTICE_ID FROM secopre.NOTICE_DISTRICT ND) ");
			buffer.append("AND N.ACTIVE = 1 ");

			notices = this.queryForList(Notice.class, buffer.toString(), params, new NoticeMapper());
			
			if (notices != null && !notices.isEmpty() ) {
				return notices.get(0);
			}
			
	    }
	
		return null;
	}
}
