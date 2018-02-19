package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the group_type_tbl database table.
 * 
 */
@Entity
@Table(name="group_type_tbl")
@NamedQuery(name="GroupTypeTbl.findAll", query="SELECT g FROM GroupTypeTbl g")
public class GroupTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=40)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to GroupTbl
	@OneToMany(mappedBy="groupTypeTbl",  fetch=FetchType.EAGER)
	private List<GroupTbl> groupTbls;

	public GroupTypeTbl() {
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

	public List<GroupTbl> getGroupTbls() {
		return this.groupTbls;
	}

	public void setGroupTbls(List<GroupTbl> groupTbls) {
		this.groupTbls = groupTbls;
	}

	public GroupTbl addGroupTbl(GroupTbl groupTbl) {
		getGroupTbls().add(groupTbl);
		groupTbl.setGroupTypeTbl(this);

		return groupTbl;
	}

	public GroupTbl removeGroupTbl(GroupTbl groupTbl) {
		getGroupTbls().remove(groupTbl);
		groupTbl.setGroupTypeTbl(null);

		return groupTbl;
	}

}