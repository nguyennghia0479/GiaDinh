package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.PhanCongDao;
import com.website.giadinh.entity.PhanCong;

@Service("phanCongService")
@Transactional
public class PhanCongServiceImpl implements PhanCongService {
	@Autowired
	private PhanCongDao phanCongDao;

	@Override
	public List<PhanCong> findAll() {
		return phanCongDao.findAll();
	}

	@Override
	public PhanCong findById(Integer maPC) {
		return phanCongDao.findById(maPC);
	}

	@Override
	public Boolean isExistReference(Integer maPC) {
		return phanCongDao.isExistReference(maPC);
	}

	@Override
	public void add(PhanCong phanCong) {
		phanCongDao.add(phanCong);
	}

	@Override
	public void update(PhanCong phanCong) {
		phanCongDao.update(phanCong);
	}

	@Override
	public void delete(PhanCong phanCong) {
		phanCongDao.delete(phanCong);
	}

	@Override
	public Long countList() {
		return phanCongDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return phanCongDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return phanCongDao.searchAuto(keyword);
	}

	@Override
	public List<PhanCong> search(String keyword) {
		return phanCongDao.search(keyword);
	}
}