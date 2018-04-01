package org.wcec.retreat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the attending_tbl database table.
 * 
 */
@Entity
@Table(name="attending_tbl")
@NamedQuery(name="AttendingTbl.findAll", query="SELECT a FROM AttendingTbl a")
public class AttendingTbl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="attending_date")
	private Date attendingDate;
	//bi-directional many-to-one association to RegistrationTbl
	@ManyToOne
	@JoinColumn(name="registration_id")
	private RegistrationTbl registrationTbl;

	public AttendingTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAttendingDate() {
		return this.attendingDate;
	}

	public void setAttendingDate(Date attendingDate) {
		this.attendingDate = attendingDate;
	}

	public RegistrationTbl getRegistrationTbl() {
		return registrationTbl;
	}

	public void setRegistrationTbl(RegistrationTbl registrationTbl) {
		this.registrationTbl = registrationTbl;
	}
	
	

}