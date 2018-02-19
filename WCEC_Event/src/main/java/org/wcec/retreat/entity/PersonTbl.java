package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	//bi-directional many-to-one association to EmailTbl
	@OneToMany(mappedBy="personTbl")
	private List<EmailTbl> emailTbls;

	//bi-directional many-to-one association to EventHostTbl
	@OneToMany(mappedBy="personTbl")
	private List<EventHostTbl> eventHostTbls;

	//bi-directional many-to-one association to LodgingAssignmentTbl
	@OneToMany(mappedBy="personTbl")
	private List<LodgingAssignmentTbl> lodgingAssignmentTbls;

	//bi-directional many-to-one association to PaymentTbl
	@OneToMany(mappedBy="personTbl")
	private List<PaymentTbl> paymentTbls;

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
	@OneToMany(mappedBy="personTbl")
	private List<PhoneTbl> phoneTbls;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="personTbl")
	private List<RegistrationTbl> registrationTbls;

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

	public List<EmailTbl> getEmailTbls() {
		return this.emailTbls;
	}

	public void setEmailTbls(List<EmailTbl> emailTbls) {
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

	public List<EventHostTbl> getEventHostTbls() {
		return this.eventHostTbls;
	}

	public void setEventHostTbls(List<EventHostTbl> eventHostTbls) {
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

	public List<LodgingAssignmentTbl> getLodgingAssignmentTbls() {
		return this.lodgingAssignmentTbls;
	}

	public void setLodgingAssignmentTbls(List<LodgingAssignmentTbl> lodgingAssignmentTbls) {
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

	public List<PaymentTbl> getPaymentTbls() {
		return this.paymentTbls;
	}

	public void setPaymentTbls(List<PaymentTbl> paymentTbls) {
		this.paymentTbls = paymentTbls;
	}

	public PaymentTbl addPaymentTbl(PaymentTbl paymentTbl) {
		getPaymentTbls().add(paymentTbl);
		paymentTbl.setPersonTbl(this);

		return paymentTbl;
	}

	public PaymentTbl removePaymentTbl(PaymentTbl paymentTbl) {
		getPaymentTbls().remove(paymentTbl);
		paymentTbl.setPersonTbl(null);

		return paymentTbl;
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

	public List<PhoneTbl> getPhoneTbls() {
		return this.phoneTbls;
	}

	public void setPhoneTbls(List<PhoneTbl> phoneTbls) {
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

	public List<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(List<RegistrationTbl> registrationTbls) {
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

}