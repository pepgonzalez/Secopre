package ideasw.secopre.web.security;

import java.util.List;

import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userSecurityService")
public class UserSecurityService implements UserDetailsService {

	@Autowired
	Assembler assembler;

	@Autowired
	AccessService accessService;
	
	@Autowired
	private AccessNativeService accessNativeService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
//		User user2 = accessService.loadUserByUsername(username);
		User user = accessNativeService.getUserByUsename(username);
	    List<Role> authorities = accessNativeService.getRolesByUser(user.getId());
	    user.setAuthorities(authorities);
		
		if (user == null)
			throw new UsernameNotFoundException("El usuario: " + username
					+ " no esta registrado en el sistema");
		
		if(!user.isActive())
			throw new UsernameNotFoundException("El usuario: " + username
					+ " no ese encuentra activo");

		//TODO: validar password expirado 
		
		return assembler.buildUserFromUserEntity(user);

	}

}
