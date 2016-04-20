package ideasw.secopre.service;

import ideasw.secopre.model.Notice;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;

import java.util.List;

public interface DashboardService {

	public List<String> getIndicators(User user);
	
	public List<Notice> getNotice(User user, List<District> distritcs);

}
