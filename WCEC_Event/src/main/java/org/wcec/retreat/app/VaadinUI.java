package org.wcec.retreat.app;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;
import org.wcec.retreat.authentication.AuthService;
import org.wcec.retreat.authentication.PublicComponent;
import org.wcec.retreat.authentication.UserService;
import org.wcec.retreat.domain.generated.AllDomainTable;
import org.wcec.retreat.domain.generated.WCECDomainTable;
import org.wcec.retreat.entity.AccessLevel;
import org.wcec.retreat.entity.AddressTbl;
import org.wcec.retreat.entity.BuildingTbl;
import org.wcec.retreat.entity.ChurchTbl;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.entity.GroupTbl;
import org.wcec.retreat.entity.RegistrationTbl;
import org.wcec.retreat.entity.RoomTbl;
import org.wcec.retreat.entity.UserTbl;
import org.wcec.retreat.extendedgui.MyTableMaintenanceDesign;
import org.wcec.retreat.gui.vaadin.extended.MyListSelect;
import org.wcec.retreat.model.Meal;
import org.wcec.retreat.model.MealPlan;
import org.wcec.retreat.model.MealTemplate;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;
import org.wcec.retreat.repo.AccessLevelRepo;
import org.wcec.retreat.repo.AddressTblRepo;
import org.wcec.retreat.repo.AttendingTblRepo;
import org.wcec.retreat.repo.BedTypeTblRepo;
import org.wcec.retreat.repo.BuildingTblRepo;
import org.wcec.retreat.repo.ChurchTblRepo;
import org.wcec.retreat.repo.EmailTblRepo;
import org.wcec.retreat.repo.EventHostTblRepo;
import org.wcec.retreat.repo.EventTblRepo;
import org.wcec.retreat.repo.EventTypeTblRepo;
import org.wcec.retreat.repo.GroupTblRepo;
import org.wcec.retreat.repo.GroupTypeTblRepo;
import org.wcec.retreat.repo.JustPersonEntityRepository;
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
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Setter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
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
public class VaadinUI extends UI implements ValueChangeListener<Set<String>>, SelectionListener<RegistrationRecord> {
	
	public static final int ADMIN_LEVEL = 200;
		
	
	@Autowired
	AccessLevelRepo                            TheAccessLevelRepo;
	
	@Autowired
	AddressTblRepo                             TheAddressTblRepo;
	
	@Autowired
	AttendingTblRepo                           TheAttendingTblRepo;


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
	JustPersonEntityRepository                 TheJustPersonEntityRepository;

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
	

	public static List<ChurchTbl> AllChurch;
	public static List<AddressTbl> AllAddress;
	public static List<GroupTbl> AllGroup;
	
	 
		

	 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final EventTblRepo eventTblRepo;

	private final EventTblEditor editor;
	
	public static final String SESSION_GROUP_ID = "SESSION_GROUP_ID";
	public static final String SESSION_ACTIVE_EVENT = "SESSION_ACTIVE_EVENT";

	 

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
	
	final VerticalLayout mainDomainTableLayout = new MyTableMaintenanceDesign(); 
	final Button newButton = new Button("Add");
	final Button submitButton = new Button("Submit");
	
	private LoginWindow mMyLoginWindow;
	
	boolean loginFlag = false;
	
	public static EventTbl TheActiveEvent;
	
	
	protected void setupData() {
		fetchEvent();
		TheEntityManager = entityManager;
		TheEMF = emf;
		adminManager = new AdminDisplayManager();
		adminManager.setMyUI(this);
		System.out.println( "init()");
		
		if (AllChurch == null) {
			AllChurch = TheChurchTblRepo.findAll();
			AllGroup = TheGroupTblRepo.findAll();
			AllAddress = TheAddressTblRepo.findAll();
		}
		
	}
	
	private EventTbl fetchEvent() {
		List <EventTbl> eventList = TheEventTblRepo.findAllByOrderByStartDtDesc();
		if (eventList != null) {
			// Major kludge - relying on this for 2018 !!
			if (TheActiveEvent == null || activeEventDateList == null) {
				TheActiveEvent = eventList.get(0);
				//VaadinSession.getCurrent().setAttribute(VaadinUI.SESSION_ACTIVE_EVENT, TheActiveEvent);
				Date startDate0 = TheActiveEvent.getStartDt();
				Date endDate0 = TheActiveEvent.getEndDt();
				DateTime startDate = new DateTime(startDate0);
				DateTime endDate = new DateTime(endDate0);
				activeEventDateList = new ArrayList<DateTime> ();
				int days = Days.daysBetween(startDate, endDate).getDays() + 1;
				for (int i=0; i < days; i++) {
				    DateTime d = startDate.withFieldAdded(DurationFieldType.days(), i);
				    activeEventDateList.add(d);
				}
			}
		} 
		return TheActiveEvent;
	}
	
	
	String currentUserName;
	RegistrationTbl currentRegistrationRecord; // whoever logs on to the system...
	UIHelper uiHelper = new UIHelper();
	
