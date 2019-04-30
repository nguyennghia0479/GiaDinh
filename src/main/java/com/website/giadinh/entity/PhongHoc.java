package com.website.giadinh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PhongHoc")
public class PhongHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maPhong;
	private String loaiPhong;
	private Set<ThoiKhoaBieu> tkb;

	@Id
	@Column(name = "MaPhong", length = 10)
	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	@Column(name =  "LoaiPhong", length = 20)
	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phongHoc")
	public Set<ThoiKhoaBieu> getTkb() {
		return tkb;
	}

	public void setTkb(Set<ThoiKhoaBieu> tkb) {
		this.tkb = tkb;
	}
}