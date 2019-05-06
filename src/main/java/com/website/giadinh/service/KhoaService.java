package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.Khoa;

public interface KhoaService {
	List<Khoa> findAll();

	Khoa findById(String maKhoa);
	
	Boolean isExistKey(String maKhoa);
	
	Boolean isExistReference(String maKhoa);

	void add(Khoa khoa);

	void update(Khoa khoa);

	void delete(Khoa khoa);
	
	Long countList();
	
	Long countSearchResult(String keyword);
	
	List<String> searchAuto(String keyword);
	
	List<Khoa> search(String keyword);
}