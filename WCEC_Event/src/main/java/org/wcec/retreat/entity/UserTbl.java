package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_tbl database table.
 * 
 */
@Entity
@Table(name="user_tbl")
@NamedQuery(name="UserTbl.findAll", query="SELECT u FROM UserTbl u")
public class UserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(nullable=false, length=40)
	private String password;

	@Column(name="person_id", nullable=false)
	private int personId;

	@Column(name="user_email_id", nullable=false)
	private int userEmailId;

	//bi-directional many-to-one association to AccessLevel
	@ManyToOne
	@JoinColumn(name="access_level_id")
	private AccessLevel accessLevel;

	public UserTbl() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getUserEmailId() {
		return this.userEmailId;
	}

	public void setUserEmailId(int userEmailId) {
		this.userEmailId = userEmailId;
	}

	public AccessLevel getAccessLevel() {
		return this.accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

}