package com.website.giadinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tenTK", length = 10)
	@NotEmpty
	private String maTK;
	
	@Column(name = "matKhau")
	@NotEmpty
	private String matKhau;
	
	@Column(name = "quyen", length = 10)
	private int quyen;

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

	public int getQuyen() {
		return quyen;
	}

	public void setQuyen(int quyen) {
		this.quyen = quyen;
	}
}