package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event_tbl database table.
 * 
 */
@Entity
@Table(name="event_tbl")
@NamedQuery(name="EventTbl.findAll", query="SELECT e FROM EventTbl e")
public class EventTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_in_dt", nullable=false)
	private Date checkInDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="early_registration_dt", nullable=false)
	private Date earlyRegistrationDt;

	@Temporal(TemporalType.DATE)
	@Column(name="end_dt", nullable=false)
	private Date endDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="last_updt_user_id", nullable=false)
	private int lastUpdtUserId;

	@Column(name="registration_fee", nullable=false, precision=10, scale=2)
	private BigDecimal registrationFee;

	@Column(name="special_note", length=200)
	private String specialNote;

	@Temporal(TemporalType.DATE)
	@Column(name="start_dt", nullable=false)
	private Date startDt;

	//bi-directional many-to-one association to EventTypeTbl
	@ManyToOne
	@JoinColumn(name="event_type_id", nullable=false)
	private EventTypeTbl eventTypeTbl;

	//bi-directional many-to-one association to EventHostTbl
	@ManyToOne
	@JoinColumn(name="event_host_id", nullable=false)
	private EventHostTbl eventHostTbl;

	//bi-directional many-to-one association to MealTbl
	@OneToMany(mappedBy="eventTbl")
	private List<MealTbl> mealTbls;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="eventTbl")
	private List<RegistrationTbl> registrationTbls;

	public EventTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCheckInDt() {
		return this.checkInDt;
	}

	public void setCheckInDt(Date checkInDt) {
		this.checkInDt = checkInDt;
	}

	public Date getEarlyRegistrationDt() {
		return this.earlyRegistrationDt;
	}

	public void setEarlyRegistrationDt(Date earlyRegistrationDt) {
		this.earlyRegistrationDt = earlyRegistrationDt;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public int getLastUpdtUserId() {
		return this.lastUpdtUserId;
	}

	public void setLastUpdtUserId(int lastUpdtUserId) {
		this.lastUpdtUserId = lastUpdtUserId;
	}

	public BigDecimal getRegistrationFee() {
		return this.registrationFee;
	}

	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}

	public String getSpecialNote() {
		return this.specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public EventTypeTbl getEventTypeTbl() {
		return this.eventTypeTbl;
	}

	public void setEventTypeTbl(EventTypeTbl eventTypeTbl) {
		this.eventTypeTbl = eventTypeTbl;
	}

	public EventHostTbl getEventHostTbl() {
		return this.eventHostTbl;
	}

	public void setEventHostTbl(EventHostTbl eventHostTbl) {
		this.eventHostTbl = eventHostTbl;
	}

	public List<MealTbl> getMealTbls() {
		return this.mealTbls;
	}

	public void setMealTbls(List<MealTbl> mealTbls) {
		this.mealTbls = mealTbls;
	}

	public MealTbl addMealTbl(MealTbl mealTbl) {
		getMealTbls().add(mealTbl);
		mealTbl.setEventTbl(this);

		return mealTbl;
	}

	public MealTbl removeMealTbl(MealTbl mealTbl) {
		getMealTbls().remove(mealTbl);
		mealTbl.setEventTbl(null);

		return mealTbl;
	}

	public List<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(List<RegistrationTbl> registrationTbls) {
		this.registrationTbls = registrationTbls;
	}

	public RegistrationTbl addRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().add(registrationTbl);
		registrationTbl.setEventTbl(this);

		return registrationTbl;
	}

	public RegistrationTbl removeRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().remove(registrationTbl);
		registrationTbl.setEventTbl(null);

		return registrationTbl;
	}

}