package org.wcec.retreat.app;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;
import org.wcec.retreat.domain.generated.AllDomainTable;
import org.wcec.retreat.domain.generated.WCECDomainTable;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.extendedgui.MyTableMaintenanceDesign;
import org.wcec.retreat.gui.vaadin.extended.MyListSelect;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;
import org.wcec.retreat.repo.AccessLevelRepo;
import org.wcec.retreat.repo.AddressTblRepo;
import org.wcec.retreat.repo.BedTypeTblRepo;
import org.wcec.retreat.repo.BuildingTblRepo;
import org.wcec.retreat.repo.ChurchTblRepo;
import org.wcec.retreat.repo.EmailTblRepo;
import org.wcec.retreat.repo.EventHostTblRepo;
import org.wcec.retreat.repo.EventTblRepo;
import org.wcec.retreat.repo.EventTypeTblRepo;
import org.wcec.retreat.repo.GroupTblRepo;
import org.wcec.retreat.repo.GroupTypeTblRepo;
import org.wcec.retreat.repo.LodgingAssignmentTblRepo;
import org.wcec.retreat.repo.MealPlanTblRepo;
import org.wcec.retreat.repo.MealTblRepo;
import org.wcec.retreat.repo.MealTblRepository;
import org.wcec.retreat.repo.PaymentTblRepo;
import org.wcec.retreat.repo.PersonTblRepository;
import org.wcec.retreat.repo.PhoneTblRepo;
import org.wcec.retreat.repo.PhoneTypeTblRepo;
import org.wcec.retreat.repo.RegistrationTblRepo;
import org.wcec.retreat.repo.RoomTblRepo;
import org.wcec.retreat.repo.UserLoginRepo;
import org.wcec.retreat.repo.UserTblRepo;

import com.google.common.collect.Lists;
import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Setter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

//@Theme("base")
@SpringUI
public class VaadinUI extends UI implements ValueChangeListener<Set<String>> {
	@Autowired
	AccessLevelRepo                            TheAccessLevelRepo;
	
	@Autowired
	AddressTblRepo                             TheAddressTblRepo;
	
	@Autowired
	BedTypeTblRepo                             TheBedTypeTblRepo;
	
	@Autowired
	BuildingTblRepo                            TheBuildingTblRepo;
	
	@Autowired
	ChurchTblRepo                              TheChurchTblRepo;
	
	@Autowired
	EmailTblRepo                               TheEmailTblRepo;
	
	@Autowired
	EventHostTblRepo                           TheEventHostTblRepo;
	
	@Autowired
	EventTblRepo                               TheEventTblRepo;
	
	@Autowired
	EventTypeTblRepo                           TheEventTypeTblRepo;
	
	@Autowired
	GroupTblRepo                               TheGroupTblRepo;
	
	@Autowired
	GroupTypeTblRepo                           TheGroupTypeTblRepo;
	
	@Autowired
	LodgingAssignmentTblRepo                   TheLodgingAssignmentTblRepo;
	
	@Autowired
	MealPlanTblRepo                            TheMealPlanTblRepo;
	
	@Autowired
	MealTblRepo                                TheMealTblRepo;
	
	@Autowired
	MealTblRepository                          TheMealTblRepository;
	
	@Autowired
	PaymentTblRepo                             ThePaymentTblRepo;
	
	@Autowired
	PersonTblRepository                        ThePersonTblRepository;
	
	@Autowired
	PhoneTblRepo                               ThePhoneTblRepo;
	
	@Autowired
	PhoneTypeTblRepo                           ThePhoneTypeTblRepo;
	
	@Autowired
	RegistrationTblRepo                        TheRegistrationTblRepo;
	
	@Autowired
	RoomTblRepo                                TheRoomTblRepo;
	
	@Autowired
	UserLoginRepo                              TheUserLoginRepo;
	
	@Autowired
	UserTblRepo                                TheUserTblRepo;
	

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final EventTblRepo eventTblRepo;

	private final EventTblEditor editor;

	 

	final TextField filter;

	private final Button addNewBtn;
	
	
	@Autowired
	public VaadinUI(EventTblRepo eventTblRepo, EventTblEditor editor) {
		this.eventTblRepo = eventTblRepo;
		this.editor = editor;
		
		
		this.filter = new TextField();
		this.addNewBtn = new Button("New meal type", FontAwesome.PLUS);
	}
	
	
	 
	 

