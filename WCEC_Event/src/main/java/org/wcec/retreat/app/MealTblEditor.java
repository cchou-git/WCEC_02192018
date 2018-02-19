package org.wcec.retreat.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.wcec.retreat.entity.MealTbl;
import org.wcec.retreat.repo.MealTblRepository;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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
public class MealTblEditor extends VerticalLayout {

	private final MealTblRepository repository;

	/**
	 * The currently edited mealTbl
	 */
	private MealTbl mealTbl;

	/* Fields to edit properties in MealTbl entity */
	TextField meal_type = new TextField("Type of Meal");
	TextField meal_price = new TextField("Meal Price");
	TextField age_from = new TextField("From Age");
	TextField age_to = new TextField("To Age");

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<MealTbl> binder = new Binder<>(MealTbl.class);
	 
	@Autowired
	public MealTblEditor(MealTblRepository repository) {
		this.repository = repository;
	 	 
        binder.forField ( meal_price ) 
        	  .withNullRepresentation( "" )
              .withConverter ( new StringToBigDecimalConverter(new BigDecimal(10.00), "Price must be in ##.## format"))
              .bind ( MealTbl:: getMealPrice, MealTbl:: setMealPrice );
        /*  
        binder.forField ( meal_type) 
  	          .withNullRepresentation( "" )
              .bind ( MealTbl::getMealType, MealTbl:: setMealType );
        
        binder.forField ( age_from ) 
  	  .withNullRepresentation( "" )
        .withConverter ( new StringToIntegerConverter(new Integer(5), "Price must be integer"))
        .bind ( MealTbl:: getAgeFrom, MealTbl:: setAgeFrom );
        binder.forField ( age_to ) 
  	  .withNullRepresentation( "" )
        .withConverter ( new StringToIntegerConverter(new Integer(5), "Price must be integer"))
        .bind ( MealTbl:: getAgeTo, MealTbl:: setAgeTo );
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
		save.addClickListener(e -> repository.save(mealTbl));
		delete.addClickListener(e -> repository.delete(mealTbl));
		cancel.addClickListener(e -> editMealTbl(mealTbl));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editMealTbl(MealTbl c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != 0;
		if (persisted) {
			// Find fresh entity for editing
			mealTbl = repository.findOne(c.getId());
		}
		else {
			mealTbl = c;
		}
		cancel.setVisible(persisted);

		// Bind mealTbl properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(mealTbl);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		age_from.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
