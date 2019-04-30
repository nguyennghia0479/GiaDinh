package com.website.giadinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maTK;
	private String matKhau;
	private Integer quyen;
	private SinhVien sinhVien;

	@Id
	@Column(name = "MaTK", length = 10)
	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	@Column(name = "MatKhau", length = 50)
	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Column(name = "Quyen")
	public Integer getQuyen() {
		return quyen;
	}

	public void setQuyen(Integer quyen) {
		this.quyen = quyen;
	}

	@OneToOne(mappedBy = "taiKhoan")
	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
}