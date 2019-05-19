package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.PhongHoc;

public interface PhongHocService {
	List<PhongHoc> findAll();

	PhongHoc findById(String maPH);

	Boolean isExistKey(String maPH);

	Boolean isExistReference(String maPH);

	void add(PhongHoc phongHoc);

	void update(PhongHoc phongHoc);

	void delete(PhongHoc phongHoc);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<PhongHoc> search(String keyword);
}