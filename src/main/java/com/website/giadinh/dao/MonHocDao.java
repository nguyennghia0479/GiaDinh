package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.MonHoc;

public interface MonHocDao {
	List<MonHoc> findAll();

	MonHoc findById(String maMH);
	
	Boolean isExistKey(String maMH);
	
	Boolean isExistReference(String maMH);

	void add(MonHoc monHoc);

	void update(MonHoc monHoc);

	void delete(MonHoc monHoc);
	
	Long countList();
	
	Long countSearchResult(String keyword);
	
	List<String> searchAuto(String keyword);
	
	List<MonHoc> search(String keyword);
}