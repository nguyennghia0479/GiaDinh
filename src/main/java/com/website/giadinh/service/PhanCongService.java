package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.PhanCong;

public interface PhanCongService {
	List<PhanCong> findAll();

	PhanCong findById(Integer maPC);

	Boolean isExistReference(Integer maPC);

	void add(PhanCong phanCong);

	void update(PhanCong phanCong);

	void delete(PhanCong phanCong);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<PhanCong> search(String keyword);
}