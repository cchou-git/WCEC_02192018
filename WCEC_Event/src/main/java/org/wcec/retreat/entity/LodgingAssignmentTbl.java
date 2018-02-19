package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lodging_assignment_tbl database table.
 * 
 */
@Entity
@Table(name="lodging_assignment_tbl")
@NamedQuery(name="LodgingAssignmentTbl.findAll", query="SELECT l FROM LodgingAssignmentTbl l")
public class LodgingAssignmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to BuildingTbl
	@ManyToOne
	@JoinColumn(name="building_id")
	private BuildingTbl associatedBuildingTbl;

	//bi-directional many-to-one association to BedTypeTbl
	@ManyToOne
	@JoinColumn(name="bed_id")
	private BedTypeTbl bedTypeTbl;

	//bi-directional many-to-one association to RoomTbl
	@ManyToOne
	@JoinColumn(name="room_id")
	private RoomTbl roomTbl;

	//bi-directional many-to-one association to PersonTbl
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonTbl personTbl;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="lodgingAssignmentTbl")
	private List<RegistrationTbl> registrationTbls;

	public LodgingAssignmentTbl() {
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

	public BuildingTbl getBuildingTbl() {
		return this.associatedBuildingTbl;
	}

	public void setBuildingTbl(BuildingTbl buildingTbl) {
		this.associatedBuildingTbl = buildingTbl;
	}

	public BedTypeTbl getBedTypeTbl() {
		return this.bedTypeTbl;
	}

	public void setBedTypeTbl(BedTypeTbl bedTypeTbl) {
		this.bedTypeTbl = bedTypeTbl;
	}

	public RoomTbl getRoomTbl() {
		return this.roomTbl;
	}

	public void setRoomTbl(RoomTbl roomTbl) {
		this.roomTbl = roomTbl;
	}

	public PersonTbl getPersonTbl() {
		return this.personTbl;
	}

	public void setPersonTbl(PersonTbl personTbl) {
		this.personTbl = personTbl;
	}

	public List<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(List<RegistrationTbl> registrationTbls) {
		this.registrationTbls = registrationTbls;
	}

	public RegistrationTbl addRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().add(registrationTbl);
		registrationTbl.setLodgingAssignmentTbl(this);

		return registrationTbl;
	}

	public RegistrationTbl removeRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().remove(registrationTbl);
		registrationTbl.setLodgingAssignmentTbl(null);

		return registrationTbl;
	}

}