	// Admin related display is managed by this.
	AdminDisplayManager adminManager;
	
	public void processUserLogin(String userName) {
		setupData();
		currentUserName = userName;
		UserTbl theUser = (UserTbl) VaadinSession.getCurrent().getAttribute(UserService.SESSION_USER);
		AccessLevel level = theUser.getAccessLevel();
		if (level.getLevel() == ADMIN_LEVEL) {
			List<RegistrationTbl> records = TheRegistrationTblRepo.findByNoLodging(TheActiveEvent.getId());
			displayAdminPage(records);
		} else {
			// find out whether the user has been registrated or not
			currentRegistrationRecord = TheRegistrationTblRepo.findByChineseName(TheActiveEvent.getId(), this.currentUserName);
			if (currentRegistrationRecord != null) {
				Integer groupID = currentRegistrationRecord.getGroupTbl().getId();
				VaadinSession.getCurrent().setAttribute(SESSION_GROUP_ID, groupID);
				// if yes, find out about its registration group (14)	
				List<RegistrationTbl> records = TheRegistrationTblRepo.findByGroupId(currentRegistrationRecord.getGroupTbl().getId());
				displayUserPage(records);
			} else {
					displayUserPage(null);
			}
		}
	}
	
	Navigator navigator;
	public static final String LOGOUT_VIEW = "LOGOUT";
	
	public Navigator getNavigator() { return navigator; }
	
	
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		TheApplicationContext = appContext; // kludge
		VaadinSession.getCurrent().setAttribute(VaadinUI.SESSION_ACTIVE_EVENT, TheActiveEvent);
		setupData() ;
		appContext.getBean(MealPlan.class).init();
		/**
		 * A word/reminder of how to use the @Bean and @Autowired
		 * The @Bean method must create the associated bean so that it can be
		 * hooked up to the @Autowired interface.
		 * 
		 * In this case, the @Bean is placed in the Application.
		 */
		 
		
		//navigator = new Navigator(this, this);

		//navigator.addView("", this);
        // Create and register the views
        //navigator.addView(LOGOUT_VIEW, new LogoutView());

