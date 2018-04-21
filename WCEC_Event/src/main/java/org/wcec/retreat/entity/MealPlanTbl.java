package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the meal_plan_tbl database table.
 * 
 */
@Entity
@Table(name="meal_plan_tbl")
@NamedQuery(name="MealPlanTbl.findAll", query="SELECT m FROM MealPlanTbl m")
public class MealPlanTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to RegistrationTbl
	@ManyToOne
	@JoinColumn(name="registration_id")
	private RegistrationTbl registrationTbl;

	//bi-directional many-to-one association to MealTbl
	@ManyToOne
	@JoinColumn(name="meal_id")
	private MealTbl mealTbl;
	    

	public MealPlanTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public RegistrationTbl getRegistrationTbl() {
		return this.registrationTbl;
	}

	public void setRegistrationTbl(RegistrationTbl registrationTbl) {
		this.registrationTbl = registrationTbl;
	}

	public MealTbl getMealTbl() {
		return this.mealTbl;
	}

	public void setMealTbl(MealTbl mealTbl) {
		this.mealTbl = mealTbl;
	}

	
}