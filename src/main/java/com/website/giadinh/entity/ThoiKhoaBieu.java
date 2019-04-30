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
	private PhanCong phanCong;
	private BuoiHoc buoiHoc;
	private PhongHoc phongHoc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaPC", nullable = false)
	public PhanCong getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(PhanCong phanCong) {
		this.phanCong = phanCong;
	}

	@Id()
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maBH", nullable = false)
	public BuoiHoc getBuoiHoc() {
		return buoiHoc;
	}

	public void setBuoiHoc(BuoiHoc buoiHoc) {
		this.buoiHoc = buoiHoc;
	}
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaPhong", nullable = false)
	public PhongHoc getPhongHoc() {
		return phongHoc;
	}

	public void setPhongHoc(PhongHoc phongHoc) {
		this.phongHoc = phongHoc;
	}
}