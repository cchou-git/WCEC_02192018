package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event_host_tbl database table.
 * 
 */
@Entity
@Table(name="event_host_tbl")
@NamedQuery(name="EventHostTbl.findAll", query="SELECT e FROM EventHostTbl e")
public class EventHostTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="host_name", nullable=false, length=60)
	private String hostName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	@Column(name="last_updt_user_id", nullable=false)
	private int lastUpdtUserId;

	//bi-directional many-to-one association to PersonTbl
	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private PersonTbl personTbl;

	//bi-directional many-to-one association to EventTbl
	@OneToMany(mappedBy="eventHostTbl")
	private List<EventTbl> eventTbls;

	public EventHostTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	public int getLastUpdtUserId() {
		return this.lastUpdtUserId;
	}

	public void setLastUpdtUserId(int lastUpdtUserId) {
		this.lastUpdtUserId = lastUpdtUserId;
	}

	public PersonTbl getPersonTbl() {
		return this.personTbl;
	}

	public void setPersonTbl(PersonTbl personTbl) {
		this.personTbl = personTbl;
	}

	public List<EventTbl> getEventTbls() {
		return this.eventTbls;
	}

	public void setEventTbls(List<EventTbl> eventTbls) {
		this.eventTbls = eventTbls;
	}

	public EventTbl addEventTbl(EventTbl eventTbl) {
		getEventTbls().add(eventTbl);
		eventTbl.setEventHostTbl(this);

		return eventTbl;
	}

	public EventTbl removeEventTbl(EventTbl eventTbl) {
		getEventTbls().remove(eventTbl);
		eventTbl.setEventHostTbl(null);

		return eventTbl;
	}

}