package com.website.giadinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tenTK", length = 10)
	private String maTK;
	
	@Column(name = "matKhau")
	private String matKhau;
	
	@Column(name = "quyen", length = 10)
	private String quyen;

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
}