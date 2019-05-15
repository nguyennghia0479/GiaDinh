package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.NganhHoc;

public interface NganhHocService {
	List<NganhHoc> findAll();

	NganhHoc findById(String maNganh);

	Boolean isExistKey(String maNganh);

	Boolean isExistReference(String maNganh);

	void add(NganhHoc nganhHoc);

	void update(NganhHoc nganhHoc);

	void delete(NganhHoc nganhHoc);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<NganhHoc> search(String keyword);	
}