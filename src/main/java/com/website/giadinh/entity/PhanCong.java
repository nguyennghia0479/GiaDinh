package com.website.giadinh.entity;

import java.util.Date;
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

@Entity
@Table(name = "PhanCong")
public class PhanCong implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long maPC;
	private Date ngayBD;
	private Date ngayKT;
	private Integer hocKy;
	private Integer namHoc;
	private GiangVien giangVien;
	private LopHoc lopHoc;
	private MonHoc monHoc;
	private Set<ThoiKhoaBieu> tkb;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaPC")
	public Long getMaPC() {
		return maPC;
	}

	public void setMaPC(Long maPC) {
		this.maPC = maPC;
	}

	@Column(name = "NgayBD")
	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	@Column(name = "NgayKT")
	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	@Column(name = "HocKy")
	public Integer getHocKy() {
		return hocKy;
	}

	public void setHocKy(Integer hocKy) {
		this.hocKy = hocKy;
	}

	@Column(name = "NamHoc")
	public Integer getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(Integer namHoc) {
		this.namHoc = namHoc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaGV", nullable = false)
	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaLop", nullable = false)
	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaMH", nullable = false)
	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phanCong")
	public Set<ThoiKhoaBieu> getTkb() {
		return tkb;
	}

	public void setTkb(Set<ThoiKhoaBieu> tkb) {
		this.tkb = tkb;
	}
}