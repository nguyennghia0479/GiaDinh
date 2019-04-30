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

}