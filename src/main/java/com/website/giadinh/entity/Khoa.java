package com.website.giadinh.entity;

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
	private String maKhoa;
	private String tenKhoa;
	private Set<NganhHoc> nganhHoc;
	private List<GiangVien> giangVien;

	@NotEmpty
	@Id
	@Column(name = "MaKhoa", length = 10)
	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	@NotEmpty
	@Name
	@Column(name = "TenKhoa", length = 100)
	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khoa")
	public Set<NganhHoc> getNganhHoc() {
		return nganhHoc;
	}

	public void setNganhHoc(Set<NganhHoc> nganhHoc) {
		this.nganhHoc = nganhHoc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khoa")
	public List<GiangVien> getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(List<GiangVien> giangVien) {
		this.giangVien = giangVien;
	}
}