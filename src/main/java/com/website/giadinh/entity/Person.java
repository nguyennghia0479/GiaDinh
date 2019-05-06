package com.website.giadinh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
	@Column(name = "Ho_Ten", length = 100)
	private String hoTen;
	
	@Column(name = "Gioi_Tinh", length = 5)
	private String gioiTinh;
	
	@Column(name = "Dia_Chi")
	private String diaChi;
	
	@Column(name = "SDT", length = 10)
	private String SDT;
	
	@Column(name = "Ngay_Sinh")
	private Date ngaySinh;
	
	@Column(name = "Noi_Sinh")
	private String noiSinh;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Hinh_Anh")
	private byte[] hinhAnh;
	

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
}