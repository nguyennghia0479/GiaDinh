package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.PhanCong;

public interface PhanCongDao {
	List<PhanCong> findAll();

	PhanCong findById(Long maPC);

	Boolean isExistReference(Long maPC);

	void add(PhanCong phanCong);

	void update(PhanCong phanCong);

	void delete(PhanCong phanCong);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<PhanCong> search(String keyword);
}