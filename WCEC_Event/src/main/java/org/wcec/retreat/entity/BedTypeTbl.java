package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bed_type_tbl database table.
 * 
 */
@Entity
@Table(name="bed_type_tbl")
@NamedQuery(name="BedTypeTbl.findAll", query="SELECT b FROM BedTypeTbl b")
public class BedTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="bed_type", nullable=false, length=45)
	private String bedType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to LodgingAssignmentTbl
	//@OneToMany(mappedBy="bedTypeTbl")
	//private List<LodgingAssignmentTbl> lodgingAssignmentTbls;

	public BedTypeTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBedType() {
		return this.bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	/*public List<LodgingAssignmentTbl> getLodgingAssignmentTbls() {
		return this.lodgingAssignmentTbls;
	}

	public void setLodgingAssignmentTbls(List<LodgingAssignmentTbl> lodgingAssignmentTbls) {
		this.lodgingAssignmentTbls = lodgingAssignmentTbls;
	}

	public LodgingAssignmentTbl addLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().add(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setBedTypeTbl(this);

		return lodgingAssignmentTbl;
	}

	public LodgingAssignmentTbl removeLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().remove(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setBedTypeTbl(null);

		return lodgingAssignmentTbl;
	} */

}