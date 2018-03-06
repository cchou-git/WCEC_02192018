package org.wcec.retreat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the person_tbl database table.
 * 
 */
@Entity
@Table(name="person_tbl")
@NamedQuery(name="JustPersonEntity.findAll", query="SELECT p FROM PersonTbl p")
public class JustPersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_dt", nullable=false)
	private Date birthDt;

	@Column(name="chinese_nm", length=60)
	private String chineseNm;

	@Column(name="first_nm", nullable=false, length=45)
	private String firstNm;

	@Column(nullable=false, length=6)
	private String gender;

	@Column(name="last_nm", nullable=false, length=45)
	private String lastNm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;


	public JustPersonEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChineseNm() {
		return this.chineseNm;
	}

	public void setChineseNm(String chineseNm) {
		this.chineseNm = chineseNm;
	}


	public String getLastNm() {
		return this.lastNm;
	}

	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

}