package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.entity.SinhVien;

@Repository("sinhVienDao")
public class SinhVienDaoImpl implements SinhVienDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SinhVien> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<SinhVien> cq = cb.createQuery(SinhVien.class);
		Root<SinhVien> root = cq.from(SinhVien.class);
		cq.select(root);
		List<SinhVien> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public SinhVien findById(String maSV) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(SinhVien.class, maSV);
	}

	@Override
	public Boolean isExistKey(String maSV) {
		if (findById(maSV) == null) {
			return false;
		}
		return true;
	}

	@Override
	public void add(SinhVien sinhVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(sinhVien);
	}

	@Override
	public void update(SinhVien sinhVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(sinhVien);
	}

	@Override
	public void delete(SinhVien sinhVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(sinhVien);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<SinhVien> root = cq.from(SinhVien.class);
		cq.select(cb.count(root.get("maSV")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<SinhVien> root = cq.from(SinhVien.class);
		Join<SinhVien, LopHoc> join = root.join("lopHoc");
		cq.select(cb.count(root.get("maSV"))).where(cb.or(cb.like(root.get("maSV"), "%" + keyword + "%"),
				cb.like(root.get("hoTen"), "%" + keyword + "%"), cb.like(join.get("maLop"), "%" + keyword + "%")));
		;
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<SinhVien> root = cq.from(SinhVien.class);
		Join<SinhVien, LopHoc> join = root.join("lopHoc");
		cq.select(root.get("hoTen")).where(cb.like(root.get("hoTen"), "%" + keyword + "%")).distinct(true);
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(root.get("maSV")).where(cb.like(root.get("maSV"), "%" + keyword + "%"));
			list = session.createQuery(cq).getResultList();
		}
		if (list.isEmpty()) {
			cq.select(join.get("maLop")).where(cb.like(join.get("maLop"), "%" + keyword + "%")).distinct(true);
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<SinhVien> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<SinhVien> cq = cb.createQuery(SinhVien.class);
		Root<SinhVien> root = cq.from(SinhVien.class);
		Join<SinhVien, LopHoc> join = root.join("lopHoc");
		cq.select(root).where(cb.or(cb.like(root.get("maSV"), "%" + keyword + "%"),
				cb.like(root.get("hoTen"), "%" + keyword + "%"), cb.like(join.get("maLop"), "%" + keyword + "%")));
		List<SinhVien> list = session.createQuery(cq).getResultList();
		return list;
	}
}