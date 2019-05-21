package com.website.giadinh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "PhanCong")
public class PhanCong implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maPC")
	private Long maPC;

	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name = "ngayBD")
	@NotNull
	private Date ngayBD;

	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name = "ngayKT")
	@NotNull
	private Date ngayKT;

	@Column(name = "hocKy")
	@NotNull
	private Integer hocKy;

	@Column(name = "namHoc")
	@NotNull
	private Integer namHoc;

	@Column(name = "trangThai")
	private int trangThai;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maGV")
	private GiangVien giangVien;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maLop")
	private LopHoc lopHoc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maMH")
	private MonHoc monHoc;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phanCong")
	private Set<ThoiKhoaBieu> thoiKhoaBieus = new HashSet<ThoiKhoaBieu>();

	public Long getMaPC() {
		return maPC;
	}

	public void setMaPC(Long maPC) {
		this.maPC = maPC;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public Integer getHocKy() {
		return hocKy;
	}

	public void setHocKy(Integer hocKy) {
		this.hocKy = hocKy;
	}

	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public Set<ThoiKhoaBieu> getThoiKhoaBieus() {
		return thoiKhoaBieus;
	}

	public void setThoiKhoaBieus(Set<ThoiKhoaBieu> thoiKhoaBieus) {
		this.thoiKhoaBieus = thoiKhoaBieus;
	}

	public Integer getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(Integer namHoc) {
		this.namHoc = namHoc;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}