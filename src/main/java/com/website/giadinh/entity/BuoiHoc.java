package com.website.giadinh.entity;

import java.util.HashSet;
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
	@Id
	@Column(name = "maBH", length = 10)
	private String maBH;
	
	@Column(name = "thu", length = 10, unique = true)
	private String thu = Thu.THU_HAI.thu;
	
	@Column(name = "ca", length = 10, unique = true)
	private String ca = Ca.CA_MOT.ca;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "buoiHoc")
	private Set<ThoiKhoaBieu> thoiKhoaBieus = new HashSet<ThoiKhoaBieu>();

	public String getMaBH() {
		return maBH;
	}

	public void setMaBH(String maBH) {
		this.maBH = maBH;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public Set<ThoiKhoaBieu> getThoiKhoaBieus() {
		return thoiKhoaBieus;
	}

	public void setThoiKhoaBieus(Set<ThoiKhoaBieu> thoiKhoaBieus) {
		this.thoiKhoaBieus = thoiKhoaBieus;
	}
}