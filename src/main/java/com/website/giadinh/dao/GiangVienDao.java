package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.GiangVien;

public interface GiangVienDao {
	List<GiangVien> findAll();

	GiangVien findById(String maGV);

	Boolean isExistKey(String maGV);

	Boolean isExistReference(String maGV);

	void add(GiangVien giangVien);

	void update(GiangVien giangVien);

	void delete(GiangVien giangVien);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<GiangVien> search(String keyword);
}