		PublicComponent wPubComp = new PublicComponent( 
				TheUserLoginRepo, 
				TheUserTblRepo, 
				ThePersonTblRepository );
		if ( !wPubComp.isUserAuthenticated()) {
				setContent(wPubComp);
			} else {
				currentUserName = (String) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_USERNAME);
				// find out whether the user has been registrated or not
				processUserLogin(currentUserName);
			}
	}
	
	final Grid<RoomTbl> roomGrid = new Grid<RoomTbl>(RoomTbl.class);
	
	List<BuildingTbl> bList; // building list
	ListSelect<String> theListSelect; // the ListSelect of the building
	BuildingChangeListener buildingSelectListener;// listens to building selection change
	
	public void displayAdminPage(List<RegistrationTbl> registrationRecords) {
		if (bList == null) {
			bList = this.TheBuildingTblRepo.findAll();
		}
		VerticalLayout mainLayout = new VerticalLayout(); 
		HorizontalLayout belowMainLayout = new HorizontalLayout();
		VerticalLayout leftHandSide = new VerticalLayout();
		VerticalLayout middleSection =  new VerticalLayout();
		VerticalLayout rightHandSide=  new VerticalLayout(); 
				 
		final Label labelTitle = new Label("WCEC Retreat Registration Form - Admin");
		mainLayout.addComponent(labelTitle);
		
		mainLayout.addComponent(logoutButton);
		LogoutListener logoutListener = new LogoutListener(this);
		logoutButton.addClickListener(logoutListener);
		
		theListSelect = new ListSelect<String>("Select Building:");  
		List<String> buildingNameList = new ArrayList<String>();
		boolean first = true;
		for (BuildingTbl eachBuilding : bList) {
			buildingNameList.add(eachBuilding.getName()); 
		}
		theListSelect.setItems(buildingNameList);
		buildingSelectListener = new BuildingChangeListener(roomGrid, bList);
		theListSelect.addValueChangeListener(buildingSelectListener); 
		roomGrid.setColumns("roomNo");
		roomGrid.setWidth("220");
		roomGrid.setHeight("280");
		HorizontalLayout smallControlLayout = new HorizontalLayout();
		smallControlLayout.addComponent(theListSelect);
		smallControlLayout.addComponent(roomGrid);
		leftHandSide.addComponent(smallControlLayout);
		Label aLabel = new Label("Unassigned registrants:");
		leftHandSide.addComponent(aLabel);
		this.populateRegistrationGrid(leftHandSide, registrationRecords, false); 
		
		Button assignButton = new Button("Assign ->"); 
		//assignButton.setStyleName(ValoTheme.BUTTON_LINK);
		//assignButton.setIcon(new ClassResource("/images/assign_arrow.PNG"));
		assignButton.setWidth(120, Unit.PIXELS);
		assignButton.setHeight(50, Unit.PIXELS);
		AssignLodgingListener assignLodgingListener = new AssignLodgingListener(mgr, mgr2, registrationGrid, registrationGrid2);
		assignLodgingListener.setCallback(buildingSelectListener);
		assignLodgingListener.setLodginRepo(TheLodgingAssignmentTblRepo);
		assignLodgingListener.setRegistrationRepo(TheRegistrationTblRepo);
		assignButton.addClickListener(assignLodgingListener);
		 // button.addClickListener(e -> System.out.println("click"));
		Button unassignButton = new Button("<- Unassign");
		//unassignButton.setStyleName(ValoTheme.BUTTON_LINK);
		//unassignButton.setIcon(new ClassResource("/images/unassign_arrow.png"));
		unassignButton.setWidth(120, Unit.PIXELS);
		unassignButton.setHeight(50, Unit.PIXELS);
		UnAssignLodgingListener unAssignLodgingListener = new UnAssignLodgingListener(mgr, mgr2, registrationGrid, registrationGrid2);
		unAssignLodgingListener.setLodginRepo(TheLodgingAssignmentTblRepo);
		unAssignLodgingListener.setRegistrationRepo(TheRegistrationTblRepo);
		unassignButton.addClickListener(unAssignLodgingListener);
		middleSection.addComponent(assignButton);
		middleSection.addComponent(unassignButton);
		List<RegistrationTbl> list = TheRegistrationTblRepo.findByHasLodging(TheActiveEvent.getId());
		mgr2.populateFromDatabase(TheActiveEvent, list);
		adminManager.populateAssignedLodgingRegistrationGrid(rightHandSide, bList, mgr2, registrationGrid2, ThePaymentTblRepo, TheRegistrationTblRepo);
//		mainLayout.addComponent(submitButton);
		belowMainLayout.setSizeFull();
		belowMainLayout.addComponent(leftHandSide);
		belowMainLayout.setExpandRatio(leftHandSide, 6);
		belowMainLayout.addComponent(middleSection); 
		belowMainLayout.setExpandRatio(middleSection, 2);
		belowMainLayout.addComponent(rightHandSide);
		belowMainLayout.setExpandRatio(rightHandSide, 12);
		mainLayout.addComponent(belowMainLayout);
		setContent(mainLayout); 
	}
	
	/**
	 * 
	 * @param aTable
	 */
	private void populateRoomGrid(BuildingTbl aTable) {
		
	}


	public void displayUserPage(List<RegistrationTbl> registrationRecords) {
		VerticalLayout mainLayout = new VerticalLayout();
		final Label labelTitle = new Label("WCEC Retreat Registration Form");
		// labelTitle.setStyleName("h1");
		mainLayout.addComponent(labelTitle);

		mainLayout.addComponent(logoutButton);
		LogoutListener logoutListener = new LogoutListener(this);
		logoutButton.addClickListener(logoutListener);

		final Label labelPerson = new Label("Add a person: ");

		HorizontalLayout hLayout = new HorizontalLayout(labelPerson, newButton);
		hLayout.setVisible(true);
		hLayout.setHeight(100, Unit.PERCENTAGE);
		mainLayout.addComponent(hLayout);
		this.populateRegistrationGrid(mainLayout, registrationRecords, true);
		this.displayMealPlan(mainLayout, TheActiveEvent);
		
		mainLayout.addComponent(submitButton);
		setContent(mainLayout); 
	}

		
	protected void init_old(VaadinRequest request, List<RegistrationTbl> registrationRecords) {
		/*if(!loginFlag) {
			mMyLoginWindow = new LoginWindow(TheUserLoginRepo, TheUserTblRepo, ThePersonTblRepository);
			addWindow(mMyLoginWindow);
			loginFlag = true;
		} else {*/
			VerticalLayout mainLayout = new VerticalLayout();
			final Label labelTitle = new Label("WCEC Retreat Registration Form");
			//labelTitle.setStyleName("h1");
			mainLayout.addComponent(labelTitle);
			final Label labelPerson = new Label("Add a person: "); 
			HorizontalLayout hLayout = new HorizontalLayout(labelPerson, newButton);
			hLayout.setVisible(true); 
			hLayout.setHeight(100, Unit.PERCENTAGE);
			mainLayout.addComponent(hLayout); 
			this.populateRegistrationGrid(mainLayout, registrationRecords, true); 
			mainLayout.addComponent(submitButton);
			setContent(mainLayout);
		//}
	}
	
	protected void init_domain(VaadinRequest request) {
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
	
	
	@Autowired
	private EntityManagerFactory emf;
	
	public static EntityManagerFactory TheEMF;
	
	@Autowired
    private EntityManager entityManager;

	public static ApplicationContext TheApplicationContext;
	public static EntityManager TheEntityManager;
		
	 

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
			Collection theColleciton = repo.findAll();
			for (Object e : theColleciton) {
				Class aClass = e.getClass();
				if (aClass == ChurchTbl.class) {
					ChurchTbl theInstance = (ChurchTbl) e;
					System.out.println(theInstance.getName());
				} 
			}
			theGrid.setItems(theColleciton);	
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


	List<DateTime> activeEventDateList; 
	
	RegistrationRecordCollection mgr =  new RegistrationRecordCollection();   // this is the from manager
	RegistrationRecordCollection mgr2 =  new RegistrationRecordCollection();  // this is used by admin assignment
	final Grid<RegistrationRecord> registrationGrid = new Grid(RegistrationRecord.class);
	final Grid<RegistrationRecord> registrationGrid2= new Grid(RegistrationRecord.class); // this is used for admin containing lodgingssignments
		
	/**
	 * @param layout
	 */
	void populateRegistrationGrid(VerticalLayout mLayout, List<RegistrationTbl> aList, boolean removeFlagOption) { 
		registrationGrid.setColumns("lastName", "firstName","chineseName");
		if (aList == null) {
			registrationGrid.setItems(mgr.populateDefaultRecords(TheActiveEvent));
		} else {
			registrationGrid.setItems(mgr.populateFromDatabase(TheActiveEvent, aList));
		}
		registrationGrid.setWidth(100, Unit.PERCENTAGE);
		registrationGrid.setVisible(true);
		//BiConsumer<RegistrationRecord, String> chineseNameSetter = RegistrationRecord::setChineseName;
		
		Setter<RegistrationRecord, String> aSetter = RegistrationRecord::setChineseName;
		registrationGrid.getColumn("lastName")
        .setEditorComponent(new TextField())   // need a second argument for the setter!!
        .setExpandRatio(1);  
		registrationGrid.getColumn("chineseName")
                .setCaption("中文名")
                .setEditorComponent(new TextField())   // need a second argument for the setter!!
                .setExpandRatio(1);
		registrationGrid.getColumn("firstName")
		        .setEditorComponent(new TextField())   // need a second argument for the setter!!
		        .setExpandRatio(1);  
		 
		List<String> genderList = new ArrayList<String>();
		genderList.add("M");
		genderList.add("F");
		uiHelper.addSelectPropertyColumn(registrationGrid, "genders", "Gender", genderList);
		uiHelper.addBooleanPropertyColumn(registrationGrid, "greaterThanFiveYearsOldFlag", "Greater Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "adultFlag", "Is Adult?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "fullTimeFlag", "Full Time?");
	 	
	 	String dateCaption = "";
	 	for (int i = 0; i < this.activeEventDateList.size(); i++) {
	 		dateCaption = formatJodaDateTime(this.activeEventDateList.get(i));
	 		uiHelper.addBooleanPropertyColumn(registrationGrid, "attendingDate" + (i+1), dateCaption);
	 	}   
	 	
	 	uiHelper.addTextPropertyColumn(registrationGrid, "specialRequest", "Special Request"); 
	 	uiHelper.addTextPropertyColumn(registrationGrid, "freeWillOffering", "Free Will Offering"); 
	 	uiHelper.addBooleanPropertyColumn(registrationGrid, "needFancialFlag", "Need Financial Support");
		if (removeFlagOption) {
			registrationGrid.setHeight(350, Unit.PIXELS);
			registrationGrid.addComponentColumn(record -> {
			      Button button = new Button("Remove!");
			      button.addClickListener(click ->
			      	removeARegistrationRecord(record)); 
			      return button;
			}); 
			registrationGrid.getEditor().setEnabled(true);
			registrationGrid.setSelectionMode(SelectionMode.SINGLE);
			registrationGrid.addSelectionListener(this);
		} else {
			registrationGrid.setHeight(700, Unit.PIXELS);
			registrationGrid.setSelectionMode(SelectionMode.MULTI);
			registrationGrid.getEditor().setEnabled(false);
		}
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
			RegistrationRecord record = new RegistrationRecord();
			record.initialize(TheActiveEvent, appContext.getBean(MealTemplate.class));
			mgr.addRecord(record);
			registrationGrid.setItems(mgr.getCollection()); 
		});
		RegistrationSubmitButtonListener buttonListener = new RegistrationSubmitButtonListener(mgr, TheJustPersonEntityRepository);
		buttonListener.setPersonRepo(ThePersonTblRepository);
		buttonListener.setEmailRepo(TheEmailTblRepo);
		buttonListener.setUserRepo(TheUserTblRepo);
		buttonListener.setRegistrationRepo(TheRegistrationTblRepo);
		buttonListener.setGroupRepo(TheGroupTblRepo);
		buttonListener.setAttendingTblRepo(TheAttendingTblRepo);
		submitButton.addClickListener(buttonListener);
	}
	
	private String formatJodaDateTime(DateTime jodatime) { 
		DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		// Parsing the date  
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
		// Printing the date
		return dtfOut.print(jodatime);
	}
	
	
	
	
	void removeARegistrationRecord(RegistrationRecord aRecord) {
		Notification.show("Remove " + aRecord.getFirstName() + aRecord.getLastName());
		mgr.removeRecord(aRecord);
		registrationGrid.setItems(mgr.getCollection()); 
	}
	
	void setAdultFlagForRegistrationRecord(RegistrationRecord aRecord, CheckBox cbox) {
		if (cbox.getValue()) {
			aRecord.setAdultFlag(true);
		} else {
			aRecord.setAdultFlag(false);
		}
		/*
		mgr.removeRecord(aRecord);
		registrationGrid.setItems(mgr.getCollection());
		*/ 
	}
	
	boolean upsertRegistration() {
		/**
		 * TODO - 
		 * Upsert the list of registration records - this will require some business logic.
		 */
		return true;
	}
	 
	/**
	 * For selecting a building.
	 * 
	 * @return
	 */
	public BuildingTbl getSelectedBuilding() {
		return buildingSelectListener.getSelectedBuilding();
	}

	/**
	 * For selecting a room for assignment.
	 * @return
	 */
	public Set<RoomTbl> getSelectedRoom() {
		return roomGrid.getSelectedItems();
	}
	
	final Button logoutButton = new Button("Log out");
	
	Grid<Meal> mealGrid = new Grid<Meal>(Meal.class);

	/**
	 * Add a table of meals
	 * @param mainLayout
	 */
	Label mealLabel = new Label("Meal Plan for ");
	private void displayMealPlan(VerticalLayout mLayout, EventTbl event) {
		mLayout.addComponent(mealLabel);
		mealGrid.setColumns("mealDescription", "mealPrice","fromAge", "toAge"); 
		mealGrid.setHeight(400, Unit.PIXELS);
		mealGrid.setWidth(100, Unit.PERCENTAGE);
		mealGrid.setVisible(true);
		
		//BiConsumer<RegistrationRecord, String> chineseNameSetter = RegistrationRecord::setChineseName;
		//uiHelper.addBooleanPropertyColumn(mealGrid, "selected", "Selected");

		mealGrid.setSelectionMode(SelectionMode.MULTI);
		mLayout.addComponent(mealGrid);   
	} 

	/**
	 * When user selects a new registration record, display the corresponind meal plan table.
	 */
	@Override
	public void selectionChange(SelectionEvent<RegistrationRecord> event) {
		Optional<RegistrationRecord> selectedRecord = event.getFirstSelectedItem();
		if (selectedRecord.isPresent() && selectedRecord.get() != null) {
			List<Meal> list = selectedRecord.get().getMeals(); 
			mealGrid.setItems(list);
			mealLabel.setCaption("Meal Plan for " + selectedRecord.get().getChineseName());
		}		
	}
}
