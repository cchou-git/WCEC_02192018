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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the person_tbl database table.
 * 
 */
@Entity
@Table(name="person_tbl")
@NamedQuery(name="PersonTbl.findAll", query="SELECT p FROM PersonTbl p")
public class PersonTbl implements Serializable {
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

	@Column(name="primary_group_id")
	private int primaryGroupId;
	
	@Column(name="is_adult_flag")
	private Boolean isAdultFlag;

	@Column(name="is_greater_than_five")
	private Boolean isGreaterThanFive;

	//bi-directional many-to-one association to EmailTbl
	@OneToMany(mappedBy="personTbl",  fetch = FetchType.EAGER)
	private Set<EmailTbl> emailTbls;

	//bi-directional many-to-one association to EventHostTbl
	@OneToMany(mappedBy="personTbl",  fetch = FetchType.EAGER)
	private Set<EventHostTbl> eventHostTbls;

	//bi-directional many-to-one association to LodgingAssignmentTbl
	@OneToMany(mappedBy="personTbl",  fetch = FetchType.EAGER)
	private Set<LodgingAssignmentTbl> lodgingAssignmentTbls;

	//bi-directional many-to-one association to GroupTbl
	@ManyToOne
	@JoinColumn(name="secondary_group_id")
	private GroupTbl groupTbl;

	//bi-directional many-to-one association to ChurchTbl
	@ManyToOne
	@JoinColumn(name="church_id")
	private ChurchTbl churchTbl;

	//bi-directional many-to-one association to AddressTbl
	@ManyToOne
	@JoinColumn(name="address_id", nullable=false)
	private AddressTbl addressTbl;

	//bi-directional many-to-one association to PhoneTbl
	@OneToMany(mappedBy="personTbl",  fetch = FetchType.EAGER)
	private Set<PhoneTbl> phoneTbls;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="personTbl",  fetch = FetchType.EAGER)
	private Set<RegistrationTbl> registrationTbls;

	public PersonTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDt() {
		return this.birthDt;
	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	public String getChineseNm() {
		return this.chineseNm;
	}

	public void setChineseNm(String chineseNm) {
		this.chineseNm = chineseNm;
	}

	public String getFirstNm() {
		return this.firstNm;
	}

	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getPrimaryGroupId() {
		return this.primaryGroupId;
	}

	public void setPrimaryGroupId(int primaryGroupId) {
		this.primaryGroupId = primaryGroupId;
	}

	public Set<EmailTbl> getEmailTbls() {
		return this.emailTbls;
	}

	public void setEmailTbls(Set<EmailTbl> emailTbls) {
		this.emailTbls = emailTbls;
	}

	public EmailTbl addEmailTbl(EmailTbl emailTbl) {
		getEmailTbls().add(emailTbl);
		emailTbl.setPersonTbl(this);

		return emailTbl;
	}

	public EmailTbl removeEmailTbl(EmailTbl emailTbl) {
		getEmailTbls().remove(emailTbl);
		emailTbl.setPersonTbl(null);

		return emailTbl;
	}

	public Set<EventHostTbl> getEventHostTbls() {
		return this.eventHostTbls;
	}

	public void setEventHostTbls(Set<EventHostTbl> eventHostTbls) {
		this.eventHostTbls = eventHostTbls;
	}

	public EventHostTbl addEventHostTbl(EventHostTbl eventHostTbl) {
		getEventHostTbls().add(eventHostTbl);
		eventHostTbl.setPersonTbl(this);

		return eventHostTbl;
	}

	public EventHostTbl removeEventHostTbl(EventHostTbl eventHostTbl) {
		getEventHostTbls().remove(eventHostTbl);
		eventHostTbl.setPersonTbl(null);

		return eventHostTbl;
	}

	public Set<LodgingAssignmentTbl> getLodgingAssignmentTbls() {
		return this.lodgingAssignmentTbls;
	}

	public void setLodgingAssignmentTbls(Set<LodgingAssignmentTbl> lodgingAssignmentTbls) {
		this.lodgingAssignmentTbls = lodgingAssignmentTbls;
	}

	public LodgingAssignmentTbl addLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().add(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setPersonTbl(this);

		return lodgingAssignmentTbl;
	}

	public LodgingAssignmentTbl removeLodgingAssignmentTbl(LodgingAssignmentTbl lodgingAssignmentTbl) {
		getLodgingAssignmentTbls().remove(lodgingAssignmentTbl);
		lodgingAssignmentTbl.setPersonTbl(null);

		return lodgingAssignmentTbl;
	}

	public GroupTbl getGroupTbl() {
		return this.groupTbl;
	}

	public void setGroupTbl(GroupTbl groupTbl) {
		this.groupTbl = groupTbl;
	}

	public ChurchTbl getChurchTbl() {
		return this.churchTbl;
	}

	public void setChurchTbl(ChurchTbl churchTbl) {
		this.churchTbl = churchTbl;
	}

	public AddressTbl getAddressTbl() {
		return this.addressTbl;
	}

	public void setAddressTbl(AddressTbl addressTbl) {
		this.addressTbl = addressTbl;
	}

	public Set<PhoneTbl> getPhoneTbls() {
		return this.phoneTbls;
	}

	public void setPhoneTbls(Set<PhoneTbl> phoneTbls) {
		this.phoneTbls = phoneTbls;
	}

	public PhoneTbl addPhoneTbl(PhoneTbl phoneTbl) {
		getPhoneTbls().add(phoneTbl);
		phoneTbl.setPersonTbl(this);

		return phoneTbl;
	}

	public PhoneTbl removePhoneTbl(PhoneTbl phoneTbl) {
		getPhoneTbls().remove(phoneTbl);
		phoneTbl.setPersonTbl(null);

		return phoneTbl;
	}

	public Set<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(Set<RegistrationTbl> registrationTbls) {
		this.registrationTbls = registrationTbls;
	}

	public RegistrationTbl addRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().add(registrationTbl);
		registrationTbl.setPersonTbl(this);

		return registrationTbl;
	}

	public RegistrationTbl removeRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().remove(registrationTbl);
		registrationTbl.setPersonTbl(null);

		return registrationTbl;
	}

	public Boolean getIsAdultFlag() {
		return isAdultFlag;
	}

	public void setIsAdultFlag(Boolean isAdultFlag) {
		this.isAdultFlag = isAdultFlag;
	}

	public Boolean getIsGreaterThanFive() {
		return isGreaterThanFive;
	}

	public void setIsGreaterThanFive(Boolean isGreaterThanFive) {
		this.isGreaterThanFive = isGreaterThanFive;
	}
	
	@Column(name="is_less_than_five")
	private Boolean isLessThanFive;

	@Column(name="is_five_to_twelve")
	private Boolean isFiveToTwelve;
	
	@Column(name="is_thirteen_to_eighteen")
	private Boolean isThirteenToEighteen;

	public Boolean getIsLessThanFive() {
		return isLessThanFive;
	}

	public void setIsLessThanFive(Boolean isLessThanFive) {
		this.isLessThanFive = isLessThanFive;
	}

	public Boolean getIsFiveToTwelve() {
		return isFiveToTwelve;
	}

	public void setIsFiveToTwelve(Boolean isFiveToTwelve) {
		this.isFiveToTwelve = isFiveToTwelve;
	}

	public Boolean getIsThirteenToEighteen() {
		return isThirteenToEighteen;
	}

	public void setIsThirteenToEighteen(Boolean isThirteenToEighteen) {
		this.isThirteenToEighteen = isThirteenToEighteen;
	}
	
	
}