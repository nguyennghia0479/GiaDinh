package com.website.giadinh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.website.giadinh.validation.Name;

@Entity
@Table(name = "NganhHoc")
@Access(AccessType.FIELD)
public class NganhHoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Ma_Nganh", length = 10)
	@NotEmpty
	private String maNganh;

	@Column(name = "Ten_Nganh", length = 100)
	@NotEmpty
	@Name
	private String tenNganh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Ma_Khoa")
	private Khoa khoa;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nganhHoc")
	private Set<LopHoc> lopHoc = new HashSet<LopHoc>();

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public String getTenNganh() {
		return tenNganh;
	}

	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public Set<LopHoc> getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(Set<LopHoc> lopHoc) {
		this.lopHoc = lopHoc;
	}
}