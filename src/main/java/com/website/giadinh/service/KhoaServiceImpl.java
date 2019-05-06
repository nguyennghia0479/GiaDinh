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
	public Boolean isExistKey(String maKhoa) {
		return khoaDao.isExistKey(maKhoa);
	}
	
	@Override
	public Boolean isExistReference(String maKhoa) {
		return khoaDao.isExistReference(maKhoa);
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
	public void delete(Khoa khoa) {
		khoaDao.delete(khoa);
	}

	@Override
	public Long countList() {
		return khoaDao.countList();
	}
	
	@Override
	public Long countSearchResult(String keyword) {
		return khoaDao.countSearchResult(keyword);
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