package com.website.giadinh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "MonHoc")
public class MonHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maMH", length = 10)
	@NotEmpty
	private String maMH;

	@Column(name = "tenMH", length = 100)
	@NotEmpty
	private String tenMH;

	@Column(name = "soTC")
	private Integer soTC;

	@Column(name = "lyThuyet")
	private Integer lyThuyet;

	@Column(name = "thucHanh")
	private Integer thucHanh;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "monHoc")
	private Set<PhanCong> phanCong = new HashSet<PhanCong>();

	@Transient
	private String mode = "add";

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public Integer getSoTC() {
		return soTC;
	}

	public void setSoTC(Integer soTC) {
		this.soTC = soTC;
	}

	public Integer getLyThuyet() {
		return lyThuyet;
	}

	public void setLyThuyet(Integer lyThuyet) {
		this.lyThuyet = lyThuyet;
	}

	public Integer getThucHanh() {
		return thucHanh;
	}

	public void setThucHanh(Integer thucHanh) {
		this.thucHanh = thucHanh;
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