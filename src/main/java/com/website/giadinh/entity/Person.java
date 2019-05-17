package com.website.giadinh.entity;

import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.website.giadinh.validator.Contact;
import com.website.giadinh.validator.Name;

@MappedSuperclass
public class Person {
	@Column(name = "hoTen", length = 100)
	@NotEmpty
	@Name
	private String hoTen;

	@Column(name = "gioiTinh", length = 5)
	@NotEmpty
	private String gioiTinh;

	@Column(name = "diaChi")
	@NotEmpty
	private String diaChi;

	@Column(name = "SDT", length = 10)
	@NotEmpty
	@Contact
	private String SDT;

	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaySinh")
	@NotNull
	private Date ngaySinh;

	@Column(name = "noiSinh")
	private String noiSinh;

	@Column(name = "email")
	@Email
	@NotEmpty
	private String email;

	@Column(name = "hinh", length = 50000000)
	private byte[] hinhAnh;

	@Column(name = "quyen")
	private String quyen;

	@Transient
	private String mode = "add";

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

	@Transient
	public String getBase64() {
		return Base64.getEncoder().encodeToString(hinhAnh);
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
}