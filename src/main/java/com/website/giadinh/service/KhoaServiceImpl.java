package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.KhoaDao;
import com.website.giadinh.entity.Khoa;

@Service("khoaService")
@Transactional
public class KhoaServiceImpl implements KhoaService {
	@Autowired
	private KhoaDao khoaDao;
	
	@Override
	public List<Khoa> findAll() {
		return khoaDao.findAll();
	}

	@Override
	public Khoa findById(String maKhoa) {
		return khoaDao.findById(maKhoa);
	}

	@Override
	public void add(Khoa khoa) {
		khoaDao.add(khoa);
	}

	@Override
	public void update(Khoa khoa) {
		khoaDao.update(khoa);
	}

	@Override
	public Boolean delete(String maKhoa) {
		return khoaDao.delete(maKhoa);
	}

	@Override
	public Long count() {
		return khoaDao.count();
	}

	@Override
	public Boolean existKey(String primaryKey) {
		return khoaDao.existKey(primaryKey);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return khoaDao.searchAuto(keyword);
	}

	@Override
	public List<Khoa> search(String keyword) {
		return khoaDao.search(keyword);
	}
}