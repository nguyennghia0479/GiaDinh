package com.website.giadinh.dao;

import java.util.List;

import com.website.giadinh.entity.Khoa;

public interface KhoaDao {
	List<Khoa> findAll();

	Khoa findById(String maKhoa);

	void add(Khoa khoa);

	void update(Khoa khoa);

	Boolean delete(String maKhoa);
	
	Long count();
	
	Boolean existKey(String primaryKey);
	
	List<String> searchAuto(String keyword);
	
	List<Khoa> search(String keyword);
}