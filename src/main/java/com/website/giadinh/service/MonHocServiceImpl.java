package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.MonHocDao;
import com.website.giadinh.entity.MonHoc;

@Service("monHocService")
@Transactional
public class MonHocServiceImpl implements MonHocService {
	@Autowired
	private MonHocDao monHocDao;
	
	@Override
	public List<MonHoc> findAll() {
		return monHocDao.findAll();
	}

	@Override
	public MonHoc findById(String maMH) {
		return monHocDao.findById(maMH);
	}

	@Override
	public Boolean isExistKey(String maMH) {
		return monHocDao.isExistKey(maMH);
	}

	@Override
	public Boolean isExistReference(String maMH) {
		return monHocDao.isExistReference(maMH);
	}

	@Override
	public void add(MonHoc monHoc) {
		monHocDao.add(monHoc);
	}

	@Override
	public void update(MonHoc monHoc) {
		monHocDao.update(monHoc);
	}

	@Override
	public void delete(MonHoc monHoc) {
		monHocDao.delete(monHoc);
	}

	@Override
	public Long countList() {
		return monHocDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return monHocDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return monHocDao.searchAuto(keyword);
	}

	@Override
	public List<MonHoc> search(String keyword) {
		return monHocDao.search(keyword);
	}
}