package ideasw.secopre.web.security;

import ideasw.secopre.model.security.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Assembler {

	@Transactional(readOnly = true)
	public org.springframework.security.core.userdetails.User buildUserFromUserEntity(
			User userInBD) {

		boolean enabled = userInBD.isActive();
		String username = userInBD.getUsername();
		String password = userInBD.getPassword();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
				username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				userInBD.getAuthorities());
		return user;
	}

}
