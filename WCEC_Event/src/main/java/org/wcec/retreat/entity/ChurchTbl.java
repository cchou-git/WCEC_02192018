package org.wcec.retreat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the church_tbl database table.
 * 
 */
@Entity
@Table(name="church_tbl")
@NamedQuery(name="ChurchTbl.findAll", query="SELECT c FROM ChurchTbl c")
public class ChurchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to PersonTbl
	@OneToMany(mappedBy="churchTbl",  fetch=FetchType.EAGER)
	private List<PersonTbl> personTbls;

	public ChurchTbl() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PersonTbl> getPersonTbls() {
		return this.personTbls;
	}

	public void setPersonTbls(List<PersonTbl> personTbls) {
		this.personTbls = personTbls;
	}

	public PersonTbl addPersonTbl(PersonTbl personTbl) {
		getPersonTbls().add(personTbl);
		personTbl.setChurchTbl(this);

		return personTbl;
	}

	public PersonTbl removePersonTbl(PersonTbl personTbl) {
		getPersonTbls().remove(personTbl);
		personTbl.setChurchTbl(null);

		return personTbl;
	}

}