	void findTabSheet(ComponentContainer aComponentContainer, List<TabSheet> outList) {
		Iterator<Component> itr = aComponentContainer.getComponentIterator();
		while (itr.hasNext()) {
			Component comp = itr.next();
			System.out.println("Comp = " + comp);
			if (comp instanceof TabSheet) {
				outList.add((TabSheet)comp);
			}
			if (comp instanceof ComponentContainer) {
				findTabSheet((ComponentContainer) comp, outList);
			}
		}
	}
	
	VerticalLayout mainDomainTableLayout = new MyTableMaintenanceDesign(); 
	Button newButton = new Button("Add");
	
	@Override	
	protected void init(VaadinRequest request) {
		 
		VerticalLayout mainLayout = new VerticalLayout();
		final Label labelTitle = new Label("WCEC Retreat Registration Form");
		//labelTitle.setStyleName("h1");
		mainLayout.addComponent(labelTitle);
		 
		
		final Label labelPerson = new Label("Add a person: "); 
		
		HorizontalLayout hLayout = new HorizontalLayout(labelPerson, newButton);
		hLayout.setVisible(true); 
		hLayout.setHeight(100, Unit.PERCENTAGE);
		mainLayout.addComponent(hLayout); 
		this.populateRegistrationGrid(mainLayout); 
		setContent(mainLayout);
	}
	
	
	
	protected void init_domaintables(VaadinRequest request) {
		initializeDomainTableData();
		// build layout
		MyTableMaintenanceDesign theDesign = (MyTableMaintenanceDesign) mainDomainTableLayout;
		theDesign.populateTableName(domainTableList);
		ListSelect<String> theListSelect = theDesign.getListSelect();
		theListSelect.addValueChangeListener(this);
		
		this.populateGrid("event_type_tbl", mainDomainTableLayout);
		 
		// Initialize listing
		listEventTbls(null); 
	}
	
	@Autowired
	private ApplicationContext appContext;
	
	 
		
	 

