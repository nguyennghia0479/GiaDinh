package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.GiangVienDao;
import com.website.giadinh.entity.GiangVien;

@Service("giangVienService")
@Transactional
public class GiangVienServiceImpl implements GiangVienService {
	@Autowired
	private GiangVienDao giangVienDao;

	@Override
	public List<GiangVien> findAll() {
		return giangVienDao.findAll();
	}

	@Override
	public GiangVien findById(String maGV) {
		return giangVienDao.findById(maGV);
	}

	@Override
	public Boolean isExistKey(String maGV) {
		return giangVienDao.isExistKey(maGV);
	}

	@Override
	public Boolean isExistReference(String maGV) {
		return giangVienDao.isExistReference(maGV);
	}

	@Override
	public void add(GiangVien giangVien) {
		giangVienDao.add(giangVien);
	}

	@Override
	public void update(GiangVien giangVien) {
		giangVienDao.update(giangVien);
	}

	@Override
	public void delete(GiangVien giangVien) {
		giangVienDao.delete(giangVien);
	}

	@Override
	public Long countList() {
		return giangVienDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return giangVienDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return giangVienDao.searchAuto(keyword);
	}

	@Override
	public List<GiangVien> search(String keyword) {
		return giangVienDao.search(keyword);
	}
}