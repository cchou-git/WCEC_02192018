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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the building_tbl database table.
 * 
 */
@Entity
@Table(name="building_tbl")
@NamedQuery(name="BuildingTbl.findAll", query="SELECT b FROM BuildingTbl b")
public class BuildingTbl implements Serializable {
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

	//bi-directional many-to-one association to LodgingAssignmentTbl
	@OneToMany(mappedBy="associatedBuildingTbl", fetch = FetchType.EAGER)
	private Set<LodgingAssignmentTbl> lodgingAssignmentTbls;

	//bi-directional many-to-one association to RoomTbl
	@OneToMany(mappedBy="buildingTbl", fetch = FetchType.EAGER)
	private Set<RoomTbl> roomTbls;

	public BuildingTbl() {
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

	
	public Set<LodgingAssignmentTbl> getLodgingAssignmentTbls() {
		return this.lodgingAssignmentTbls;
	}

	public void setLodgingAssignmentTbls(Set<LodgingAssignmentTbl> lodgingAssignmentTbls) {
		this.lodgingAssignmentTbls = lodgingAssignmentTbls;
	}

	public LodgingAssignmentTbl addLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().add(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setBuildingTbl(this);

		return lodgingAssignmentTbl;
	}

	public LodgingAssignmentTbl removeLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().remove(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setBuildingTbl(null);

		return lodgingAssignmentTbl;
	}
	
	
	public Set<RoomTbl> getRoomTbls() {
		return this.roomTbls;
	}

	public void setRoomTbls(Set<RoomTbl> roomTbls) {
		this.roomTbls = roomTbls;
	}

	public RoomTbl addRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().add(roomTbl);
		roomTbl.setBuildingTbl(this);

		return roomTbl;
	}

	public RoomTbl removeRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().remove(roomTbl);
		roomTbl.setBuildingTbl(null);

		return roomTbl;
	}
	
}