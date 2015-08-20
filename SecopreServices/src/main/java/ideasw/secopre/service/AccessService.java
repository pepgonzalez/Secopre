package ideasw.secopre.service;

import ideasw.secopre.model.security.User;

public interface AccessService {

	public User loadUserByUsername(String userName);
}
