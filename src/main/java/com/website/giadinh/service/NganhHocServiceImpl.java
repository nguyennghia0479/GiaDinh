package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.NganhHocDao;
import com.website.giadinh.entity.NganhHoc;

@Service("nganhHocService")
@Transactional
public class NganhHocServiceImpl implements NganhHocService {
	@Autowired
	private NganhHocDao nganhHocDao;
	
	@Override
	public List<NganhHoc> findAll() {
		return nganhHocDao.findAll();
	}

	@Override
	public NganhHoc findById(String maNganh) {
		return nganhHocDao.findById(maNganh);
	}

	@Override
	public Boolean isExistKey(String maNganh) {
		return nganhHocDao.isExistKey(maNganh);
	}
	
	@Override
	public Boolean isExistReference(String maNganh) {
		return nganhHocDao.isExistReference(maNganh);
	}

	@Override
	public void add(NganhHoc nganhHoc) {
		nganhHocDao.add(nganhHoc);
	}

	@Override
	public void update(NganhHoc nganhHoc) {
		nganhHocDao.update(nganhHoc);
	}

	@Override
	public void delete(NganhHoc nganhHoc) {
		nganhHocDao.delete(nganhHoc);
	}

	@Override
	public Long countList() {
		return nganhHocDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return nganhHocDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return nganhHocDao.searchAuto(keyword);
	}

	@Override
	public List<NganhHoc> search(String keyword) {
		return nganhHocDao.search(keyword);
	}
}