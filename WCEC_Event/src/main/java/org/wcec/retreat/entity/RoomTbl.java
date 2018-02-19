package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the room_tbl database table.
 * 
 */
@Entity
@Table(name="room_tbl")
@NamedQuery(name="RoomTbl.findAll", query="SELECT r FROM RoomTbl r")
public class RoomTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="handicap_access_ind")
	private byte handicapAccessInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="room_no", nullable=false, length=6)
	private String roomNo;

	@Column(name="room_price", nullable=false, precision=10, scale=2)
	private BigDecimal roomPrice;

	//bi-directional many-to-one association to LodgingAssignmentTbl
	//@OneToMany(mappedBy="roomTbl")
	//private List<LodgingAssignmentTbl> lodgingAssignmentTbls;

	//bi-directional many-to-one association to BuildingTbl
	@ManyToOne
	@JoinColumn(name="building_id")
	private BuildingTbl buildingTbl;

	public RoomTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getHandicapAccessInd() {
		return this.handicapAccessInd;
	}

	public void setHandicapAccessInd(byte handicapAccessInd) {
		this.handicapAccessInd = handicapAccessInd;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public BigDecimal getRoomPrice() {
		return this.roomPrice;
	}

	public void setRoomPrice(BigDecimal roomPrice) {
		this.roomPrice = roomPrice;
	}

	/*public List<LodgingAssignmentTbl> getLodgingAssignmentTbls() {
		return this.lodgingAssignmentTbls;
	}

	public void setLodgingAssignmentTbls(List<LodgingAssignmentTbl> lodgingAssignmentTbls) {
		this.lodgingAssignmentTbls = lodgingAssignmentTbls;
	}

	public LodgingAssignmentTbl addLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().add(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setRoomTbl(this);

		return lodgingAssignmentTbl;
	}

	public LodgingAssignmentTbl removeLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().remove(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setRoomTbl(null);

		return lodgingAssignmentTbl;
	} */

	public BuildingTbl getBuildingTbl() {
		return this.buildingTbl;
	}

	public void setBuildingTbl(BuildingTbl buildingTbl) {
		this.buildingTbl = buildingTbl;
	}

}