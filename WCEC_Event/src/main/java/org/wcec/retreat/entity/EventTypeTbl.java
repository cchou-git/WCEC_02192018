package org.wcec.retreat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event_type_tbl database table.
 * 
 */
@Entity
@Table(name="event_type_tbl")
@NamedQuery(name="EventTypeTbl.findAll", query="SELECT e FROM EventTypeTbl e")
public class EventTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updt_ts", nullable=false)
	private Date lastUpdtTs;

	/* This collection by default will use lazy loading. 
	 * I am in the process of doing my domain tables and do not need
	 * this distraction. I am wondering what would happen if I comment 
	 * this list out. 1/6/2018
	 */
	//bi-directional many-to-one association to EventTbl
	//@OneToMany(mappedBy="eventTypeTbl")
	//private List<EventTbl> eventTbls;

	public EventTypeTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdtTs() {
		return this.lastUpdtTs;
	}

	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}

	/*public List<EventTbl> getEventTbls() {
		return this.eventTbls;
	}

	public void setEventTbls(List<EventTbl> eventTbls) {
		this.eventTbls = eventTbls;
	}

	public EventTbl addEventTbl(EventTbl eventTbl) {
		getEventTbls().add(eventTbl);
		eventTbl.setEventTypeTbl(this);

		return eventTbl;
	}

	public EventTbl removeEventTbl(EventTbl eventTbl) {
		getEventTbls().remove(eventTbl);
		eventTbl.setEventTypeTbl(null);

		return eventTbl;
	} */

}