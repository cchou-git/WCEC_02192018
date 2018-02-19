package org.wcec.retreat.app;

import com.vaadin.ui.Component;
import com.vaadin.ui.renderers.AbstractRenderer;

public class ColumnToRendererAssociation {
	
	String propertyName;
	
	Component editorComponent;
	
	public ColumnToRendererAssociation(String aName, Component aComponent) {
		this.setPropertyName(aName); 
		this.setEditorComponent(aComponent);
	}

	public Component getEditorComponent() {
		return editorComponent;
	}

	public void setEditorComponent(Component editorComponent) {
		this.editorComponent = editorComponent;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}

