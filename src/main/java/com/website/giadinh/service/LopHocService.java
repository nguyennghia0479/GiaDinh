package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.LopHoc;

public interface LopHocService {
	List<LopHoc> findAll();
	
	LopHoc findById(String maLop);
	
	Boolean isExistKey(String maLop);
	
	Boolean isExistReference(String maLop);

	void add(LopHoc lopHoc);

	void update(LopHoc lopHoc);

	void delete(LopHoc lopHoc);
	
	Long countList();
	
	Long countSearchResult(String keyword);
	
	List<String> searchAuto(String keyword);
	
	List<LopHoc> search(String keyword);
}