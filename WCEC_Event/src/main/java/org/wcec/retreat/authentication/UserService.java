package org.wcec.retreat.authentication;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.entity.UserLogin;
import org.wcec.retreat.entity.UserTbl;
import org.wcec.retreat.repo.PersonTblRepository;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;

public class UserService {

	private SecureRandom random = new SecureRandom();
	private JpaRepository mPersonRepo;
	private JpaRepository mUserLoginRepo;
	private JpaRepository mUserTblRepo;

	public UserService(JpaRepository aUserLoginRepo, JpaRepository aUserTblRepo, JpaRepository aPersonRepo) {
		mUserLoginRepo = aUserLoginRepo;
		mUserTblRepo = aUserTblRepo;
		mPersonRepo = aPersonRepo;
	}

	private Map<String, String> rememberedUsers = new HashMap<>();

	public String rememberUser(String username) {
		String randomId = new BigInteger(130, random).toString(32);
		rememberedUsers.put(randomId, username);
		return randomId;
	}

	public String getRememberedUser(String id) {
		return rememberedUsers.get(id);
	}

	public void removeRememberedUser(String id) {
		rememberedUsers.remove(id);
	}

	// mysql stuff
	public boolean isAuthenticUser(String aStrUsername, String password) {
		System.out.println("check here " + aStrUsername + " " + password);
		// return aStrUsername.equals("aa") && password.equals("jj");
		PersonTblRepository repo = (PersonTblRepository) mPersonRepo;
		List<PersonTbl> thePersonList = repo.findByChineseNm(aStrUsername);
		if (thePersonList != null && thePersonList.size() > 0) {
			UserTbl example = new UserTbl();
			  
			UserTbl theUser = (UserTbl) this.mUserTblRepo.findOne(thePersonList.get(0).getId());
			if (theUser != null) {
				// check password
				char[] wvcPwd = password.toCharArray();

				if (Arrays.equals(theUser.getPassword().toCharArray(), wvcPwd)) {
					doClearArrayWithZero(wvcPwd);
					doUpdUserLoginRepo(theUser.getId());
					System.out.println("User person=" + thePersonList.get(0).getChineseNm());
					return true;
				} 
				Notification.show("Wrong password", Notification.Type.ERROR_MESSAGE); 
				doClearArrayWithZero(wvcPwd);
				return false;
			}
			// Notification.show("User id " + aStrUsername + " does not exist",
			// Notification.Type.ERROR_MESSAGE);
			// doClearArrayWithZero( wvcPwd );
			return false;
		} else { 
			Notification.show("User " + aStrUsername + " invalid", Notification.Type.ERROR_MESSAGE);
			return false;
		} 
	}

	private void doUpdUserLoginRepo(int aiUserId) {

		final String wStrSessionId = VaadinSession.getCurrent().getSession().getId();
		final Timestamp wTsLoginTime = new Timestamp(System.currentTimeMillis());

		UserLogin wUserLogin = new UserLogin();
		wUserLogin.setUserId(aiUserId);
		wUserLogin.setSessionId(wStrSessionId);
		wUserLogin.setLoginTime(wTsLoginTime);

		// logout time needs to accept null... below line to be deleted once DBA
		// fixed
		wUserLogin.setLogoutTime(new Timestamp(0));

		wUserLogin.setLastUpdtTs(wTsLoginTime);

		mUserLoginRepo.save(wUserLogin);
		mUserLoginRepo.flush();

	}

	private String getStrTabPersonName(int aiUserId) {
		Collection<PersonTbl> wColPerson = mPersonRepo.findAll();
		for (PersonTbl wPerson : wColPerson) {
			if (wPerson.getId() == aiUserId) {
				System.out.println("here");
				// wPerson.setChineseNm("------------------------------XXXWWWFFFF");
				// myPersonRepo.save(wPerson);
				return wPerson.getFirstNm() + " " + wPerson.getLastNm();
			}
		}
		return "db integry error user id=" + aiUserId;
	}

	private void doClearArrayWithZero(char[] av) {
		for (int i = 0; i < av.length; i++)
			av[i] = 0;
		av = null;
	}
}