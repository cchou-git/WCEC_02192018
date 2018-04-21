package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the payment_tbl database table.
 * 
 */
@Entity
@Table(name="payment_tbl")
@NamedQuery(name="PaymentTbl.findAll", query="SELECT p FROM PaymentTbl p")
public class PaymentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="amount_paid", precision=10, scale=2)
	private BigDecimal amountPaid;

	@Column(name="free_offering", precision=10, scale=2)
	private BigDecimal freeOffering;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="paid_in_full")
	private byte paidInFull;

	@Column(name="payment_type_tx")
	private String paymentTextTx;

	//bi-directional many-to-one association to RegistrationTbl
	@OneToMany(mappedBy="paymentTbl")
	private List<RegistrationTbl> registrationTbls;

	@Column(name="payment_expected")
	private Boolean paymentExpected;
	
	public PaymentTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public BigDecimal getFreeOffering() {
		return this.freeOffering;
	}

	public void setFreeOffering(BigDecimal freeOffering) {
		this.freeOffering = freeOffering;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public byte getPaidInFull() {
		return this.paidInFull;
	}

	public void setPaidInFull(byte paidInFull) {
		this.paidInFull = paidInFull;
	}

	
	public String getPaymentTextTx() {
		return paymentTextTx;
	}

	public void setPaymentTextTx(String paymentTextTx) {
		this.paymentTextTx = paymentTextTx;
	}

	public List<RegistrationTbl> getRegistrationTbls() {
		return this.registrationTbls;
	}

	public void setRegistrationTbls(List<RegistrationTbl> registrationTbls) {
		this.registrationTbls = registrationTbls;
	}

	public RegistrationTbl addRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().add(registrationTbl);
		registrationTbl.setPaymentTbl(this);

		return registrationTbl;
	}

	public RegistrationTbl removeRegistrationTbl(RegistrationTbl registrationTbl) {
		getRegistrationTbls().remove(registrationTbl);
		registrationTbl.setPaymentTbl(null);

		return registrationTbl;
	}

	public Boolean getPaymentExpected() {
		return paymentExpected;
	}

	public void setPaymentExpected(Boolean paymentExpected) {
		this.paymentExpected = paymentExpected;
	}
	
	

}