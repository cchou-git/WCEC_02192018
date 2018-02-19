package org.wcec.retreat.app;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.repo.PersonTblRepository;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Viritin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class PersonTblEditor extends VerticalLayout {

	private final PersonTblRepository repository;

	/**
	 * The currently edited personTbl
	 */
	private PersonTbl personTbl;

	/* Fields to edit properties in PersonTbl entity */
	TextField birthDt = new TextField("Birth Date");
	TextField chineseNm = new TextField("Chinese Name");
	TextField firstNm = new TextField("First Name");
	TextField lastNm = new TextField("Last Name");
	 
	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<PersonTbl> binder = new Binder<>(PersonTbl.class);
	 
	@Autowired
	public PersonTblEditor(PersonTblRepository repository) {
		this.repository = repository;
	/* 	 
        binder.forField ( meal_price ) 
        	  .withNullRepresentation( "" )
              .withConverter ( new StringToBigDecimalConverter(new BigDecimal(10.00), "Price must be in ##.## format"))
              .bind ( PersonTbl:: getMealPrice, PersonTbl:: setMealPrice );
          
        binder.forField ( meal_type) 
  	          .withNullRepresentation( "" )
              .bind ( PersonTbl::getMealType, PersonTbl:: setMealType );
        
        binder.forField ( age_from ) 
  	  .withNullRepresentation( "" )
        .withConverter ( new StringToIntegerConverter(new Integer(5), "Price must be integer"))
        .bind ( PersonTbl:: getAgeFrom, PersonTbl:: setAgeFrom );
        binder.forField ( age_to ) 
  	  .withNullRepresentation( "" )
        .withConverter ( new StringToIntegerConverter(new Integer(5), "Price must be integer"))
        .bind ( PersonTbl:: getAgeTo, PersonTbl:: setAgeTo );
        addComponents(meal_type, age_from, age_to, meal_price, actions);  
      */   
        // bind using naming convention
		//binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(personTbl));
		delete.addClickListener(e -> repository.delete(personTbl));
		cancel.addClickListener(e -> editPersonTbl(personTbl));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editPersonTbl(PersonTbl c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != 0;
		if (persisted) {
			// Find fresh entity for editing
			personTbl = repository.findOne(c.getId());
		}
		else {
			personTbl = c;
		}
		cancel.setVisible(persisted);

		// Bind personTbl properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(personTbl);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		 
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
