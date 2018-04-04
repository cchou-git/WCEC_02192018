package org.wcec.retreat.app;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LogoutListener implements ClickListener {
	
	VaadinUI myUI;
	
	public LogoutListener (VaadinUI aUI){
		myUI = aUI;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		    // Close the VaadinServiceSession
		    myUI.getNavigator().navigateTo(VaadinUI.LOGOUT_VIEW);
		    // Invalidate underlying session instead if login info is stored there
		    // VaadinService.getCurrentRequest().getWrappedSession().invalidate();

		    // Redirect to avoid keeping the removed UI open in the browser
		    //myUI.getPage().setLocation("http://localhost:8080/logout.html");
		    myUI.getSession().close();
		    
	}

	

}
