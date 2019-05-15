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

@Entity
@Table(name = "PhanCong")
public class PhanCong implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maPC")
	private Long maPC;

	@Column(name = "ngayBD")
	private Date ngayBD;

	@Column(name = "ngayKT")
	private Date ngayKT;

	@Column(name = "hocKy")
	private Integer hocKy;

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
}