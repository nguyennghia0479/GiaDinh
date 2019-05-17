package com.website.giadinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.SinhVienDao;
import com.website.giadinh.entity.SinhVien;

@Service("sinhVienService")
@Transactional
public class SinhVienServiceImpl implements SinhVienService {
	@Autowired
	private SinhVienDao sinhVienDao;

	@Override
	public List<SinhVien> findAll() {
		return sinhVienDao.findAll();
	}

	@Override
	public SinhVien findById(String maSV) {
		return sinhVienDao.findById(maSV);
	}

	@Override
	public Boolean isExistKey(String maSV) {
		return sinhVienDao.isExistKey(maSV);
	}

	@Override
	public void add(SinhVien sinhVien) {
		sinhVienDao.add(sinhVien);
	}

	@Override
	public void update(SinhVien sinhVien) {
		sinhVienDao.update(sinhVien);
	}

	@Override
	public void delete(SinhVien sinhVien) {
		sinhVienDao.delete(sinhVien);
	}

	@Override
	public Long countList() {
		return sinhVienDao.countList();
	}

	@Override
	public Long countSearchResult(String keyword) {
		return sinhVienDao.countSearchResult(keyword);
	}

	@Override
	public List<String> searchAuto(String keyword) {
		return sinhVienDao.searchAuto(keyword);
	}

	@Override
	public List<SinhVien> search(String keyword) {
		return sinhVienDao.search(keyword);
	}
}