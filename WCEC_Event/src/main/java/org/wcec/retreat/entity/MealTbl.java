package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the meal_tbl database table.
 * 
 */
@Entity
@Table(name="meal_tbl")
@NamedQuery(name="MealTbl.findAll", query="SELECT m FROM MealTbl m")
public class MealTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="from_age", nullable=false)
	private int fromAge;
	
	@Column(name="to_age", nullable=false)
	private int toAge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="meal_description", nullable=false, length=45)
	private String mealDescription;

	@Column(name="meal_price", nullable=false, precision=10, scale=2)
	private BigDecimal mealPrice;

	//bi-directional many-to-one association to MealPlanTbl
	@OneToMany(mappedBy="mealTbl")
	private List<MealPlanTbl> mealPlanTbls;

	//bi-directional many-to-one association to EventTbl
	@ManyToOne
	@JoinColumn(name="event_id", nullable=false)
	private EventTbl eventTbl;

	public MealTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	} 

	public int getFromAge() {
		return fromAge;
	}

	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public String getMealDescription() {
		return this.mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	public BigDecimal getMealPrice() {
		return this.mealPrice;
	}

	public void setMealPrice(BigDecimal mealPrice) {
		this.mealPrice = mealPrice;
	}

	public List<MealPlanTbl> getMealPlanTbls() {
		return this.mealPlanTbls;
	}

	public void setMealPlanTbls(List<MealPlanTbl> mealPlanTbls) {
		this.mealPlanTbls = mealPlanTbls;
	}

	public MealPlanTbl addMealPlanTbl(MealPlanTbl mealPlanTbl) {
		getMealPlanTbls().add(mealPlanTbl);
		mealPlanTbl.setMealTbl(this);

		return mealPlanTbl;
	}

	public MealPlanTbl removeMealPlanTbl(MealPlanTbl mealPlanTbl) {
		getMealPlanTbls().remove(mealPlanTbl);
		mealPlanTbl.setMealTbl(null);

		return mealPlanTbl;
	}

	public EventTbl getEventTbl() {
		return this.eventTbl;
	}

	public void setEventTbl(EventTbl eventTbl) {
		this.eventTbl = eventTbl;
	}

}