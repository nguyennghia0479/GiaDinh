package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.entity.NganhHoc;

public interface LopHocDao {
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
	
	List<NganhHoc> getNganhHocList();
}