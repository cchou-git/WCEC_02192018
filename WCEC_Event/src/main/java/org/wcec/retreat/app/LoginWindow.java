
package org.wcec.retreat.app;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.entity.UserTbl;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LoginWindow extends Window {
	
    private Layout mLayout = new VerticalLayout();

    private Button mBtnLogin = new Button("Login");
    private TextField mTxtUsername = new TextField("Username");
    private PasswordField mTxtPassword = new PasswordField("Password");
    private Label mLblMsg = new Label("");
    
    JpaRepository myRepo;
    JpaRepository myUserRepo;
    JpaRepository myPersonRepo;
    
    public LoginWindow(JpaRepository aRepo, JpaRepository userRepo, JpaRepository pRepo) {
        super(" Login");
        setContent(mLayout);
        myRepo = aRepo;
        myUserRepo = userRepo;
        myPersonRepo = pRepo;
        initUI(); 
    }
    private void initUI() {
        mTxtUsername.setRequiredIndicatorVisible(true);

        final Panel wLoginPanel = new Panel("WCEC 2008 Retreat Login");
        mLayout.addComponent(wLoginPanel);
        mBtnLogin.addClickListener(e -> {
        	Collection<PersonTbl> persons = myPersonRepo.findAll();
            Collection<UserTbl> allUsers = myUserRepo.findAll();
        	for (UserTbl usr : allUsers) {
        		System.out.println(usr.getUserEmailId());
        		System.out.println(usr.getPassword());
        	}
            mLblMsg.setValue ( "Thanks " + mTxtUsername.getValue() );
        });

        final FormLayout wLoginForm = new FormLayout();
        wLoginForm.setMargin(true);
        wLoginForm.setStyleName("loginForm");
        wLoginForm.addComponent(mTxtUsername);
        wLoginForm.addComponent(mTxtPassword);
        wLoginForm.addComponent(mBtnLogin);
        wLoginForm.addComponent(mLblMsg);

        wLoginPanel.setContent(wLoginForm);
    }
}
