package com.website.giadinh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PhongHoc")
public class PhongHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maPhong", length = 10)
	@NotEmpty
	private String maPH;

	@Column(name = "loaiPhong", length = 10)
	@NotEmpty
	private String loaiPH;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phongHoc")
	private Set<ThoiKhoaBieu> thoiKhoaBieu = new HashSet<ThoiKhoaBieu>();

	@Transient
	private String mode = "add";

	public String getMaPH() {
		return maPH;
	}

	public void setMaPH(String maPH) {
		this.maPH = maPH;
	}

	public String getLoaiPH() {
		return loaiPH;
	}

	public void setLoaiPH(String loaiPH) {
		this.loaiPH = loaiPH;
	}

	public Set<ThoiKhoaBieu> getThoiKhoaBieu() {
		return thoiKhoaBieu;
	}

	public void setThoiKhoaBieu(Set<ThoiKhoaBieu> thoiKhoaBieu) {
		this.thoiKhoaBieu = thoiKhoaBieu;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}