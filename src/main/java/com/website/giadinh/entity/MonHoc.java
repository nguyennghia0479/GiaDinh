package com.website.giadinh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MonHoc")
public class MonHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maMH;
	private String tenMH;
	private Integer soTC;
	private Set<PhanCong> phanCong;

	@Id
	@Column(name = "MaMH", length = 10)
	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	@Column(name = "TenMH", length = 100)
	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	@Column(name = "SoTC")
	public Integer getSoTC() {
		return soTC;
	}

	public void setSoTC(Integer soTC) {
		this.soTC = soTC;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "monHoc")
	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}
}