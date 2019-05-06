package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.giadinh.dao.LopHocDao;
import com.website.giadinh.entity.LopHoc;

@Service("lopHocService")
public class LopHocServiceImpl implements LopHocService {
	@Autowired
	private LopHocDao lopHocDao;
	
	@Override
	public List<LopHoc> findAll() {
		return lopHocDao.findAll();
	}

	@Override
	public LopHoc findById(String maLop) {
		return lopHocDao.findById(maLop);
	}

	@Override
	public Boolean isExistKey(String maLop) {
		return lopHocDao.isExistKey(maLop);
	}

	@Override
	public Boolean isExistReference(String maLop) {
		return lopHocDao.isExistReference(maLop);
	}

	@Override
	public void add(LopHoc lopHoc) {
		lopHocDao.add(lopHoc);
	}

	@Override
	public void update(LopHoc lopHoc) {
		lopHocDao.update(lopHoc);
	}

	@Override
	public void delete(LopHoc lopHoc) {
		lopHocDao.delete(lopHoc);
	}

	@Override
	public Long countList() {
		return lopHocDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return lopHocDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return lopHocDao.searchAuto(keyword);
	}

	@Override
	public List<LopHoc> search(String keyword) {
		return lopHocDao.search(keyword);
	}
}