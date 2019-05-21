package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.TaiKhoan;

@Repository("taiKhoanDao")
public class TaiKhoanDaoImpl implements TaiKhoanDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TaiKhoan> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TaiKhoan> cq = cb.createQuery(TaiKhoan.class);
		Root<TaiKhoan> root = cq.from(TaiKhoan.class);
		cq.select(root);
		List<TaiKhoan> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public TaiKhoan findById(String maTK) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(TaiKhoan.class, maTK);
	}
	
	@Override
	public TaiKhoan findByPassword(String matKhau) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(TaiKhoan.class, matKhau);
	}

	@Override
	public void add(TaiKhoan taiKhoan) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(taiKhoan);
	}

	@Override
	public void update(TaiKhoan taiKhoan) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(taiKhoan);
	}

	@Override
	public void delete(TaiKhoan taiKhoan) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(taiKhoan);
	}

	@Override
	public Long countList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countSearchResult(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaiKhoan> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}