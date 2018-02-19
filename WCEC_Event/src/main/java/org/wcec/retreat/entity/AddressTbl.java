package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the address_tbl database table.
 * 
 */
@Entity
@Table(name="address_tbl")
@NamedQuery(name="AddressTbl.findAll", query="SELECT a FROM AddressTbl a")
public class AddressTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(nullable=false, length=80)
	private String line1;

	@Column(length=80)
	private String line2;

	@Column(length=2)
	private String state;

	@Column(name="zip_code", nullable=false, length=10)
	private String zipCode;

	//bi-directional many-to-one association to PersonTbl
	@OneToMany(mappedBy="addressTbl")
	private List<PersonTbl> personTbls;

	public AddressTbl() {
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

	public String getLine1() {
		return this.line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<PersonTbl> getPersonTbls() {
		return this.personTbls;
	}

	public void setPersonTbls(List<PersonTbl> personTbls) {
		this.personTbls = personTbls;
	}

	public PersonTbl addPersonTbl(PersonTbl personTbl) {
		getPersonTbls().add(personTbl);
		personTbl.setAddressTbl(this);

		return personTbl;
	}

	public PersonTbl removePersonTbl(PersonTbl personTbl) {
		getPersonTbls().remove(personTbl);
		personTbl.setAddressTbl(null);

		return personTbl;
	}

}