package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.SinhVien;

public interface SinhVienDao {
	List<SinhVien> findAll();

	SinhVien findById(String maSV);

	Boolean isExistKey(String maSV);

	void add(SinhVien sinhVien);

	void update(SinhVien sinhVien);

	void delete(SinhVien sinhVien);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<SinhVien> search(String keyword);
}