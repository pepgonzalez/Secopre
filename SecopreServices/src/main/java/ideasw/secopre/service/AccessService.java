package ideasw.secopre.service;

import ideasw.secopre.dto.RenderSingleMenu;
import ideasw.secopre.model.security.User;

import java.util.List;

public interface AccessService {

	public User loadUserByUsername(String userName);

	public List<RenderSingleMenu> getMenuByUserName(String username);
	
	public void onLoginSuccess(String username, String jSessionId, String remoteIP);
}
