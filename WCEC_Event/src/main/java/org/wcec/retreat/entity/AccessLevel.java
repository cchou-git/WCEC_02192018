package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the access_level database table.
 * 
 */
@Entity
@Table(name="access_level")
@NamedQuery(name="AccessLevel.findAll", query="SELECT a FROM AccessLevel a")
public class AccessLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(nullable=false)
	private int level;

	//bi-directional many-to-one association to UserTbl
	@OneToMany(mappedBy="accessLevel")
	private List<UserTbl> userTbls;

	public AccessLevel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<UserTbl> getUserTbls() {
		return this.userTbls;
	}

	public void setUserTbls(List<UserTbl> userTbls) {
		this.userTbls = userTbls;
	}

	public UserTbl addUserTbl(UserTbl userTbl) {
		getUserTbls().add(userTbl);
		userTbl.setAccessLevel(this);

		return userTbl;
	}

	public UserTbl removeUserTbl(UserTbl userTbl) {
		getUserTbls().remove(userTbl);
		userTbl.setAccessLevel(null);

		return userTbl;
	}

}