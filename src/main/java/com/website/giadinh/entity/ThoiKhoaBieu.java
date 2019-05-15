package com.website.giadinh.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ThoiKhoaBieu")
public class ThoiKhoaBieu implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maPhong")
	private PhongHoc phongHoc;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maBH")
	private BuoiHoc buoiHoc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maPC")
	private PhanCong phanCong;

	public PhongHoc getPhongHoc() {
		return phongHoc;
	}

	public void setPhongHoc(PhongHoc phongHoc) {
		this.phongHoc = phongHoc;
	}

	public BuoiHoc getBuoiHoc() {
		return buoiHoc;
	}

	public void setBuoiHoc(BuoiHoc buoiHoc) {
		this.buoiHoc = buoiHoc;
	}

	public PhanCong getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(PhanCong phanCong) {
		this.phanCong = phanCong;
	}
}