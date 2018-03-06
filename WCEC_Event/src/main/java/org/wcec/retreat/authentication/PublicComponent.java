package org.wcec.retreat.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.app.VaadinUI;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

/**
 * @author Alejandro Duarte.
 */
public class PublicComponent extends CustomComponent {

//
	private AuthService mAuthService;

	public PublicComponent( JpaRepository aUserLoginRepo, JpaRepository aUserTblRepo,
			JpaRepository aPersonRepo) {

		mAuthService = new AuthService( aUserLoginRepo, aUserTblRepo, aPersonRepo);

		TextField username = new TextField("Username");
		username.focus();

		PasswordField password = new PasswordField("Password");

		CheckBox rememberMe = new CheckBox("Remember me");

		Button button = new Button("Login",
				e -> onLogin(username.getValue(), password.getValue(), rememberMe.getValue()));
		button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		FormLayout formLayout = new FormLayout(username, password, button, rememberMe);
		formLayout.setSizeUndefined();
		formLayout.setCaption("WCEC 2018 Retreat Login");

		VerticalLayout layout = new VerticalLayout(formLayout);
		layout.setSizeFull();
		layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

		setCompositionRoot(layout);
		setSizeFull();
	}

	public boolean isUserAuthenticated() {
		return mAuthService.isAuthenticated();
	}
	private void onLogin(String username, String password, boolean rememberMe) {
		if (mAuthService.login(username, password, rememberMe)) {
			VaadinUI ui = (VaadinUI) UI.getCurrent();
			ui.showPrivateComponent();
		} 
	}

}