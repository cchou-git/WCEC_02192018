package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the email_tbl database table.
 * 
 */
@Entity
@Table(name="email_tbl")
@NamedQuery(name="EmailTbl.findAll", query="SELECT e FROM EmailTbl e")
public class EmailTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=40)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to PersonTbl
	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private PersonTbl personTbl;

	public EmailTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public PersonTbl getPersonTbl() {
		return this.personTbl;
	}

	public void setPersonTbl(PersonTbl personTbl) {
		this.personTbl = personTbl;
	}

}