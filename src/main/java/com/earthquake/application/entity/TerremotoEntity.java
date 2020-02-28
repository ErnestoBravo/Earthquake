package com.earthquake.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TerremotoEntity implements Serializable {
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -4088614444086701879L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	private Integer id;

	@Column(name = "mag", nullable = true)
	private String mag;

	@Column(name = "place", nullable = true)
	private String place;

	@Column(name = "time", nullable = true)
	private String time;

	@Column(name = "updated", nullable = true)
	private String updated;

	@Column(name = "tz", nullable = true)
	private String tz;

	@Column(name = "type", nullable = true)
	private String type;

	public String getMag() {
		return mag;
	}

	public void setMag(String mag) {
		this.mag = mag;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Terremoto [id=" + id + ", mag=" + mag + ", place=" + place + ", time=" + time + ", updated=" + updated
				+ ", tz=" + tz + ", type=" + type + "]";
	}
}
