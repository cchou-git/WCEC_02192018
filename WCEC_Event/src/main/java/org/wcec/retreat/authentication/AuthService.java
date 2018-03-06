package org.wcec.retreat.authentication;

import com.vaadin.server.VaadinSession;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;

import javax.servlet.http.Cookie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Optional;

public class AuthService {

	private static final String COOKIE_NAME = "remember-me";
	public static final String SESSION_USERNAME = "username";
	private UserService mUserService;
	
	public AuthService( 
			JpaRepository aUserLoginRepo, 
    		JpaRepository aUserTblRepo,
    		JpaRepository aPersonRepo ) {
		mUserService = new UserService( aUserLoginRepo, aUserTblRepo,  aPersonRepo);
	}
	
	public boolean isAuthenticated() {
		return VaadinSession.getCurrent().getAttribute(SESSION_USERNAME) != null || loginRememberedUser();
	}

	public boolean login(String username, String password, boolean rememberMe) {
		if ( mUserService.isAuthenticUser(username, password)) {
			VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, username);

			if (rememberMe) {
				rememberUser(username);
			}
			return true;
		}

		return false;
	}

	public void logOut() {
		Optional<Cookie> cookie = getRememberMeCookie();
		if (cookie.isPresent()) {
			String id = cookie.get().getValue();
			mUserService.removeRememberedUser(id);
			deleteRememberMeCookie();
		}

		VaadinSession.getCurrent().close();
		Page.getCurrent().setLocation("");
	}

	private Optional<Cookie> getRememberMeCookie() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		return Arrays.stream(cookies).filter(c -> c.getName().equals(COOKIE_NAME)).findFirst();
	}

	private boolean loginRememberedUser() {
		System.out.println( "login rememberuser()");
		
		Optional<Cookie> rememberMeCookie = getRememberMeCookie();

		if (rememberMeCookie.isPresent()) {
			System.out.println( "present");;
			String id = rememberMeCookie.get().getValue();
			String username = mUserService.getRememberedUser(id);

			if (username != null) {
				System.out.println( "username != null =" + username );;

				VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, username);
				return true;
			}
			System.out.println( "username = null" );;

		} else {
			System.out.println( "not present");

		}
		

		return false;
	}

	private void rememberUser(String username) {
		
		System.out.println( "remember user" + username );;

		String id = mUserService.rememberUser(username);

		Cookie cookie = new Cookie(COOKIE_NAME, id);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 30); // valid for 30 days
		VaadinService.getCurrentResponse().addCookie(cookie);
	}

	private void deleteRememberMeCookie() {
		Cookie cookie = new Cookie(COOKIE_NAME, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		VaadinService.getCurrentResponse().addCookie(cookie);
	}
}