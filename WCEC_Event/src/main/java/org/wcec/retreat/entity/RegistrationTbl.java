package org.wcec.retreat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the registration_tbl database table.
 * 
 */
@Entity
@Table(name="registration_tbl")
@NamedQuery(name="RegistrationTbl.findAll", query="SELECT r FROM RegistrationTbl r")
public class RegistrationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="discount_percentage", precision=10, scale=2)
	private BigDecimal discountPercentage;

	@Column(name="comment")
	private String comment;

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="part_time_ind", nullable=false)
	private byte partTimeInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registration_date")
	private Date registrationDate;

	//bi-directional many-to-one association to MealPlanTbl
	@OneToMany(mappedBy="registrationTbl")
	private List<MealPlanTbl> mealPlanTbls;

	//bi-directional many-to-one association to MealPlanTbl
	@ManyToOne
	@JoinColumn(name="meal_plan_id")
	private MealPlanTbl mealPlanTbl;

	//bi-directional many-to-one association to EventTbl
	@ManyToOne
	@JoinColumn(name="event_id")
	private EventTbl eventTbl;

	//bi-directional many-to-one association to LodgingAssignmentTbl
	@ManyToOne
	@JoinColumn(name="lodging_assignment_id")
	private LodgingAssignmentTbl lodgingAssignmentTbl;

	//bi-directional many-to-one association to PersonTbl
	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private PersonTbl personTbl;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", nullable=true)
	private PaymentTbl paymentTbl;

	//bi-directional many-to-one association to GroupTbl
	@ManyToOne
	@JoinColumn(name="group_id", nullable=false)
	private GroupTbl groupTbl;

	//bi-directional many-to-one association to MealPlanTbl
	@OneToMany(mappedBy="registrationTbl",  fetch = FetchType.EAGER)
	private Set<AttendingTbl> attendingTbls;

	public Set<AttendingTbl> getAttendingTbls() {
		return attendingTbls;
	}

	public void setAttendingTbls(Set<AttendingTbl> attendingTbls) {
		this.attendingTbls = attendingTbls;
	}

	public RegistrationTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public byte getPartTimeInd() {
		return this.partTimeInd;
	}

	public void setPartTimeInd(byte partTimeInd) {
		this.partTimeInd = partTimeInd;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<MealPlanTbl> getMealPlanTbls() {
		return this.mealPlanTbls;
	}

	public void setMealPlanTbls(List<MealPlanTbl> mealPlanTbls) {
		this.mealPlanTbls = mealPlanTbls;
	}

	public MealPlanTbl addMealPlanTbl(MealPlanTbl mealPlanTbl) {
		getMealPlanTbls().add(mealPlanTbl);
		mealPlanTbl.setRegistrationTbl(this);

		return mealPlanTbl;
	}

	public MealPlanTbl removeMealPlanTbl(MealPlanTbl mealPlanTbl) {
		getMealPlanTbls().remove(mealPlanTbl);
		mealPlanTbl.setRegistrationTbl(null);

		return mealPlanTbl;
	}

	public MealPlanTbl getMealPlanTbl() {
		return this.mealPlanTbl;
	}

	public void setMealPlanTbl(MealPlanTbl mealPlanTbl) {
		this.mealPlanTbl = mealPlanTbl;
	}

	public EventTbl getEventTbl() {
		return this.eventTbl;
	}

	public void setEventTbl(EventTbl eventTbl) {
		this.eventTbl = eventTbl;
	}

	public LodgingAssignmentTbl getLodgingAssignmentTbl() {
		return this.lodgingAssignmentTbl;
	}

	public void setLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		this.lodgingAssignmentTbl = lodgingAssignmentTbl;
	}

	public PersonTbl getPersonTbl() {
		return this.personTbl;
	}

	public void setPersonTbl(PersonTbl personTbl) {
		this.personTbl = personTbl;
	}

	public PaymentTbl getPaymentTbl() {
		return this.paymentTbl;
	}

	public void setPaymentTbl(PaymentTbl paymentTbl) {
		this.paymentTbl = paymentTbl;
	}

	public GroupTbl getGroupTbl() {
		return this.groupTbl;
	}

	public void setGroupTbl(GroupTbl groupTbl) {
		this.groupTbl = groupTbl;
	}

}