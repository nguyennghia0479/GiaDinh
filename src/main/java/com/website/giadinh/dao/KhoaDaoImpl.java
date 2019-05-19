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

import com.website.giadinh.entity.GiangVien;
import com.website.giadinh.entity.Khoa;
import com.website.giadinh.entity.NganhHoc;

@Repository("khoaDao")
public class KhoaDaoImpl implements KhoaDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Khoa> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Khoa> cq = cb.createQuery(Khoa.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root).orderBy(cb.asc(root.get("tenKhoa")));
		List<Khoa> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public Khoa findById(String maKhoa) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Khoa.class, maKhoa);
	}

	@Override
	public Boolean isExistKey(String maKhoa) {
		if (findById(maKhoa) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maKhoa) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<NganhHoc> nganhHocQuery = cb.createQuery(NganhHoc.class);
		CriteriaQuery<GiangVien> giangVienQuery = cb.createQuery(GiangVien.class);
		
		Root<NganhHoc> nganhHocRoot = nganhHocQuery.from(NganhHoc.class);
		Root<GiangVien> giangVienRoot = giangVienQuery.from(GiangVien.class);
		
		Join<NganhHoc, Khoa> nganhHocJoin = nganhHocRoot.join("khoa");
		Join<GiangVien, Khoa> giangVienJoin = giangVienRoot.join("khoa");
		
		nganhHocQuery.select(nganhHocRoot).where(cb.like(nganhHocJoin.get("maKhoa"), maKhoa));
		giangVienQuery.select(giangVienRoot).where(cb.like(giangVienJoin.get("maKhoa"), maKhoa));
		
		List<NganhHoc> nganhHoclist = session.createQuery(nganhHocQuery).getResultList();
		List<GiangVien> giangvienList = session.createQuery(giangVienQuery).getResultList();
		if (nganhHoclist.isEmpty() && giangvienList.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void add(Khoa khoa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(khoa);
	}

	@Override
	public void update(Khoa khoa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(khoa);
	}

	@Override
	public void delete(Khoa khoa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(khoa);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(cb.count(root.get("maKhoa")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(cb.count(root.get("maKhoa"))).where(cb.or(cb.like(root.get("maKhoa"), "%" + keyword + "%"),
				cb.like(root.get("tenKhoa"), "%" + keyword + "%")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root.get("tenKhoa")).where((cb.like(root.get("tenKhoa"), "%" + keyword + "%")));
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(root.get("maKhoa")).where((cb.like(root.get("maKhoa"), "%" + keyword + "%")));
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<Khoa> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Khoa> cq = cb.createQuery(Khoa.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root).where(cb.or(cb.like(root.get("tenKhoa"), "%" + keyword + "%"),
				cb.like(root.get("maKhoa"), "%" + keyword + "%")));
		List<Khoa> list = session.createQuery(cq).getResultList();
		return list;
	}	
}