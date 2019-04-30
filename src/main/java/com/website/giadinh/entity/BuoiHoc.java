package com.website.giadinh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BuoiHoc")
public class BuoiHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maBH;
	private Integer thu;
	private Integer ca;
	private Set<ThoiKhoaBieu> tkb;

	@Id
	@Column(name = "MaBH", length = 10)
	public String getMaBH() {
		return maBH;
	}

	public void setMaBH(String maBH) {
		this.maBH = maBH;
	}

	@Column(name = "Thu")
	public Integer getThu() {
		return thu;
	}

	public void setThu(Integer thu) {
		this.thu = thu;
	}

	@Column(name = "Ca")
	public Integer getCa() {
		return ca;
	}

	public void setCa(Integer ca) {
		this.ca = ca;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "buoiHoc")
	public Set<ThoiKhoaBieu> getTkb() {
		return tkb;
	}

	public void setTkb(Set<ThoiKhoaBieu> tkb) {
		this.tkb = tkb;
	}
}