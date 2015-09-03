/**
 * 
 */
package ideasw.secopre.web.security;

import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * @author jorge.canoc@gmail.com
 *
 */
@Service(value="authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	AccessService accessService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		setDefaultTargetUrl(SecopreConstans.MV_START);
		User user = null;
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		} else
			return;

		HttpSession session = request.getSession();
		if (session != null) {
			String redirectUrl = (String) session
					.getAttribute("url_prior_login");
			if (redirectUrl != null) {
				request.getSession().removeAttribute(
						SecopreConstans.USER_IN_SESSION);

				ideasw.secopre.model.security.User userInBD = accessService
						.loadUserByUsername(user.getUsername());

				logger.info("User in session: " + userInBD.getUsername());
				String jSessionId = request.getSession().getId();
				// is client behind something?
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				accessService.onLoginSuccess(user.getUsername(), jSessionId,
						ipAddress);
				getRedirectStrategy().sendRedirect(request, response,
						redirectUrl);
			} else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
