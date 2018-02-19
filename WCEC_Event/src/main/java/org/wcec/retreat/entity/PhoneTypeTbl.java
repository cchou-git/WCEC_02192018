package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the phone_type_tbl database table.
 * 
 */
@Entity
@Table(name="phone_type_tbl")
@NamedQuery(name="PhoneTypeTbl.findAll", query="SELECT p FROM PhoneTypeTbl p")
public class PhoneTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=10)
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	//bi-directional many-to-one association to PhoneTbl
	@OneToMany(mappedBy="phoneTypeTbl")
	private List<PhoneTbl> phoneTbls;

	public PhoneTypeTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public List<PhoneTbl> getPhoneTbls() {
		return this.phoneTbls;
	}

	public void setPhoneTbls(List<PhoneTbl> phoneTbls) {
		this.phoneTbls = phoneTbls;
	}

	public PhoneTbl addPhoneTbl(PhoneTbl phoneTbl) {
		getPhoneTbls().add(phoneTbl);
		phoneTbl.setPhoneTypeTbl(this);

		return phoneTbl;
	}

	public PhoneTbl removePhoneTbl(PhoneTbl phoneTbl) {
		getPhoneTbls().remove(phoneTbl);
		phoneTbl.setPhoneTypeTbl(null);

		return phoneTbl;
	}

}