	// tag::listEventTbls[]
	void listEventTbls(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			//grid.setItems(eventTblRepo.findAll());
		} 
	}
	// end::listEventTbls[]
	 
	
	protected void initializeGrid(JpaRepository aRepo) { 
		VerticalLayout mainDomainTableLayout = new MyTableMaintenanceDesign(); 
		ArrayList<TabSheet> outList = new ArrayList<TabSheet>();
		findTabSheet((ComponentContainer) mainDomainTableLayout, outList); 
		setContent(mainDomainTableLayout);
		TabSheet sheet = outList.get(0);
		Grid<EventTbl> theGrid = new Grid<>(EventTbl.class);
		
		List<Field> currentClassFields = (List) Lists.newArrayList(EventTbl.class.getDeclaredFields());
		String fieldNames[] = new String[currentClassFields.size()];
		int actualSize = 0;
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (!currentClassFields.get(i).getName().equals("serialVersionUID")) {
				fieldNames[j] = currentClassFields.get(i).getName();
				j++;
				actualSize = j;
			}
		}
		String fieldNames2[] = new String[actualSize];
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (fieldNames[i] != null) { 
				fieldNames2[j] = fieldNames[i];
				j++;
			}
		}
		
		VerticalLayout tab1 = new VerticalLayout(); 
		sheet.addTab(tab1, "Domain Table", null); 
		tab1.setVisible(true); 
		tab1.addComponent(theGrid);
		theGrid.setHeight(100, Unit.PERCENTAGE);
		theGrid.setWidth(100, Unit.PERCENTAGE);
		theGrid.setVisible(true);
		theGrid.setColumns(fieldNames2); 
		theGrid.setItems(aRepo.findAll());
	}
	
	Hashtable<String, WCECDomainTable> domainTableHash = new Hashtable<String, WCECDomainTable>();
	List<WCECDomainTable> domainTableList = new ArrayList<WCECDomainTable>();
	
	/**
	 * To initialize the maintainable list of domain tables, this method reads in the pre-defined xml
	 * file containing the metadata of the tables and save them into the member domainTableHash by the 
	 * table name as key.
	 * 
	 * It also saves these domain table instances into the list so we can get the dropdown easier.
	 * 
	 * 
	 */
	void initializeDomainTableData() {
		File xmlInstanceFile;
		try {
			xmlInstanceFile = new File(VaadinUI.class.getResource("/org/wcec/retreat/domain/domain_table.xml").toURI());
			JAXBContext jc = JAXBContext.newInstance("org.wcec.retreat.domain.generated");
			Unmarshaller um = jc.createUnmarshaller();
			AllDomainTable allTable = (AllDomainTable) JAXBIntrospector.getValue(um.unmarshal(xmlInstanceFile));
			for (WCECDomainTable each : allTable.getWCECDomainTable()) {
				domainTableHash.put(each.getTableName(),  each);
				domainTableList.add(each);
				
				Class aClass;
				try {
					aClass = Class.forName(each.getRepositoryName());
					Object obj = appContext.getBean(aClass);
					each.setRepoProxy(obj);  
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (URISyntaxException e) {
		
		} catch (JAXBException e) { 
		}
	}

	/**
	 * Add a grid for the implied domain table to the layout.
	 * 
	 * @param layout
	 */
	void populateGrid(String tableName, VerticalLayout mLayout) { 
		ArrayList<TabSheet> outList = new ArrayList<TabSheet>();
		findTabSheet((ComponentContainer) mLayout, outList); 
		setContent(mLayout);
		TabSheet sheet = outList.get(0);  
		VerticalLayout tab1 = new VerticalLayout(); 
		sheet.addTab(tab1, tableName, null); 

		tab1.setVisible(true);
		WCECDomainTable theDomainTable = domainTableHash.get(tableName);
		Class domainTableClass;
		try {
			domainTableClass = Class.forName(theDomainTable.getEntityName());
			JpaRepository repo = (JpaRepository) theDomainTable.getRepoProxy();
			ColumnToRendererAssociation [] fieldNames2 = extractProperty(domainTableClass);
			 
			Grid theGrid = new Grid<>(domainTableClass);
			tab1.addComponent(theGrid);
			theGrid.setHeight(100, Unit.PERCENTAGE);
			theGrid.setWidth(100, Unit.PERCENTAGE);
			theGrid.setVisible(true);
			
			//theGrid.setColumns(fieldNames2); 
			
			theGrid.setItems(repo.findAll());	
			for (int i = 0; i < fieldNames2.length; i++) {
				Component editorComponent = fieldNames2[i].getEditorComponent();
				if (editorComponent != null) {
					theGrid.removeColumn(fieldNames2[i].getPropertyName());
					theGrid.addColumn(fieldNames2[i].getPropertyName()).setEditorComponent((MyListSelect) editorComponent);
				}
			}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	ColumnToRendererAssociation [] extractProperty(Class aClass) {
		List<Field> currentClassFields = (List) Lists.newArrayList(aClass.getDeclaredFields());
		ColumnToRendererAssociation fieldNames[] = new ColumnToRendererAssociation[currentClassFields.size()];
		int actualSize = 0;
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (!currentClassFields.get(i).getName().equals("serialVersionUID")) {
				MyListSelect listSelect = new MyListSelect();
				listSelect.setWidth(100, Unit.PERCENTAGE);
				listSelect.setHeight(82, Unit.PIXELS);  
				//listSelect.addValueChangeListener(this);
				Field aField = currentClassFields.get(i);
				
				String packageName = "";
				if (aField.getType().getPackage() != null) {
					packageName = aField.getType().getPackage().getName();
				}
				if (packageName.indexOf("org.wcec.retreat") >= 0) {
					fieldNames[j] = new ColumnToRendererAssociation(currentClassFields.get(i).getName(), (Component) listSelect);
				} else {
					fieldNames[j] = new ColumnToRendererAssociation(currentClassFields.get(i).getName(), null);
				} 
				 
				j++;
				actualSize = j;
			}
		}
		ColumnToRendererAssociation fieldNames2[] = new ColumnToRendererAssociation[actualSize];
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (fieldNames[i] != null) { 
				fieldNames2[j] = fieldNames[i];
				j++;
			}
		}
		return fieldNames2;
	}




	/*
	 * This is called when the list select has changed its value.
	 */
	@Override 
	public void valueChange(ValueChangeEvent<Set<String>> event) {

		Set <String> valueSet = event.getValue();
		if (valueSet.size() > 0 ) {
			Iterator itr = valueSet.iterator();
			while (itr.hasNext()) {
				String tableName = (String) itr.next();
				this.populateGrid(tableName,  this.mainDomainTableLayout);
			}
		}
		int debug = 1;
		debug++;
	}


	
	 
	RegistrationRecordCollection mgr =  new RegistrationRecordCollection();
	final Grid<RegistrationRecord> registrationGrid = new Grid(RegistrationRecord.class); 
	/**
	 * Add a grid for the implied domain table to the layout.
	 * 
	 * @param layout
	 */
	void populateRegistrationGrid(VerticalLayout mLayout) { 
		// instead of new Grid(beanType)
		// PropertySet<RegistrationRecord> ps = BeanPropertySet.get(RegistrationRecord.class);
		 
		registrationGrid.setColumns("lastName", "firstName","chineseName", "gender", "freeWillOffering", "age");
//		registrationGrid.addColumn("chineseName").setCaption(RegistrationRecordLabel.ChineseNameLabel);
//		registrationGrid.addColumn("firstName").setCaption(RegistrationRecordLabel.EnglishFirstNameLabel);
//		registrationGrid.addColumn("lastName").setCaption(RegistrationRecordLabel.EnglishLastNameLabel);
//		registrationGrid.addColumn("gender").setCaption(RegistrationRecordLabel.GenderLabel);
//		registrationGrid.addColumn("adultFlag").setCaption(RegistrationRecordLabel.AdultLabel);
//		registrationGrid.addColumn("greaterThanFiveYearsOldFlag").setCaption(RegistrationRecordLabel.GreaterThanFiveYearOldLabel);
//		registrationGrid.addColumn("freeWillOffering").setCaption(RegistrationRecordLabel.DonationLabel);
//		registrationGrid.addColumn("age").setCaption(RegistrationRecordLabel.AgeLabel);
//		registrationGrid.addColumn("specialRequest").setCaption(RegistrationRecordLabel.SpecialNeedLabel);
//		registrationGrid.addColumn("needFancialFlag").setCaption(RegistrationRecordLabel.FinancialNeedLabel);
		registrationGrid.setItems(mgr.populateDefaultRecords());
		registrationGrid.setHeight(300, Unit.PIXELS);
		registrationGrid.setWidth(100, Unit.PERCENTAGE);
		registrationGrid.setVisible(true);
		//BiConsumer<RegistrationRecord, String> chineseNameSetter = RegistrationRecord::setChineseName;
		
		Setter<RegistrationRecord, String> aSetter = RegistrationRecord::setChineseName;
		registrationGrid.getEditor().setEnabled(true);  
		registrationGrid.getColumn("chineseName")
                .setCaption("中文名")
                .setEditorComponent(new TextField())   // need a second argument for the setter!!
                .setExpandRatio(1);
		registrationGrid.getColumn("lastName")
		        .setCaption("Last Name")
		        .setEditorComponent(new TextField())   // need a second argument for the setter!!
		        .setExpandRatio(1);  

		Binder<RegistrationRecord> binder = registrationGrid.getEditor().getBinder(); 
		CheckBox adultFlagField = new CheckBox();
		
  		
		Column aColumn = registrationGrid.addComponentColumn(record -> {
		    adultFlagField.addValueChangeListener(listener->{
				  if (adultFlagField.getValue()) {
					  record.setAdultFlag(true);
				  } else {
					  record.setAdultFlag(false);
				  }
			});  
		    return adultFlagField;
		});
		
		Binding<RegistrationRecord, Boolean> adultBinding = binder.bind(
				adultFlagField, RegistrationRecord::isAdultFlag, RegistrationRecord::setAdultFlag); 
		
		aColumn.setEditorBinding(adultBinding);
		aColumn.setCaption("Adult");
		
		registrationGrid.addComponentColumn(record -> {
		      Button button = new Button("Remove!");
		      button.addClickListener(click ->
		      	removeARegistrationRecord(record));
		      return button;
		});
		
		
		registrationGrid.getEditor().addSaveListener(event -> {
            try {
            	// this sc is a JpaController
                // sc.edit(event.getBean());
            	int test = 1;
            	test++;
            } catch (Exception ex) {
                //LOG.log(Level.SEVERE, null, ex);
            	ex.printStackTrace();
            }
        }); 
		
		mLayout.addComponent(registrationGrid); 
		 
		
		newButton.addClickListener(click -> {
			mgr.addRecord(new RegistrationRecord());
			registrationGrid.setItems(mgr.getCollection()); 
		});

	}
	
	
	void removeARegistrationRecord(RegistrationRecord aRecord) {
		Notification.show("Remove " + aRecord.getFirstName() + aRecord.getLastName());
		mgr.removeRecord(aRecord);
		registrationGrid.setItems(mgr.getCollection()); 
	}
	
}
