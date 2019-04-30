package com.website.giadinh.entity;

import java.util.Date;

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

@Entity
@Table(name = "SinhVien")
public class SinhVien implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maSV;
	private String hoTen;
	private String gioiTinh;
	private String diaChi;
	private String SDT;
	private Date ngaySinh;
	private String noiSinh;
	private String email;
	private byte[] hinhAnh;
	private Integer khoa;
	private Integer quyen;
	private LopHoc lopHoc;
	private TaiKhoan taiKhoan;

	@Id
	@Column(name = "MaSV", length = 10)
	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	@Column(name = "HoTen", length = 100)
	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	@Column(name = "GioiTinh", length = 10)
	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Column(name = "DiaChi", length = 255)
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Column(name = "SDT", length = 11)
	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	@Column(name = "NgaySinh")
	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	@Column(name = "NoiSinh", length = 255)
	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	@Column(name = "Email", length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HinhAnh", length = 50000000)
	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Column(name = "Khoa")
	public Integer getKhoa() {
		return khoa;
	}

	public void setKhoa(Integer khoa) {
		this.khoa = khoa;
	}

	@Column(name = "Quyen")
	public Integer getQuyen() {
		return quyen;
	}

	public void setQuyen(Integer quyen) {
		this.quyen = quyen;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaLop", nullable = false)
	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}