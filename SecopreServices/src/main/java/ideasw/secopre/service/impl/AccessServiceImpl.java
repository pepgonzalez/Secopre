package ideasw.secopre.service.impl;

import ideasw.secopre.dao.JpaDao;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	JpaDao jpaDao;

	@Override
	public User loadUserByUsername(String username) {
		List<User> users = jpaDao.findByProperty(User.class, "username",
				username);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
