package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.PhongHocDao;
import com.website.giadinh.entity.PhongHoc;

@Service("phongHocService")
@Transactional
public class PhongHocServiceImpl implements PhongHocService {
	@Autowired
	private PhongHocDao phongHocDao;

	@Override
	public List<PhongHoc> findAll() {
		return phongHocDao.findAll();
	}

	@Override
	public PhongHoc findById(String maPH) {
		return phongHocDao.findById(maPH);
	}

	@Override
	public Boolean isExistKey(String maPH) {
		return phongHocDao.isExistKey(maPH);
	}

	@Override
	public Boolean isExistReference(String maPH) {
		return phongHocDao.isExistReference(maPH);
	}

	@Override
	public void add(PhongHoc phongHoc) {
		phongHocDao.add(phongHoc);
	}

	@Override
	public void update(PhongHoc phongHoc) {
		phongHocDao.update(phongHoc);
	}

	@Override
	public void delete(PhongHoc phongHoc) {
		phongHocDao.delete(phongHoc);
	}

	@Override
	public Long countList() {
		return phongHocDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return phongHocDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return phongHocDao.searchAuto(keyword);
	}

	@Override
	public List<PhongHoc> search(String keyword) {
		return phongHocDao.search(keyword);
	}
}