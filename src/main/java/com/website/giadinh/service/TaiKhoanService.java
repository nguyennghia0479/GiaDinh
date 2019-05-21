package com.website.giadinh.service;

import java.util.List;

import com.website.giadinh.entity.TaiKhoan;

public interface TaiKhoanService {
	List<TaiKhoan> findAll();

	TaiKhoan findById(String maTK);
	
	TaiKhoan findByPassword(String matKhau);

	void add(TaiKhoan taiKhoan);

	void update(TaiKhoan taiKhoan);

	void delete(TaiKhoan taiKhoan);

	Long countList();

	Long countSearchResult(String keyword);

	List<String> searchAuto(String keyword);

	List<TaiKhoan> search(String keyword);
}