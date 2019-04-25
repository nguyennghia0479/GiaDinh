package com.website.giadinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.website.giadinh.validation.Name;


@Entity
@Table(name = "Khoa")
public class Khoa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String maKhoa;
	private String tenKhoa;

	@NotEmpty
	@Id
	@Column(name = "maKhoa", length = 10)
	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	@NotEmpty
	@Name
	@Column(name = "tenKhoa", length = 50)
	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

}
