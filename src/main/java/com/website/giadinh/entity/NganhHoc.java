package com.website.giadinh.entity;

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
@Table(name = "NganhHoc")
public class NganhHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maNganh;
	private String tenNganh;
	private Khoa khoa;
	private Set<LopHoc> lopHoc;

	@Id
	@Column(name = "MaNganh", length = 10)
	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	@Column(name = "TenNganh", length = 100)
	public String getTenNganh() {
		return tenNganh;
	}

	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaKhoa", nullable = false)
	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nganhHoc")
	public Set<LopHoc> getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(Set<LopHoc> lopHoc) {
		this.lopHoc = lopHoc;
	}
}