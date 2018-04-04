package org.wcec.retreat.app;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class LogoutView extends VerticalLayout implements View {

	public LogoutView() {
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("You are now logged out.");
	}
}
