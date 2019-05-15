package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.TaiKhoanDao;
import com.website.giadinh.entity.TaiKhoan;

@Service("taiKhoanService")
@Transactional
public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired
	private TaiKhoanDao taiKhoanDao;

	@Override
	public List<TaiKhoan> findAll() {
		return taiKhoanDao.findAll();
	}

	@Override
	public TaiKhoan findById(String maTK) {
		return taiKhoanDao.findById(maTK);
	}

	@Override
	public void add(TaiKhoan taiKhoan) {
		taiKhoanDao.add(taiKhoan);
	}

	@Override
	public void update(TaiKhoan taiKhoan) {
		taiKhoanDao.update(taiKhoan);
	}

	@Override
	public void delete(TaiKhoan taiKhoan) {
		taiKhoanDao.delete(taiKhoan);
	}

	@Override
	public Long countList() {
		return taiKhoanDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return taiKhoanDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return taiKhoanDao.searchAuto(keyword);
	}

	@Override
	public List<TaiKhoan> search(String keyword) {
		return taiKhoanDao.search(keyword);
	}
}