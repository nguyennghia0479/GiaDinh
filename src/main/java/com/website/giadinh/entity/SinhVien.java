package com.website.giadinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SinhVien")
public class SinhVien extends Person implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Ma_SV", length = 10)
	private String maSV;
	
	@Column(name = "Khoa")
	private Integer khoa;
	
	@Column(name = "Quyen", length = 10)
	private String quyen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Ma_Lop")
	private LopHoc lopHoc;
	
	@OneToOne(fetch = FetchType.EAGER)
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

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
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