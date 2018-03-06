package org.wcec.retreat.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the group_tbl database table.
 * 
 */
@Entity
@Table(name="group_tbl")
@NamedQuery(name="GroupTbl.findAll", query="SELECT g FROM GroupTbl g")
public class GroupTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="group_nm", nullable=false, length=40)
	private String groupNm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="parent_id")
	private Integer parentId;

	@Column(name="person_id")
	private Integer personId;

	//bi-directional many-to-one association to GroupTypeTbl
	@ManyToOne
	@JoinColumn(name="group_type")
	private GroupTypeTbl groupTypeTbl;

	//bi-directional many-to-one association to PersonTbl
	@OneToMany(mappedBy="groupTbl", fetch = FetchType.EAGER)
	private Set<PersonTbl> personTbls;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="groupTbl", fetch = FetchType.EAGER)
	private Set<RegistrationTbl> registrationTbls;

	public GroupTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupNm() {
		return this.groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public GroupTypeTbl getGroupTypeTbl() {
		return this.groupTypeTbl;
	}

	public void setGroupTypeTbl(GroupTypeTbl groupTypeTbl) {
		this.groupTypeTbl = groupTypeTbl;
	}

	public Set<PersonTbl> getPersonTbls() {
		return this.personTbls;
	}

	public void setPersonTbls(Set<PersonTbl> personTbls) {
		this.personTbls = personTbls;
	}

	public PersonTbl addPersonTbl(PersonTbl personTbl) {
		getPersonTbls().add(personTbl);
		personTbl.setGroupTbl(this);

		return personTbl;
	}

	public PersonTbl removePersonTbl(PersonTbl personTbl) {
		getPersonTbls().remove(personTbl);
		personTbl.setGroupTbl(null);

		return personTbl;
	}

	public Set<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(Set<RegistrationTbl> registrationTbls) {
		this.registrationTbls = registrationTbls;
	}

	public RegistrationTbl addRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().add(registrationTbl);
		registrationTbl.setGroupTbl(this);

		return registrationTbl;
	}

	public RegistrationTbl removeRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().remove(registrationTbl);
		registrationTbl.setGroupTbl(null);

		return registrationTbl;
	}

}