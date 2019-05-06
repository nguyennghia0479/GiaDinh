package com.website.giadinh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "GiangVien")
public class GiangVien extends Person implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Ma_GV", length = 10)
	private String maGV;

	@Column(name = "Tham_Nien")
	private Integer thamNien;

	@Column(name = "Trinh_Do", length = 50)
	private String trinhDo;

	@Column(name = "Quyen", length = 10)
	private String quyen;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Ma_Khoa")
	private Khoa khoa;
	
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "giangVien")
	private Set<PhanCong> phanCong = new HashSet<PhanCong>();

	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}

	public Integer getThamNien() {
		return thamNien;
	}

	public void setThamNien(Integer thamNien) {
		this.thamNien = thamNien;
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}
}