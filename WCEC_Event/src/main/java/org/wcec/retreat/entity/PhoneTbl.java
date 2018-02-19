package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the phone_tbl database table.
 * 
 */
@Entity
@Table(name="phone_tbl")
@NamedQuery(name="PhoneTbl.findAll", query="SELECT p FROM PhoneTbl p")
public class PhoneTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="area_code", nullable=false, length=3)
	private String areaCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="phone_number", nullable=false, length=7)
	private String phoneNumber;

	//bi-directional many-to-one association to PersonTbl
	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private PersonTbl personTbl;

	//bi-directional many-to-one association to PhoneTypeTbl
	@ManyToOne
	@JoinColumn(name="phone_type_id", nullable=false)
	private PhoneTypeTbl phoneTypeTbl;

	public PhoneTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PersonTbl getPersonTbl() {
		return this.personTbl;
	}

	public void setPersonTbl(PersonTbl personTbl) {
		this.personTbl = personTbl;
	}

	public PhoneTypeTbl getPhoneTypeTbl() {
		return this.phoneTypeTbl;
	}

	public void setPhoneTypeTbl(PhoneTypeTbl phoneTypeTbl) {
		this.phoneTypeTbl = phoneTypeTbl;
	}

}