package com.website.giadinh.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SinhVien")
public class SinhVien extends Person implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maSV", length = 10)
	@NotEmpty
	private String maSV;
	
	@Column(name = "khoa")
	@Min(1)
	@NotNull
	private Integer khoa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maLop")
	private LopHoc lopHoc;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public Integer getKhoa() {
		return khoa;
	}

	public void setKhoa(Integer khoa) {
		this.khoa = khoa;
	}

	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}