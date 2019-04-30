package com.website.giadinh.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LopHoc")
public class LopHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maLop;
	private NganhHoc nganhHoc;
	private List<SinhVien> sinhVien;
	private Set<PhanCong> phanCong;

	@Id
	@Column(name = "MaLop", length = 10)
	public String getMaLop() {
		return maLop;
	}
	
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaNganh", nullable = false)
	public NganhHoc getNganhHoc() {
		return nganhHoc;
	}

	public void setNganhHoc(NganhHoc nganhHoc) {
		this.nganhHoc = nganhHoc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lopHoc")
	public List<SinhVien> getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(List<SinhVien> sinhVien) {
		this.sinhVien = sinhVien;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lopHoc")
	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}
}