package com.website.giadinh.entity;

import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "LopHoc")
public class LopHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maLop", length = 10)
	@NotEmpty
	private String maLop;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maNganh")
	private NganhHoc nganhHoc;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lopHoc")
	private List<SinhVien> sinhVien = new ArrayList<SinhVien>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lopHoc")
	private Set<PhanCong> phanCong = new HashSet<PhanCong>();
	
	@Transient
	private String mode = "add";

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public NganhHoc getNganhHoc() {
		return nganhHoc;
	}

	public void setNganhHoc(NganhHoc nganhHoc) {
		this.nganhHoc = nganhHoc;
	}

	public List<SinhVien> getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(List<SinhVien> sinhVien) {
		this.sinhVien = sinhVien;
	}

	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}