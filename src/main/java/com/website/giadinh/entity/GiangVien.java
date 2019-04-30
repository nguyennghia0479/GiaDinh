package com.website.giadinh.entity;

import java.util.Date;
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
@Table(name = "GiangVien")
public class GiangVien implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maGV;
	private String hoTen;
	private String gioiTinh;
	private String diaChi;
	private String SDT;
	private Date ngaySinh;
	private String noiSinh;
	private String email;
	private byte[] hinhAnh;
	private Integer thamNien;
	private String trinhDo;
	private Integer quyen;
	private Khoa khoa;
	private Set<PhanCong> phanCong;

	@Id
	@Column(name = "MaGV", length = 10)
	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
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

	@Column(name = "ThamNien")
	public Integer getThamNien() {
		return thamNien;
	}

	public void setThamNien(Integer thamNien) {
		this.thamNien = thamNien;
	}

	@Column(name = "TrinhDo", length = 50)
	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	@Column(name = "Quyen")
	public Integer getQuyen() {
		return quyen;
	}

	public void setQuyen(Integer quyen) {
		this.quyen = quyen;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaKhoa", nullable = false)
	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "giangVien")
	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}
}