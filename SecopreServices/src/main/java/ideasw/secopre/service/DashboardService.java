package ideasw.secopre.service;

import ideasw.secopre.model.security.User;

import java.util.List;

public interface DashboardService {

	public List<String> getIndicators(User user);

}
