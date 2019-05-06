package com.website.giadinh.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.website.giadinh.validation.Name;

@Entity
@Table(name = "Khoa")
public class Khoa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Ma_Khoa", length = 10)
	@NotEmpty
	private String maKhoa;

	@Column(name = "Ten_Khoa", length = 100)
	@NotEmpty
	@Name
	private String tenKhoa;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khoa")
	private Set<NganhHoc> nganhHoc = new HashSet<NganhHoc>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khoa")
	private List<GiangVien> giangVien = new ArrayList<GiangVien>();

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public Set<NganhHoc> getNganhHoc() {
		return nganhHoc;
	}

	public void setNganhHoc(Set<NganhHoc> nganhHoc) {
		this.nganhHoc = nganhHoc;
	}

	public List<GiangVien> getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(List<GiangVien> giangVien) {
		this.giangVien = giangVien;
	}
}